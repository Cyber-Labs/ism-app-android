package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface NewsListProviderInterface {
    void newsListResponse(String accessToken, PresenterCallback presenterCallback);
    void clubNewsListResponse(String accessToken,int clubId, PresenterCallback presenterCallback);
}
