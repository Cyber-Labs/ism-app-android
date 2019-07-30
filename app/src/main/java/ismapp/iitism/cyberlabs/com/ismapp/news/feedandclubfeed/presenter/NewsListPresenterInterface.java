package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter;

public interface NewsListPresenterInterface {
    void getNewsListResponse(String accessToken);

    void getClubNewsListResponse(String accessToken, int clubId);

    void getRemoveEventResponse(String accessToken, int newsId);
}
