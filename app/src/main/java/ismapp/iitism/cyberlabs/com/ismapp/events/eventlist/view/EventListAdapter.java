package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private List<EventListModel> eventListModels;
    private Context context;
    private SharedPrefs sharedPrefs;
    private FragmentActivity fragmentActivity;

    public EventListAdapter(List<EventListModel> eventListModels, Context context, FragmentActivity fragmentActivity) {
        this.eventListModels = eventListModels;
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_club_list, null);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {
        final EventListModel eventListModel = eventListModels.get(i);
        eventListViewHolder.tv_clubname.setText(eventListModel.getClub_name());
        eventListViewHolder.tv_clubtagline.setText(eventListModel.getShort_desc());
        eventListViewHolder.tv_venue.setText("Venue-"+eventListModel.getVenue());
        eventListViewHolder.tv_date.setText(eventListModel.getEvent_start_date());
       // Picasso.get().load(clubDetails.getImage_url()).into(clubAdapterViewHolder.clubimage);

    }

    @Override
    public int getItemCount() {
        return eventListModels.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {
        ImageView clubimage;
        TextView tv_clubname, tv_clubtagline,tv_venue,tv_date;
        CardView cardView;
        LinearLayout linearLayout;

        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = itemView.findViewById(R.id.iv_clubimage);
            tv_clubname = itemView.findViewById(R.id.iv_clubname);
            tv_clubtagline = itemView.findViewById(R.id.iv_clubtagline);
            tv_date = itemView.findViewById(R.id.tv_eventlist_date);
            tv_venue = itemView.findViewById(R.id.tv_eventlist_date);
            cardView = (CardView) itemView.findViewById(R.id.card_view_club);
            linearLayout = itemView.findViewById(R.id.lay_eventlist_venue_and_date);
            linearLayout.setVisibility(View.VISIBLE);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    sharedPrefs.setClubId(clubsLists.get(getAdapterPosition()).getId());
//                    sharedPrefs.setClubName(clubsLists.get(getAdapterPosition()).getName());
//                    sharedPrefs.setIsAdmin(clubsLists.get(getAdapterPosition()).isIs_admin());
//                    ((MainActivity)fragmentActivity).addFragment(new ClubDetailsFragment());
                }
            });

        }
    }
}
