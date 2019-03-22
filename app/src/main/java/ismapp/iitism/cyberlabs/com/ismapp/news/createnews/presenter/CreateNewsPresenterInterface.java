package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.presenter;

import okhttp3.MultipartBody;

public interface CreateNewsPresenterInterface {
    void getCreateNewsResponsePresenter(String accessToken,int clubid, String description, MultipartBody.Part image);
}
