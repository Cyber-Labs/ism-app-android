package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public interface NewsListProviderInterface {
    void newsListResponse(String accessToken, PresenterCallback presenterCallback);
    void clubNewsListResponse(String accessToken,int clubId, PresenterCallback presenterCallback);
}
