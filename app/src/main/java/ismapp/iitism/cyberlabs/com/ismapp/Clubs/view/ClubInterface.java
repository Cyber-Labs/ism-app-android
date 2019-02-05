package ismapp.iitism.cyberlabs.com.ismapp.Clubs.view;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsName;

public interface ClubInterface  {
    void getlist(List<ClubsName> clubsNames);
    void showMessage(String message);
    void ShowProgressBar(boolean show);
}
