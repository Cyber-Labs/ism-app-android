package ismapp.iitism.cyberlabs.com.ismapp.viewfragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import ismapp.iitism.cyberlabs.com.ismapp.activities.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;


public class AboutFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);
        view.findViewById(R.id.tv_feedback_link).setOnClickListener(v -> {
            Uri uri = Uri.parse("http://cyberlabs.netlify.com"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) Objects.requireNonNull(getActivity())).addTitletoBar("About");
        ((MainActivity)getActivity()).addActionBar();
    }
}
