package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public class SingleEventDetailFragment extends Fragment {
    @BindView(R.id.iv_event_fd_clubimg)
    ImageView ClubImg;
    @BindView(R.id.iv_event_fd_img)
    ImageView EventImage; private String sEventImage;
    @BindView(R.id.tv_event_fd_club)
    TextView ClubName;
    @BindView(R.id.tv_event_fd_desc)
    TextView Description;
    @BindView(R.id.tv_event_fd_title)
    TextView EventTitle;
    @BindView(R.id.tv_event_fd_venue)
    TextView Venue;
    @BindView(R.id.tv_event_fd_start_date)
    TextView StartDate;
    @BindView(R.id.tv_event_fd_end_date)
    TextView EndDate;
    private Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_event_fulldetail,container,false);
        ButterKnife.bind(this,view);
        ClubName.setText(bundle.getString("club_name"));
        Description.setText(bundle.getString("description"));
        EventTitle.setText(bundle.getString("title"));
        Venue.setText(bundle.getString("venue"));
        StartDate.setText(bundle.getString("start_date"));
        EndDate.setText(bundle.getString("end_date"));
        sEventImage=bundle.getString("pic_url");

        Picasso.get().load(sEventImage).into(EventImage);


        return view;
    }
}
