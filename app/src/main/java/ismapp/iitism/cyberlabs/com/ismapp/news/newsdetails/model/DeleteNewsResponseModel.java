package ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DeleteNewsResponseModel {
    private boolean success;
    private String message;
}
