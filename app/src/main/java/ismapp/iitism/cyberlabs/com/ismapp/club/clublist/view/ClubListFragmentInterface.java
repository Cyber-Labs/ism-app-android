package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;

public interface ClubListFragmentInterface {
    void getList(List<ClubDetails> clubDetails);
    void showMessage(String message);
    void showProgressBar(boolean show);
}
