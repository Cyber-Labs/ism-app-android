package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider.ClubListProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter.ClubListClubListPresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter.ClubListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;

public class ClubFragment extends Fragment implements ClubFragmentInterface {

    ClubAdapter clubAdapter;
    ClubListPresenterInterface clubListPresenterInterface;
    ProgressDialog progressDialog;
    SharedPrefs sharedPrefs;
    RecyclerView recyclerView;
    List<ClubDetails> clubDetailsArrayList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list,container,false);
        ButterKnife.bind(this, view);

        recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);
        clubAdapter = new ClubAdapter(getContext(),getFragmentManager(),getActivity());
        sharedPrefs = new SharedPrefs(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(clubDetailsArrayList ==null)
        {
            clubListPresenterInterface = new ClubListClubListPresenterImpl(ClubFragment.this,new ClubListProviderImp());
            clubListPresenterInterface.requestclublist(sharedPrefs.getAccessToken());}
        else
        {
            showProgressBar(false);
            clubAdapter.setData(clubDetailsArrayList);
            recyclerView.setAdapter(clubAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).addTitletoBar("Clubs");
        ((MainActivity)getActivity()).addActionBar();
    }

    @Override
    public void getList(List<ClubDetails> clubDetails) {
        clubDetailsArrayList = clubDetails;
        clubAdapter.setData(clubDetails);
        recyclerView.setAdapter(clubAdapter);
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(), message);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
