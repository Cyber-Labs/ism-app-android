package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider;

import android.content.Context;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

interface NewsListProviderInterface {
    void newsListResponse(Context context,String accessToken, PresenterCallback presenterCallback);
    void clubNewsListResponse(String accessToken,int clubId, PresenterCallback presenterCallback);
    void removeEventResponse(String accessToken,int news_id,PresenterCallback presenterCallback);
}
