package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;

public interface CreateNewsProviderInterface {
    void getCreateNewsResponse(String accessToken,int clubId, String description, MultipartBody.Part image, PresenterCallback presenterCallback);
    void getEditNewsResponse(String accessToken,int newsId,int clubId, String description, MultipartBody.Part image, PresenterCallback presenterCallback);

}
