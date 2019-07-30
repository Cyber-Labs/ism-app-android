package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.DeleteNewsResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.provider.NewsDetailsProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view.NewsDetailsInterface;

public class NewsDetailsPresenterImplementation implements NewsDetailsPresenterInterface {
    private final NewsDetailsInterface newsDetailsInterface;
    private final NewsDetailsProviderImplementation newsDetailsProviderImplementation;

    public NewsDetailsPresenterImplementation(NewsDetailsInterface newsDetailsInterface, NewsDetailsProviderImplementation newsDetailsProviderImplementation) {
        this.newsDetailsInterface = newsDetailsInterface;
        this.newsDetailsProviderImplementation = newsDetailsProviderImplementation;
    }

    public void getNewsDetails(String accessToken, int newsId) {
        newsDetailsInterface.showProgressBar(true);
        newsDetailsProviderImplementation.getNewsResponse(accessToken, newsId, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsDetailsInterface.getResponse((NewsDetailsModel) o);
                newsDetailsInterface.showProgressBar(false);
            }

            @Override
            public void OnFailure(String msg) {
                newsDetailsInterface.showMessage(msg);
                newsDetailsInterface.showProgressBar(false);
            }
        });
    }

    @Override
    public void getDeleteNews(String accessToken, int newsId) {
        newsDetailsInterface.showProgressBar(true);
        newsDetailsProviderImplementation.getDeleteNewsResponse(accessToken, newsId, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsDetailsInterface.getDeleteResponse((DeleteNewsResponseModel) o);
            }

            @Override
            public void OnFailure(String msg) {
                newsDetailsInterface.showMessage(msg);
            }
        });
    }
}
