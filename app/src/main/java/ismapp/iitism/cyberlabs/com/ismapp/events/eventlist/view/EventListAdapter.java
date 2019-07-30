package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.activities.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.model.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private final List<EventListModel> eventListModels;
    private final Context context;
    private final SharedPrefs sharedPrefs;
    private final FragmentActivity fragmentActivity;

    EventListAdapter(List<EventListModel> eventListModels, Context context, FragmentActivity fragmentActivity) {
        this.eventListModels = eventListModels;
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_event, viewGroup,false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {
        final EventListModel eventListModel = eventListModels.get(i);
        eventListViewHolder.tv_title.setText(eventListModel.getTitle());
        eventListViewHolder.tv_venue.setText(eventListModel.getVenue());
        eventListViewHolder.tv_sdate.setText(eventListModel.getEvent_start_date());
        eventListViewHolder.tv_edate.setText(eventListModel.getEvent_end_date());
        Picasso.get().load(eventListModel.getEvent_pic_url()).error(R.drawable.spinkit).into(eventListViewHolder.clubimage);

    }

    @Override
    public int getItemCount() {
        return eventListModels.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder { //tv_event_fd_end_date
        final ImageView clubimage;
        final TextView tv_title;
        final TextView tv_venue;
        final TextView tv_sdate;
        final TextView tv_edate;


        EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = itemView.findViewById(R.id.item_eventlist_img);
            tv_title = itemView.findViewById(R.id.item_eventlist_title);
            tv_venue = itemView.findViewById(R.id.item_eventlist_venue);
            tv_sdate = itemView.findViewById(R.id.tv_event_fd_start_date);
            tv_edate = itemView.findViewById(R.id.tv_event_fd_end_date);
              itemView.setOnClickListener(v -> {
                      SingleEventDetailFragment fragment=new SingleEventDetailFragment();
                      Bundle args=new Bundle();
                      args.putString("title",eventListModels.get(getAdapterPosition()).getTitle());
                      args.putString("description",eventListModels.get(getAdapterPosition()).getDescription());
                      args.putString("club_name",eventListModels.get(getAdapterPosition()).getClub_name());
                      args.putString("start_date",eventListModels.get(getAdapterPosition()).getEvent_start_date());
                      args.putString("end_date",eventListModels.get(getAdapterPosition()).getEvent_end_date());
                      args.putString("pic_url",eventListModels.get(getAdapterPosition()).getEvent_pic_url());
                      args.putString("venue",eventListModels.get(getAdapterPosition()).getVenue());
                      fragment.setArguments(args);
                  ((MainActivity)fragmentActivity).addFragment(fragment);
              });



        }
    }
}
