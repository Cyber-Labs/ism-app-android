package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.News;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter.NewsListPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter.NewsListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider.NewsListProviderImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class NewsList extends android.support.v4.app.Fragment implements NewsListInterface {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<News> newsArrayList;
    private NewsListPresenterInterface newsListPresenterInterface;
    private int club_id = 0;
    private boolean club_admin = false;
    public NewsList() {
        // Required empty public constructor
    }

    public static NewsList newInstance(int param1 ,boolean param2) {
        NewsList fragment = new NewsList();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putBoolean(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            club_id = getArguments().getInt(ARG_PARAM1);
            club_admin = getArguments().getBoolean(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_news_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(club_id!=0){
            //club news list
            if (newsArrayList == null) {
                SharedPrefs sharedPrefs = new SharedPrefs(getContext());
                newsListPresenterInterface = new NewsListPresenterImplementation(this, new NewsListProviderImplementation());
                newsListPresenterInterface.getClubNewsListResponse(sharedPrefs.getAccessToken(),club_id);
            }
        }
        else {
            if (newsArrayList == null) {
                SharedPrefs sharedPrefs = new SharedPrefs(getContext());
                newsListPresenterInterface = new NewsListPresenterImplementation(this, new NewsListProviderImplementation());
                newsListPresenterInterface.getNewsListResponse(sharedPrefs.getAccessToken());
            }
        }
        return view;
    }

    @Override
    public void showProgressBar(boolean show) {
   if(show){
       progressDialog.show();
   }else{
       progressDialog.dismiss();
   }
    }

    @Override
    public void getResponseNewsList(NewsListModel newsListModel) {
        NewsListAdapter newsListAdapter = new NewsListAdapter(getContext(),this,club_admin);
        newsArrayList = newsListModel.getNews_list();
        newsListAdapter.setData(newsArrayList);
        recyclerView.setAdapter(newsListAdapter);
    }


    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(),message);
    }
}
