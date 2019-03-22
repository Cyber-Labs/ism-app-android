package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import okhttp3.MultipartBody;

public interface CreateNewsProviderInterface {
    void getCreateNewsResponse(String accessToken,int clubid, String description, MultipartBody.Part image, PresenterCallback presenterCallback);
}
