package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view;

import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;

public interface NewsDetailsInterface {
    void showProgressBar(boolean show);
    void getResponse(NewsDetailsModel newsDetailsModel);
    void showMessage(String message);

}
