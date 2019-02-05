package ismapp.iitism.cyberlabs.com.ismapp.Clubs.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider.ProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.Provider.RetrofitClubListImpl;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsName;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.presenter.PresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.presenter.PresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.MsgToast;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubsFrag extends Fragment implements ClubInterface {

    ClubAdapter clubAdapter;
    PresenterInterface presenterInterface;
    ProgressDialog progressDialog;
    SharedPrefs sharedPrefs;
    AlertDialog alertDialog;
    List<ClubsName> clubsNameArrayList=  new ArrayList<ClubsName>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clubs,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);
        clubAdapter = new ClubAdapter(getContext(),getFragmentManager(),clubsNameArrayList);
        sharedPrefs = new SharedPrefs(getContext());
        alertDialog= new AlertDialog.Builder(getContext()).setView(LayoutInflater.from(getContext()).inflate(R.layout.progress_bar,null)).setCancelable(false).create();

        presenterInterface = new PresenterImpl(ClubsFrag.this,new RetrofitClubListImpl());
        presenterInterface.requestclublist(sharedPrefs.getAccessToken());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //clubAdapter.setData(clubsNameArrayList);
        recyclerView.setAdapter(clubAdapter);

      /*  progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);*/



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
        if(clubs.getClubsNameList().size() == 0){
            Toast.makeText(getContext(),"vgvg",Toast.LENGTH_LONG);
        }else{
            clubsNameArrayList.clear();
           clubsNameArrayList = (List<ClubsName>) clubs;
           clubAdapter.notifyDataSetChanged();



        }
         }

   @Override
    public void showMessage(String message) {
       //setToast
    }

    @Override
    public void ShowProgressBar(boolean showw) {
     if(showw == true){
        // progressDialog.show();
         alertDialog.show();
     }else{
        // progressDialog.dismiss();
         alertDialog.dismiss();
     }
    }
}
