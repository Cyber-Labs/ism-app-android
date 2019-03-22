package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.view;

import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model.NewsListModel;

public interface NewsListInterface {
    void showProgressBar(boolean show);
    void getResponseNewsList(NewsListModel newsListModel);
    void showMessage(String message);
}
