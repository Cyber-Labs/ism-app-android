package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.view;

import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.model.CreateNewsResponseModel;

public interface CreateNews {
    void showProgressBar(boolean show);

    void showMessage(String message);

    void getCreateNews(CreateNewsResponseModel createNewsResponseModel);

    void buttonClickable(boolean show);
}
