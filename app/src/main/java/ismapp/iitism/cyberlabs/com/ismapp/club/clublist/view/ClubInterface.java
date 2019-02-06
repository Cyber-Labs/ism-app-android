package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;

public interface ClubInterface  {
    void getlist(List<ClubDetails> clubDetails);
    void showMessage(String message);
    void ShowProgressBar(boolean show);
}
