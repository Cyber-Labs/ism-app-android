package ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.activities.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.createevent.view.CreateEvent;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Presenter.AdmintEventListPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Presenter.AdmintEventListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.admin_eventlist.Provider.AdmintEventListProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.model.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class AdminEventListAdapter extends RecyclerView.Adapter<AdminEventListAdapter.AdminEventListHolder> {
   private final List<EventListModel> eventListModels;
   private final Context context;
   private final SharedPrefs sharedPrefs;
   private final AdminEventListFragmentInterface adminEventListFragmentInterface;
    private final FragmentActivity fragmentActivity;
    private final int club_id;

    public AdminEventListAdapter(List<EventListModel> eventListModels, Context context, FragmentActivity fragmentActivity,AdminEventListFragmentInterface adminEventListFragmentInterface,int club_id) {
        this.eventListModels = eventListModels;
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        this.fragmentActivity = fragmentActivity;
        this.adminEventListFragmentInterface=adminEventListFragmentInterface;
        this.club_id=club_id;
    }

    @NonNull
    @Override
    public AdminEventListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_event_fulldetail, viewGroup,false);
        return new AdminEventListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminEventListHolder adminEventListHolder, int i) {
        EventListModel eventListModel=eventListModels.get(i);
        adminEventListHolder.ClubName.setText(eventListModel.getClub_name());
        adminEventListHolder.Description.setText(eventListModel.getDescription());
        adminEventListHolder.EventTitle.setText(eventListModel.getTitle());
        adminEventListHolder. Venue.setText(eventListModel.getVenue());
        adminEventListHolder.StartDate.setText(eventListModel.getEvent_start_date());
        adminEventListHolder.EndDate.setText(eventListModel.getEvent_end_date());
        Picasso.get().load(eventListModel.getEvent_pic_url()).into(adminEventListHolder.EventImage);

    }

    @Override
    public int getItemCount() {
        return eventListModels.size();
    }

    class AdminEventListHolder extends RecyclerView.ViewHolder{

        final ImageView ClubImg;

        final ImageView EventImage;

        final TextView ClubName;

        final TextView Description;

        final TextView EventTitle;
        final ImageView menu;
        final TextView Venue;
        final TextView StartDate;
        final TextView EndDate;
        Bundle bundle;
        AdminEventListHolder(@NonNull View itemView) {
            super(itemView);
            ClubImg=itemView.findViewById(R.id.iv_event_fd_clubimg);
            EventImage=itemView.findViewById(R.id.iv_event_fd_img);
            ClubName=itemView.findViewById(R.id.tv_event_fd_club);
            Description=itemView.findViewById(R.id.tv_event_fd_desc);
            EventTitle=itemView.findViewById(R.id.tv_event_fd_title);
            Venue=itemView.findViewById(R.id.tv_event_fd_venue);
            StartDate=itemView.findViewById(R.id.tv_event_fd_start_date);
            EndDate=itemView.findViewById(R.id.tv_event_fd_end_date);
            menu=itemView.findViewById(R.id.iv_event_fd_menu);
            menu.setVisibility(View.VISIBLE);
            PopupMenu popupMenu=new PopupMenu(fragmentActivity,menu);
            popupMenu.getMenuInflater().inflate(R.menu.event_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if(item.getItemId()==R.id.event_edit)
                {   Bundle bundle=new Bundle();
                    bundle.putString("event_pic",eventListModels.get(getAdapterPosition()).getEvent_pic_url());
                    bundle.putString("event_title",eventListModels.get(getAdapterPosition()).getTitle());
                    bundle.putString("event_desc",eventListModels.get(getAdapterPosition()).getDescription());
                    bundle.putString("event_startdate",eventListModels.get(getAdapterPosition()).getEvent_start_date());
                    bundle.putString("event_enddate",eventListModels.get(getAdapterPosition()).getEvent_end_date());
                    bundle.putString("event_venue",eventListModels.get(getAdapterPosition()).getVenue());
                    bundle.putInt("event_id",eventListModels.get(getAdapterPosition()).getId());

                    CreateEvent createEvent=new CreateEvent();
                    createEvent.setArguments(bundle);
                    ((MainActivity)fragmentActivity).addFragment(createEvent);
                }
                else
                {
                     AlertDialog alertDialog = new AlertDialog.Builder(context)
                            .setTitle("Are you sure you want to remove this Event?")
                            .setCancelable(false)
                            .setPositiveButton("Remove", (dialog, which) -> {
                                AdmintEventListPresenterInterface admintEventListPresenterInterface = new AdmintEventListPresenterImp(adminEventListFragmentInterface, new AdmintEventListProviderImp(), -1, eventListModels.get(getAdapterPosition()).getId());
                                admintEventListPresenterInterface.reqEventList(sharedPrefs.getAccessToken());
                                eventListModels.remove(getAdapterPosition());
                                notifyDataSetChanged();

                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {


                            })

                            .create();
                    alertDialog.show();
                }
                return true;
            });
            menu.setOnClickListener(v -> popupMenu.show());
        }
    }
}
