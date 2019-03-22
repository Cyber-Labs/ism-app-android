package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewsDetailsModel {
   private boolean success;
   private String message;
    private String club_name;
    private String description;
    private String news_pic_url;
    private String created_date;
    private String created_time;

}
