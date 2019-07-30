package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.provider;

import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

interface NewsDetailsProviderInterface {
    void getNewsResponse(String accessToken, int newsId, PresenterCallback presenterCallback);

    void getDeleteNewsResponse(String accessToken, int newsId, PresenterCallback presenterCallback);

}
