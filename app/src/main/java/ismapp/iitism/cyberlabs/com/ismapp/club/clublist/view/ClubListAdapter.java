package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsFragment;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubListAdapter extends RecyclerView.Adapter<ClubListAdapter.clubAdapterViewHolder> {

    private List<ClubDetails> clubsLists  ;
    private Context context;
    private SharedPrefs sharedPrefs ;
    private FragmentActivity fragmentActivity;

    ClubListAdapter(Context context, FragmentManager fragmentManager, FragmentActivity fragmentActivity) {
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        this.fragmentActivity=fragmentActivity;

    }
    void setData(List<ClubDetails> clubDetails){
        this.clubsLists = clubDetails;
    }

    @NonNull
    @Override
    public clubAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_club_list,null);
        return new clubAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull clubAdapterViewHolder clubAdapterViewHolder, int i) {
        final ClubDetails clubDetails = clubsLists.get(i);
        clubAdapterViewHolder.tv_clubname.setText(clubDetails.getName());
        clubAdapterViewHolder.tv_clubtagline.setText(clubDetails.getTagline());
        Picasso.get().load(clubDetails.getImageurl()).into(clubAdapterViewHolder.clubimage);
//        clubAdapterViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                ((MainActivity)fragmentActivity).addFragment(ClubDetailsFragment.newInstance("", ""));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return clubsLists.size();
    }

    class clubAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView clubimage;
        TextView tv_clubname, tv_clubtagline;
        CardView cardView;

        public clubAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = (ImageView)itemView.findViewById(R.id.iv_clubimage);
            tv_clubname = (TextView)itemView.findViewById(R.id.iv_clubname);
            tv_clubtagline = (TextView)itemView.findViewById(R.id.iv_clubtagline);
            cardView = (CardView)itemView.findViewById(R.id.card_view_club);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPrefs.setClubId(clubsLists.get(getAdapterPosition()).getId());
                    sharedPrefs.setClubName(clubsLists.get(getAdapterPosition()).getName());
                    ((MainActivity)fragmentActivity).addFragment(new ClubDetailsFragment());
                }
            });

        }
    }
}
