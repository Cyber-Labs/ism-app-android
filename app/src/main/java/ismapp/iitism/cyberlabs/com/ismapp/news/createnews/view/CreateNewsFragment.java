package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.UriUtils;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model.CreateNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.presenter.CreateNewsPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.presenter.CreateNewsPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.provider.CreateNewsProviderImplementation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class CreateNewsFragment extends Fragment implements CreateNews {

    private  ProgressDialog progressDialog;
    private CreateNewsPresenterInterface createNewsPresenterInterface;
    private EditText createNewsDescription;
    private ImageView createNewsImage;
    private Button createNewsButton;
    public static final int PICK_IMAGE = 1;
    private MultipartBody.Part image;

    public CreateNewsFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragmenr_add_news, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        createNewsPresenterInterface = new CreateNewsPresenterImplementation(this,new CreateNewsProviderImplementation());
        createNewsImage = (ImageView)view.findViewById(R.id.create_news_pic);
        createNewsDescription = (EditText)view.findViewById(R.id.create_news_description);
        createNewsButton = (Button)view.findViewById(R.id.create_news_button);
        getResponseFromUser();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getResponseFromUser() {
        String description = createNewsDescription.getText().toString();
        createNewsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
        SharedPrefs sharedPrefs = new SharedPrefs(getContext());
        if(!description.isEmpty()){
            buttonClickable(true);
            createNewsPresenterInterface.getCreateNewsResponsePresenter(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),description,image);
        }
      else{
          ViewUtils.showToast(getContext(),"Description is Required");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK) {
            // Bitmap photo = (Bitmap) data.getExtras().get("data");
            createNewsImage.setImageURI(data.getData());



            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            // Uri tempUri = getImageUri(getContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(UriUtils.uriToFilePathConverter(getContext(),data.getData()));
            // File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);

// MultipartBody.Part is used to send also the actual file name
            image =
                    MultipartBody.Part.createFormData("event_pic", finalFile.getName(), requestFile);



        }
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressDialog.show();

        }else {
            progressDialog.dismiss();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(),message);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void getCreateNews(CreateNewsResponseModel createNewsResponseModel) {
    if(!createNewsResponseModel.isSuccess()){
        ViewUtils.showToast(getContext(),createNewsResponseModel.getMessage());
    }
    }

    @Override
    public void buttonClickable(boolean show) {
        if(show){
           createNewsButton.setEnabled(true);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event






}
