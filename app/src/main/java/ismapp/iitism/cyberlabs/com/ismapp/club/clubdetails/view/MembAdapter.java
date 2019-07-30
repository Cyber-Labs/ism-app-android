package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.Member;

public class MembAdapter  extends RecyclerView.Adapter<MembAdapter.MyViewHolder>{
    private final List<Member> memberList  ;
    private final Context mtcx;




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
            DrawableCompat.setTint(myViewHolder.im_isadmin.getDrawable(), ContextCompat.getColor(mtcx, R.color.colorAccent));
//          myViewHolder.im_isadmin.setBackgroundColor(R.color.colorAccent);

    }

    @Override
    public int getItemCount() {
        return memberList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_memb;
        final ImageView im_isadmin;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_memb= itemView.findViewById(R.id.tv_memb);
            im_isadmin= itemView.findViewById(R.id.iv_isadmin);
        }
    }
}
