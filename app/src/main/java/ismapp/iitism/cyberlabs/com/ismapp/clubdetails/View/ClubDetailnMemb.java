package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubDetailnMemb extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_details_impl, container, false);
        bottomNavigationView=(BottomNavigationView)view.findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.desc_club);
        return view;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.desc_club: getChildFragmentManager().beginTransaction().add(R.id.club_details_container,new ClubDetailsImpl()).commit();

        }
        return true;
    }
}
