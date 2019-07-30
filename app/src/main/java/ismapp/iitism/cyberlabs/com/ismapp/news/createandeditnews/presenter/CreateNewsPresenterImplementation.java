package ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.model.CreateNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.provider.CreateNewsProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.view.CreateNews;
import okhttp3.MultipartBody;

public class CreateNewsPresenterImplementation implements CreateNewsPresenterInterface {
    private final CreateNews createNews;
    private final CreateNewsProviderImplementation createNewsProviderImplementation;


    public CreateNewsPresenterImplementation(CreateNews createNews, CreateNewsProviderImplementation createNewsProviderImplementation) {
        this.createNews = createNews;
        this.createNewsProviderImplementation = createNewsProviderImplementation;
    }

    @Override
    public void getCreateNewsResponsePresenter(String accessToken, int clubid, String description, MultipartBody.Part image) {
        createNews.showProgressBar(true);
        createNewsProviderImplementation.getCreateNewsResponse(accessToken, clubid, description, image, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                createNews.showMessage("News");
                createNews.showProgressBar(false);
            }

            @Override
            public void OnFailure(String msg) {
                createNews.showMessage(msg);
            }
        });
    }

    @Override
    public void getEditNewsResponsePresenter(String accessToken, int newsId, int clubid, String description, MultipartBody.Part image) {

        createNewsProviderImplementation.getEditNewsResponse(accessToken, newsId, clubid, description, image, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                createNews.getCreateNews((CreateNewsResponseModel) o);
            }

            @Override
            public void OnFailure(String msg) {
                createNews.showMessage(msg);
            }
        });
    }
}
