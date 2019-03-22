package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model.NewsDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.provider.NewsDetailsProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view.NewsDetailsInterface;

public class NewsDetailsPresenterImplementation implements NewsDetailsPresenterInterface {
    NewsDetailsInterface newsDetailsInterface;
    NewsDetailsProviderImplementation newsDetailsProviderImplementation;
    public void getNewsDetails(String accessToken, int newsId) {
        newsDetailsInterface.showProgressBar(true);
        newsDetailsProviderImplementation.getNewsResponse(accessToken, newsId, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsDetailsInterface.getResponse((NewsDetailsModel)o);
                newsDetailsInterface.showProgressBar(false);
            }

            @Override
            public void OnFailure(String msg) {
                    newsDetailsInterface.showMessage(msg);
                    newsDetailsInterface.showProgressBar(false);
            }
        });
    }
}
