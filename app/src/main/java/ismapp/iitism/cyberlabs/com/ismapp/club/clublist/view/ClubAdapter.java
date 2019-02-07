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

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view.ClubDetailnMemb;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.clubAdapterViewHolder> {

    private List<ClubDetails> clubsLists  ;
   private Context mtcx;
   FragmentManager fragmentManager;
   SharedPrefs sharedPrefs ;
   FragmentActivity fragmentActivity;

    public ClubAdapter(Context mtcx, FragmentManager fragmentManager,FragmentActivity fragmentActivity) {
        this.fragmentManager = fragmentManager;
        this.mtcx = mtcx;
        sharedPrefs = new SharedPrefs(mtcx);
        this.fragmentActivity=fragmentActivity;

    }
public  void setdata(List<ClubDetails> clubDetails){
    this.clubsLists = clubDetails;
}

    @NonNull
    @Override
    public clubAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(mtcx).inflate(R.layout.item_rv_club_list,null);
      return new clubAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull clubAdapterViewHolder clubAdapterViewHolder, int i) {
        final ClubDetails clubDetails = clubsLists.get(i);
        clubAdapterViewHolder.clubname.setText(clubDetails.getName());
        clubAdapterViewHolder.clubtagline.setText(clubDetails.getTagline());
        Picasso.get().load(clubDetails.getImageurl()).into(clubAdapterViewHolder.clubimage);
//        clubAdapterViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                //opens and passing arguments to fragments;
//                Bundle bundle = new Bundle();
//                bundle.putInt("id",clubDetails.getClubid());
//                ClubDetailsImpl clubDetails = new ClubDetailsImpl();
//                clubDetails.setArguments(bundle);
//                sharedPrefs.setClubId(clubDetails.getClubid());
//               // fragmentManager.beginTransaction().add(R.id.main_contaner,new ClubDetailsImpl()).addToBackStack(null).commit();
//                ((MainActivity)fragmentActivity).addFragment(new ClubDetailsImpl());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return clubsLists.size();
    }

    class clubAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView clubimage;
        TextView clubname,clubtagline;
        CardView cardView;

        public clubAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            clubimage = (ImageView)itemView.findViewById(R.id.clubimage);
            clubname = (TextView)itemView.findViewById(R.id.clubname);
            clubtagline = (TextView)itemView.findViewById(R.id.clubtagline);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPrefs.setClubId(clubsLists.get(getAdapterPosition()).getId());
                    sharedPrefs.setClubName(clubsLists.get(getAdapterPosition()).getName());
                    ((MainActivity)fragmentActivity).addFragment(new ClubDetailnMemb());
                }
            });

        }
    }
}
