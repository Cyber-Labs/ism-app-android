package ismapp.iitism.cyberlabs.com.ismapp.adminsettings.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.View.AdminEventListFragment;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.view.ManageMemberFragment;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view.NewsList;


public class AdminSettingsFragment extends Fragment {

    @BindView(R.id.ibtn_admin_man_members)
    ImageButton ibtn_admin_man_members;
    @BindView(R.id.ibtn_admin_man_events)
    ImageButton ibtn_admin_man_events;
    @BindView(R.id.ibtn_admin_man_feed)
    ImageButton ibtn_admin_man_feed;
    Bundle bundle;
    int club_id;
    private boolean club_admin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_settings, container, false);
        ButterKnife.bind(this,view);

        bundle=getArguments();
        club_id=bundle.getInt("club_id");
        club_admin = bundle.getBoolean("club_admin");

        ibtn_admin_man_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addFragment(new ManageMemberFragment());
            }
        });
        ibtn_admin_man_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=new Bundle();
                b.putInt("club_id",club_id);
                AdminEventListFragment adminEventListFragment=new AdminEventListFragment();
                adminEventListFragment.setArguments(b);
                ((MainActivity)getActivity()).addFragment(adminEventListFragment);
            }
        });
        ibtn_admin_man_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).addFragment(NewsList.newInstance(club_id));

            }
        });

        return view;
    }
}
