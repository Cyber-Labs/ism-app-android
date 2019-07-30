package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailsFragment;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.activities.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubListAdapter extends RecyclerView.Adapter<ClubListAdapter.clubAdapterViewHolder> {

    private List<ClubDetails> clubsLists  ;
    private final Context context;
    private final SharedPrefs sharedPrefs ;
    private final FragmentActivity fragmentActivity;

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
        clubAdapterViewHolder.tv_clubtagline.setText(clubDetails.gettagline());
        Picasso.get().load(clubDetails.getImage_url()).error(R.drawable.cyberlabs).into(clubAdapterViewHolder.clubimage);
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
        final ImageView clubimage;
        final TextView tv_clubname;
        final TextView tv_clubtagline;
        final CardView cardView;

        clubAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = itemView.findViewById(R.id.iv_clubimage);
            tv_clubname = itemView.findViewById(R.id.tv_clubname);
            tv_clubtagline = itemView.findViewById(R.id.tv_clubtagline);
            cardView = itemView.findViewById(R.id.card_view_club);
            cardView.setOnClickListener(v -> {
                sharedPrefs.setClubId(clubsLists.get(getAdapterPosition()).getId());
                sharedPrefs.setClubName(clubsLists.get(getAdapterPosition()).getName());
                ((MainActivity)fragmentActivity).addFragment(new ClubDetailsFragment());
            });

        }
    }
}
