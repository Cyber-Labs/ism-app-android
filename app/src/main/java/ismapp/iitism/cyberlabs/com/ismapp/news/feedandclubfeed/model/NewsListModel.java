package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class NewsListModel {
    private boolean success;
    private String message;
    private ArrayList<News> news_list;
}
