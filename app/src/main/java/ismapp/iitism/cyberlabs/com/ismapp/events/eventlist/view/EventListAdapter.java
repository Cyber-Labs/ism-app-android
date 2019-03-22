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
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_eventlist, viewGroup,false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {
        final EventListModel eventListModel = eventListModels.get(i);
        eventListViewHolder.tv_title.setText(eventListModel.getTitle());
        eventListViewHolder.tv_venue.setText(eventListModel.getEvent_start_date()+" | "+eventListModel.getEvent_end_date()+" | "+eventListModel.getVenue());

        Picasso.get().load(eventListModel.getEvent_pic_url()).into(eventListViewHolder.clubimage);

    }

    @Override
    public int getItemCount() {
        return eventListModels.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {
        ImageView clubimage;
        TextView tv_title, tv_venue;


        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = itemView.findViewById(R.id.item_eventlist_img);
            tv_title = itemView.findViewById(R.id.item_eventlist_title);
            tv_venue = itemView.findViewById(R.id.item_eventlist_venue);




        }
    }
}
