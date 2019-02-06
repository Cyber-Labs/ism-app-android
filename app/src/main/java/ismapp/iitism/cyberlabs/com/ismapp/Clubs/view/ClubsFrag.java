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
import android.util.Log;
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
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
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
    RecyclerView recyclerView;
    List<ClubsName> clubsNameArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clubs,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);
        clubAdapter = new ClubAdapter(getContext(),getFragmentManager(),getActivity());
        sharedPrefs = new SharedPrefs(getContext());
        alertDialog= new AlertDialog.Builder(getContext()).setView(LayoutInflater.from(getContext()).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(clubsNameArrayList==null)
        {presenterInterface = new PresenterImpl(ClubsFrag.this,new RetrofitClubListImpl());
        presenterInterface.requestclublist(sharedPrefs.getAccessToken());}
        else
        {
            clubAdapter.setdata(clubsNameArrayList);
            recyclerView.setAdapter(clubAdapter);
        }



        //clubAdapter.setData(clubsNameArrayList);


      /*  progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);*/



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).addTitletoBar("Clubs");
        ((MainActivity)getActivity()).addActionBar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void getlist(List<ClubsName> clubsNames) {
         // clubsNameArrayList.clear();
          //Log.i("hello",clubsNames.toString());
          clubsNameArrayList=clubsNames;
         clubAdapter.setdata(clubsNames);
//           clubAdapter.notifyDataSetChanged();

      recyclerView.setAdapter(clubAdapter);



         }

   @Override
    public void showMessage(String message) {
       //setToast
       Log.e("check", "onFailure: " +message );
    }

    @Override
    public void ShowProgressBar(boolean showw) {
     if(showw){
        // progressDialog.show();
         alertDialog.show();
     }else{
        // progressDialog.dismiss();
         alertDialog.dismiss();
     }
    }



}
