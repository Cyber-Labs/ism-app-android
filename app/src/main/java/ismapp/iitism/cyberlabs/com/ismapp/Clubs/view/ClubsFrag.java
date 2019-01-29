package ismapp.iitism.cyberlabs.com.ismapp.Clubs.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider.ProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider.RetrofitClubListImpl;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.presenter.PresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.presenter.PresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.MsgToast;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;

public class ClubsFrag extends Fragment implements ClubInterface {

    ClubAdapter clubAdapter;
    PresenterInterface presenterInterface;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clubs,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);
        clubAdapter = new ClubAdapter(getContext());

        presenterInterface = new PresenterImpl(ClubsFrag.this,new RetrofitClubListImpl());
        presenterInterface.requestclublist();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(clubAdapter);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void getlist(List<ClubsList> clubsLists) {
        ClubsList clubs = (ClubsList)clubsLists;
      clubAdapter.setData(clubs.getClubsNameList());
    }

   @Override
    public void showMessage(String message) {
       //setToast
    }

    @Override
    public void ShowProgressBar(boolean show) {
     if(true){
         progressDialog.show();
     }else{
         progressDialog.dismiss();
     }
    }
}
