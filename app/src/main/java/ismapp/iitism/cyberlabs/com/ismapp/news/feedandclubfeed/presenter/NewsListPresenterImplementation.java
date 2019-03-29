package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsListModel;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.NewsRemoveResponse;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider.NewsListProviderImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view.NewsListInterface;

public class NewsListPresenterImplementation implements NewsListPresenterInterface {
   private final NewsListInterface newsListInterface;
   private final NewsListProviderImplementation newsListProviderImplementation;

    public NewsListPresenterImplementation(NewsListInterface newsListInterface, NewsListProviderImplementation newsListProviderImplementation) {
        this.newsListInterface = newsListInterface;
        this.newsListProviderImplementation = newsListProviderImplementation;
    }

    @Override
    public void getNewsListResponse(String accessToken) {
        newsListInterface.showProgressBar(true);
        newsListProviderImplementation.newsListResponse(accessToken, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsListInterface.showProgressBar(false);
                newsListInterface.getResponseNewsList((NewsListModel)o);
            }

            @Override
            public void OnFailure(String msg) {

                newsListInterface.showProgressBar(false);
                newsListInterface.showMessage(msg);
            }
        });
    }


    @Override
    public void getClubNewsListResponse(String accessToken, int clubId) {
        newsListInterface.showProgressBar(true);
        newsListProviderImplementation.clubNewsListResponse(accessToken, clubId, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsListInterface.showProgressBar(false);
                newsListInterface.getResponseNewsList((NewsListModel)o);

            }

            @Override
            public void OnFailure(String msg) {
                newsListInterface.showProgressBar(false);
                newsListInterface.showMessage(msg);
            }
        });
    }

    @Override
    public void getRemoveEventResponse(String accessToken, int newsId) {
        newsListInterface.showProgressBar(true);
        newsListProviderImplementation.removeEventResponse(accessToken, newsId, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                newsListInterface.showProgressBar(false);
                newsListInterface.showMessage(((NewsRemoveResponse)o).getMessage());
            }

            @Override
            public void OnFailure(String msg) {
                newsListInterface.showProgressBar(false);
                newsListInterface.showMessage(msg);

            }
        });
    }
}
