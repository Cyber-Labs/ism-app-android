package ismapp.iitism.cyberlabs.com.ismapp.news.createnews.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateNewsResponseModel {
    private boolean success;
    private String message;
}
