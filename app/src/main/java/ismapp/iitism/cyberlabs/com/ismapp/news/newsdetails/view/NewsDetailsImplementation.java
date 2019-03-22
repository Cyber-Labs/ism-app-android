package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsDetailsImplementation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsDetailsImplementation extends android.support.v4.app.Fragment implements NewsDetailsInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private TextView clubName;
    private TextView Description;
    private TextView createdDate;
    private TextView createdTime;
    private ImageView clubPic;
    private ProgressDialog progressDialog;


    // TODO: Rename and change types of parameters
    private int newsId;



    public NewsDetailsImplementation() {
        // Required empty public constructor
    }



    public static NewsDetailsImplementation newInstance(int param1) {
        NewsDetailsImplementation fragment = new NewsDetailsImplementation();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsId = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        clubName = view.findViewById(R.id.news_details_club_name);
        clubPic = view.findViewById(R.id.news_details_club_pic);
        Description = view.findViewById(R.id.news_details_description);
        createdDate = view.findViewById(R.id.news_details_created_date);
        createdTime = view.findViewById(R.id.news_details_created_time);
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
        }
        else{
            progressDialog.dismiss();
        }
    }

    @Override
    public void getResponse(NewsDetailsModel newsDetailsModel) {
        if(newsDetailsModel.isSuccess()){
            clubName.setText(newsDetailsModel.getClub_name());
            Picasso.get().load(newsDetailsModel.getNews_pic_url()).into(clubPic);
            Description.setText(newsDetailsModel.getDescription());
            createdDate.setText(newsDetailsModel.getCreated_date());
            createdTime.setText(newsDetailsModel.getCreated_time());
        }else{
            ViewUtils.showToast(getContext(),newsDetailsModel.getMessage());
        }


    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(),message);
    }
}
