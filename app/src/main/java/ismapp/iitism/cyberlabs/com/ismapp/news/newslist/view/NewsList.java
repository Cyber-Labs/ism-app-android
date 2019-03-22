package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.view;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
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
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model.News;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.presenter.NewsListPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.presenter.NewsListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.provider.NewsListProviderImplementation;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class NewsList extends android.support.v4.app.Fragment implements NewsListInterface {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<News> newsArrayList;
    private NewsListPresenterInterface newsListPresenterInterface;
    public NewsList() {
        // Required empty public constructor
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

        if(newsArrayList == null){
           SharedPrefs sharedPrefs = new SharedPrefs(getContext());
         newsListPresenterInterface = new NewsListPresenterImplementation(this,new NewsListProviderImplementation());
         newsListPresenterInterface.getNewsListResponse(sharedPrefs.getAccessToken());
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
        NewsListAdapter newsListAdapter = new NewsListAdapter(getContext(),this);
        newsArrayList = newsListModel.getNews_list();
        newsListAdapter.setData(newsArrayList);
        recyclerView.setAdapter(newsListAdapter);
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(),message);
    }
}
