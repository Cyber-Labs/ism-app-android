package ismapp.iitism.cyberlabs.com.ismapp.adminsettings.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view.AddMember;

public class AdminSettingsFragment extends Fragment {

    @BindView(R.id.ibtn_admin_man_members)
    ImageButton ibtn_admin_man_members;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_settings, container, false);
        ButterKnife.bind(this,view);
        ibtn_admin_man_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addFragment(new AddMember());
            }
        });
        return view;
    }
}
