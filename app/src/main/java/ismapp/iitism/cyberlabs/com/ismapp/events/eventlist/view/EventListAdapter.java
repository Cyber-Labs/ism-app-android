package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListModel;
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
        final ImageView clubimage;
        final TextView tv_title;
        final TextView tv_venue;


        EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = itemView.findViewById(R.id.item_eventlist_img);
            tv_title = itemView.findViewById(R.id.item_eventlist_title);
            tv_venue = itemView.findViewById(R.id.item_eventlist_venue);
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
