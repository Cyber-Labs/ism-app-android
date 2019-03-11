package ismapp.iitism.cyberlabs.com.ismapp.createevent.view;

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
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.model.CreateEventModel;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.presenter.CreateEventPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.presenter.CreateEventPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.provider.CreateEventProviderImplementation;
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
    private EditText shortDescription;
    private EditText Description;
    private TextView EndDate;
    private TextView StartDate;
    private TextView EndTime;
    private TextView StartTime;
    private EditText venue;
    private Button submit;
    private Calendar calendar;
    public static final int PICK_IMAGE = 1;
    private MultipartBody.Part image;
    ImageView iv_start_day;
    ImageView iv_start_time;
    ImageView iv_end_day;
    ImageView iv_end_time;
    CreateEventPresenterInterface createEventPresenterInterface;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        selectImage = (ImageView)view.findViewById(R.id.iv_add_event_pic);
        title = (EditText) view.findViewById(R.id.tv_add_event_title);
        description = (EditText)view.findViewById(R.id.tv_add_event_desc);
        shortDescription = (EditText)view.findViewById(R.id.tv_add_event_short_desc);
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
        createEventPresenterInterface = new CreateEventPresenterImplementation(this,new CreateEventProviderImplementation());
        selectImage();
        iv_start_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddEventDate();
            }
        });
        iv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddEventTime();
            }
        });

        return   view;

    }

    private void selectAddEventDate() {
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { StartDate.setText(String.format("%d/%d/%d", year, month+1, dayOfMonth));

            }
        },Calendar.YEAR,Calendar.MONTH+1,Calendar.DAY_OF_MONTH);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    private void selectAddEventTime()
    {
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
              StartTime.setText(hourOfDay+":"+minute);
            }
        },Calendar.HOUR_OF_DAY,Calendar.MINUTE,false);

        timePickerDialog.show();
    }

    private void selectImage() {
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            selectImage.setImageBitmap(photo);



            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));
           // File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);

// MultipartBody.Part is used to send also the actual file name
             image =
                    MultipartBody.Part.createFormData("image", finalFile.getName(), requestFile);



        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    @Override
    public void showProgressBar(Boolean show) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showResponse(CreateEventModel createEventModel) {
      if(createEventModel.getSuccess()){
          ViewUtils.showToast(getContext(),createEventModel.getMessage());
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

    }

    @Override
    public void getUserResponse() {
        final String Title = title.getText().toString();
        final String Description = description.getText().toString();
        final String ShortDescription = shortDescription.getText().toString();
//        final String StartDate = Startate.getText().toString();
//        final String EndDate =EndDate.getText().toString();
        final String Venue = venue.getText().toString();
        showButtonClickable(true);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                SharedPrefs sharedPrefs = new SharedPrefs(getContext());
//                createEventPresenterInterface.getCreateEventRequest(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),Title,ShortDescription,Description,Venue,StartDate,EndDate,image);
//            }
//        });

    }

}
