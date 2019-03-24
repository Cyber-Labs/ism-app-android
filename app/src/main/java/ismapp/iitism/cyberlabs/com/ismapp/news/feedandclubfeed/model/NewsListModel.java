package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NewsListModel {
    private boolean success;
    private String message;
    private ArrayList<News> news_list;
}
