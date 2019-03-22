package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view;

import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;

public interface NewsListInterface {
    void showProgressBar(boolean show);
    void getResponseNewsList(NewsListModel newsListModel);
    void showMessage(String message);
}
