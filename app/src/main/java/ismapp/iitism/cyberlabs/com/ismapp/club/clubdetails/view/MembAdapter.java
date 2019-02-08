package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ismapp.iitism.cyberlabs.com.ismapp.R;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubAdapter;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class MembAdapter  extends RecyclerView.Adapter<MembAdapter.MyViewHolder>{
    private List<Member> memberList  ;
    private Context mtcx;




    public MembAdapter(Context mtcx,List<Member> memberList) {

        this.mtcx = mtcx;

        this.memberList=memberList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mtcx).inflate(R.layout.item_members,null);
        return new MembAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Member member = memberList.get(i);
        myViewHolder.tv_memb.setText(member.getName());
        if(member.isIs_admin())
          myViewHolder.im_isadmin.setVisibility(View.VISIBLE);
        else
            myViewHolder.im_isadmin.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return memberList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_memb;
        ImageView im_isadmin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_memb=(TextView)itemView.findViewById(R.id.tv_memb);
            im_isadmin=(ImageView)itemView.findViewById(R.id.im_isadmin);
        }
    }
}
