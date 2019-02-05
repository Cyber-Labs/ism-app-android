package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Presenter;

import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.View.ClubDetailInterface;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubDetailsPresenter implements ClubPresenInter {
    ClubDetailInterface clubDetailInterface;

    public ClubDetailsPresenter(ClubDetailInterface clubDetailInterface, ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Provider.ClubDetailInterface clubDetailInterfaces) {
        this.clubDetailInterface = clubDetailInterface;
        this.clubDetailInterfaces = clubDetailInterfaces;
    }

    ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Provider.ClubDetailInterface clubDetailInterfaces;
    @Override
    public void getclubdetail(String access_token, int id) {
        clubDetailInterface.showProgressbar(true);
      clubDetailInterfaces.getclubdetailresponse(access_token, id, new PresenterCallback() {
          @Override
          public void onSuccess(Object o) {
              clubDetailInterface.showmodel((ClubDetails)o);
              clubDetailInterface.showProgressbar(false);

          }

          @Override
          public void OnFailure(String msg) {
          clubDetailInterface.showProgressbar(false);
          clubDetailInterface.showMessage(msg);
          }
      });
    }
}
