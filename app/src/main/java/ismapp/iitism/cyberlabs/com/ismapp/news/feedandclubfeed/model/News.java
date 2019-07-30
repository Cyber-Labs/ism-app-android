package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class News {
    @SerializedName("id")
    private int news_id;
    private String club_name;
    private String description;
    private String news_pic_url;
    private String created_date;


}
