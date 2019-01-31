package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.View;

import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.model.ClubDetails;

public interface ClubDetailInterface {
    void showProgressbar(boolean show);
    void showmodel(ClubDetails clubDetails);
    void showMessage(String msg);
}
