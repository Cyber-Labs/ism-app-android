package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.presenter;

interface NewsDetailsPresenterInterface {
    void getNewsDetails(String accessToken, int newsId);

    void getDeleteNews(String accessToken, int newsId);
}
