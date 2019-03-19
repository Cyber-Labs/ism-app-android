package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model.CreateNewsResponseModel;


public class CreateNewsFragment extends Fragment implements CreateNews {

    private  ProgressDialog progressDialog;

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


        return view;
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

    // TODO: Rename method, update argument and hook method into UI event






}
