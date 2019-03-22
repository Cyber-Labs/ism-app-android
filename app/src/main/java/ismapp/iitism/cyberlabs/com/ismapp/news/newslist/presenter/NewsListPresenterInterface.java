package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.presenter;

public interface NewsListPresenterInterface {
    void getNewsListResponse(String accessToken);
    void getClubNewsListResponse(String accessToken,int clubId);
}
