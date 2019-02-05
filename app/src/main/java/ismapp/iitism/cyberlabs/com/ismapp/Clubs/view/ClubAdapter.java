package ismapp.iitism.cyberlabs.com.ismapp.Clubs.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsList;
import ismapp.iitism.cyberlabs.com.ismapp.Clubs.model.ClubsName;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.View.ClubDetailsImpl;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.clubAdapterViewHolder> {

    private List<ClubsName> clubsLists  = new ArrayList<>();
   private Context mtcx;
   FragmentManager fragmentManager;
   SharedPrefs sharedPrefs ;

    public ClubAdapter(Context mtcx, FragmentManager fragmentManager,List<ClubsName> clubsNames) {
        this.fragmentManager = fragmentManager;
        this.mtcx = mtcx;
        sharedPrefs = new SharedPrefs(mtcx);
        this.clubsLists = clubsNames;
    }


    @NonNull
    @Override
    public clubAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(mtcx).inflate(R.layout.clublistcard,null);
      return new clubAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull clubAdapterViewHolder clubAdapterViewHolder, int i) {
       final ClubsName clubsName = clubsLists.get(i);
        clubAdapterViewHolder.clubname.setText(clubsName.getName());
        clubAdapterViewHolder.clubtagline.setText(clubsName.getTagline());
        Picasso.get().load(clubsName.getImageurl()).into(clubAdapterViewHolder.clubimage);
        clubAdapterViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //opens and passing arguments to fragments;
                Bundle bundle = new Bundle();
                bundle.putInt("id",clubsName.getClubid());
                ClubDetailsImpl clubDetails = new ClubDetailsImpl();
                clubDetails.setArguments(bundle);
                sharedPrefs.setClubId(clubsName.getClubid());
                fragmentManager.beginTransaction().add(R.id.main_contaner,new ClubDetailsImpl()).addToBackStack(null).commit();

            }
        }); }

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
            cardView = (CardView)itemView.findViewById(R.id.clubcard);

        }
    }
}
