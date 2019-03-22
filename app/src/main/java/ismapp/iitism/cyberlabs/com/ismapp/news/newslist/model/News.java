package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class News {
    private int news_id;
    private String club_name;
    private String description;
    private String news_pic_url;
    private String created_date;


}
