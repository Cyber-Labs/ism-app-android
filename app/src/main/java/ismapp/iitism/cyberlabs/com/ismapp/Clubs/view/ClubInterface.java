package ismapp.iitism.cyberlabs.com.ismapp.Clubs.view;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;

public interface ClubInterface  {
    void getlist(List<ClubsList> clubsLists);
    void showMessage(String message);
    void ShowProgressBar(boolean show);
}
