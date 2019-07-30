package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.presenter;

import okhttp3.MultipartBody;

public interface CreateNewsPresenterInterface {
    void getCreateNewsResponsePresenter(String accessToken, int clubid, String description, MultipartBody.Part image);

    void getEditNewsResponsePresenter(String accessToken, int newsId, int clubid, String description, MultipartBody.Part image);
}
