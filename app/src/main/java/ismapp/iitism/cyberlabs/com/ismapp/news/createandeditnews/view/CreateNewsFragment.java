package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.Objects;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.UriUtils;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.model.CreateNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.presenter.CreateNewsPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.presenter.CreateNewsPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.provider.CreateNewsProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view.NewsList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class CreateNewsFragment extends Fragment implements CreateNews {
    private static final String ARG_PARAM1 = "param1";
    private  ProgressDialog progressDialog;
    private CreateNewsPresenterInterface createNewsPresenterInterface;
    private EditText createNewsDescription;
    private ImageView createNewsImage;
    private Button createNewsButton;
    private static final int PICK_IMAGE = 1;
    private int news_id = 0;
    private MultipartBody.Part image;

    public CreateNewsFragment() {
        // Required empty public constructor
    }


    public static NewsList newInstance(int param1) {
        NewsList fragment = new NewsList();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news_id = getArguments().getInt(ARG_PARAM1);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_news, container, false);
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


    private void getResponseFromUser() {
        String description = createNewsDescription.getText().toString();
        createNewsImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

        });
        SharedPrefs sharedPrefs = new SharedPrefs(getContext());
        if(news_id!=0){
            if(!description.isEmpty()){
                buttonClickable(true);
                createNewsButton.setOnClickListener(v -> createNewsPresenterInterface.getEditNewsResponsePresenter(sharedPrefs.getAccessToken(),news_id,sharedPrefs.getClubId(),description,image));

            }

        }else{
            if(!description.isEmpty()){
                buttonClickable(true);
                createNewsButton.setOnClickListener(v -> createNewsPresenterInterface.getCreateNewsResponsePresenter(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),description,image));

            }
            else{
                ViewUtils.showToast(getContext(),"Description is Required");
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK) {
            // Bitmap photo = (Bitmap) data.getExtras().get("data");
            createNewsImage.setImageURI(data.getData());



            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            // Uri tempUri = getImageUri(getContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(Objects.requireNonNull(UriUtils.uriToFilePathConverter(getContext(), data.getData())));
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


    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(),message);
    }
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
        }else{
            createNewsButton.setEnabled(false);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event






}
