package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.view;

import ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model.CreateNewsResponseModel;

public interface CreateNews {
    void showProgressBar(boolean show);
    void showMessage(String message);
    void getCreateNews(CreateNewsResponseModel createNewsResponseModel);
}
