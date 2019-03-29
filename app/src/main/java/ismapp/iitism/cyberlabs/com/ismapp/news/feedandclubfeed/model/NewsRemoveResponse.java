package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NewsRemoveResponse {
    private boolean success;
    private String message;
}
