package ismapp.iitism.cyberlabs.com.ismapp.createevent.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.Objects;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.presenter.CreateEventPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.presenter.CreateEventPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.provider.CreateEventProviderImplementation;

import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.UriUtils;

import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class CreateEvent extends Fragment implements CreateEventFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView selectImage;
    private EditText title;
    private EditText description;

    private TextView EndDate;
    private TextView StartDate;
    private TextView EndTime;
    private TextView StartTime;
    private EditText venue;
    private Button submit;
    private Calendar calendar;
    private ProgressBar pb_add_event;
    private static final int PICK_IMAGE = 1;
    private MultipartBody.Part image;
    private ImageView iv_start_day;
    private ImageView iv_start_time;
    private ImageView iv_end_day;
    private ImageView iv_end_time;
    private int event_id;
    private CreateEventPresenterInterface createEventPresenterInterface;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   private Bundle bundle;

    public CreateEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent newInstance(String param1, String param2) {
        CreateEvent fragment = new CreateEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if(getArguments()!=null){ bundle=getArguments(); event_id=bundle.getInt("event_id",0);}
       else event_id=0;

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        selectImage = (ImageView)view.findViewById(R.id.iv_add_event_pic);
        title = (EditText) view.findViewById(R.id.tv_add_event_title);
        description = (EditText)view.findViewById(R.id.tv_add_event_desc);
        venue = (EditText)view.findViewById(R.id.tv_add_event_venue);
        StartDate = view.findViewById(R.id.add_event_start_date);
        EndDate = view.findViewById(R.id.add_event_end_date);
        StartTime=view.findViewById(R.id.add_event_start_time);
        EndTime=view.findViewById(R.id.add_event_end_time);
        submit = (Button)view.findViewById(R.id.create_event_submit);
        iv_start_day=view.findViewById(R.id.iv_start_day);
        iv_start_time=view.findViewById(R.id.iv_start_time);
        iv_end_day=view.findViewById(R.id.iv_end_day);
        iv_end_time=view.findViewById(R.id.iv_end_time);
        calendar= Calendar.getInstance();

        pb_add_event=view.findViewById(R.id.pb_add_event);


        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                ((MainActivity) Objects.requireNonNull(getActivity())).onBackPressed();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();
        if(event_id!=0)
        {
            Picasso.get().load(bundle.getString("event_pic")).into(selectImage);
            title.setText(bundle.getString("event_title"));
            description.setText(bundle.getString("event_desc"));
            StartDate.setText(bundle.getString("event_startdate"));
            EndDate.setText(bundle.getString("event_enddate"));
            venue.setText(bundle.getString("event_venue"));
        }
        createEventPresenterInterface = new CreateEventPresenterImplementation(this,new CreateEventProviderImplementation());
        selectimage();
        iv_start_day.setOnClickListener(v -> selectAddEventDate(1));
        iv_end_day.setOnClickListener(v -> selectAddEventDate(2));
        iv_start_time.setOnClickListener(v -> selectAddEventTime(1));
        iv_end_time.setOnClickListener(v -> selectAddEventTime(2));
        showButtonClickable(true);
        submit.setOnClickListener(v -> {



          if(selectImage.getDrawable()==null || title.getText().toString().trim().isEmpty() || StartDate.getText().toString().trim().isEmpty()|| description.getText().toString().trim().isEmpty() )
              ViewUtils.showToast(getContext(),"Enter all required fields");
          else
              getUserResponse();


        });
        return   view;

    }

    private void selectAddEventDate(int i) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(Objects.requireNonNull(getActivity()), (view, year, month, dayOfMonth) -> {
            if(i==1){StartDate.setText(String.format("%d/%d/%d", year, month+1, dayOfMonth));}
                    else
                     {EndDate.setText(String.format("%d/%d/%d", year, month+1, dayOfMonth));};

        },Calendar.YEAR,Calendar.MONTH+1,Calendar.DAY_OF_MONTH);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    private void selectAddEventTime(int i)
    {
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), (view, hourOfDay, minute) -> {
         if(i==1){ StartTime.setText(hourOfDay+":"+minute);}
               else
                    EndTime.setText(hourOfDay+":"+minute);
        },Calendar.HOUR_OF_DAY,Calendar.MINUTE,false);

        timePickerDialog.show();
    }

    private void selectimage() {
        selectImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK) {
           // Bitmap photo = (Bitmap) data.getExtras().get("data");
            selectImage.setImageURI(data.getData());




            File finalFile = new File(Objects.requireNonNull(UriUtils.uriToFilePathConverter(getContext(), data.getData())));

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);


             image =
                    MultipartBody.Part.createFormData("event_pic", finalFile.getName(), requestFile);



        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(uri, null, null, null, null);
        Objects.requireNonNull(cursor).moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    @Override
    public void showProgressBar(Boolean show) {
        if(show)
            pb_add_event.setVisibility(View.VISIBLE);
         else
             pb_add_event.setVisibility(View.GONE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showResponse(CreateEventModel createEventModel) {
      if(createEventModel.getSuccess()){
          ViewUtils.showToast(getContext(),createEventModel.getMessage());
          //((MainActivity)getActivity()).onBackPressed();
      }
    }

    @Override
    public void showButtonClickable(Boolean showButton) {
     if(showButton){
         submit.setEnabled(true);
              }
    }

    @Override
    public void showMessage(String message) {
       ViewUtils.showToast(getContext(),message);
        ((MainActivity) Objects.requireNonNull(getActivity())).onBackPressed();

    }

    @Override
    public void getUserResponse() {
        final String Title = title.getText().toString();
        final String Description = description.getText().toString();



        final String StartDate = this.StartDate.getText().toString();
       final String EndDate =this.EndDate.getText().toString();
        final String Venue = venue.getText().toString();
        SharedPrefs sharedPrefs = new SharedPrefs(getContext());


        createEventPresenterInterface.getCreateEventRequest(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),Title,Description,Venue,StartDate,EndDate,image,event_id);




    }

}
