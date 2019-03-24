package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.createevent.view.CreateEvent;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Presenter.AdmintEventListPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Presenter.AdmintEventListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Provider.AdmintEventListProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;

public class AdminEventListFragment extends Fragment implements AdminEventListFragmentInterface {
    @BindView(R.id.pb_admin_eventlistst)
    ProgressBar progressBar;
    @BindView(R.id.rv_admin_eventlistst)
    RecyclerView recyclerView;
    private AdmintEventListPresenterInterface admintEventListPresenterInterface;
    private List<EventListModel> eventListModels;
    private SharedPrefs sharedPrefs;
    private AdminEventListAdapter adminEventListAdapter;
    private Bundle bundle;
    private int club_id;
    @BindView(R.id.iv_add_event)
    ImageView  addEvent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_eventlist, container, false);
        ButterKnife.bind(this,view);
        sharedPrefs = new SharedPrefs(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      if(eventListModels!=null) eventListModels.clear();
            else
                club_id=bundle.getInt("club_id");
            admintEventListPresenterInterface = new AdmintEventListPresenterImp(AdminEventListFragment.this,new AdmintEventListProviderImp(),club_id,-1);
            admintEventListPresenterInterface.reqEventList(sharedPrefs.getAccessToken());
            addEvent.setOnClickListener(v -> ((MainActivity)getActivity()).addFragment(new CreateEvent()));

        return view;
    }

    @Override
    public void getList(List<EventListModel> eventListModels) {
        this.eventListModels = eventListModels;

        adminEventListAdapter = new AdminEventListAdapter(eventListModels,getContext(),getActivity(),AdminEventListFragment.this,club_id);
        recyclerView.setAdapter(adminEventListAdapter);

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
