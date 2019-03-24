package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.Member;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.memberListViewHolder> {
    private List<Member> memberList  ;
    private final Context context;
    private final ManageMemberFragment manageMemberFragment;
    private RemoveMember removeMember;
    private SharedPrefs sharedPrefs;
    private Member member;

    MemberListAdapter(Context context, ManageMemberFragment manageMemberFragment, RemoveMember removeMember) {
        this.context = context;
        this.manageMemberFragment = manageMemberFragment;
        this.removeMember = removeMember;
    }

    MemberListAdapter(List<Member> memberList, Context context, ManageMemberFragment manageMemberFragment) {
        this.memberList = memberList;
        this.context = context;
        this.manageMemberFragment = manageMemberFragment;
    }


    @NonNull
    @Override
    public memberListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_card_manage_member,null);
        return new MemberListAdapter.memberListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull memberListViewHolder memberListViewHolder, int i) {
        member = memberList.get(i);
        memberListViewHolder.tv_name.setText(member.getName());


    }



    @Override
    public int getItemCount() {
        return memberList.size();
    }
    public class memberListViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_name ;
        final ImageView iv_delete;

        memberListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.member_name);
            iv_delete = itemView.findViewById(R.id.member_delete);
            sharedPrefs = new SharedPrefs(context);
            iv_delete.setOnClickListener(v -> {
                manageMemberFragment.showProgressBar(true);
                  manageMemberFragment.manageMemberPresenterInterface.getRemoveResponse(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),member.getEmail());
                   manageMemberFragment.getRemoveMemberResponse(removeMember);
                   if(removeMember.isSuccess()){
                       manageMemberFragment.showProgressBar(false);
                       ViewUtils.showToast(context,removeMember.getMessage());
                   }else{
                       manageMemberFragment.showProgressBar(false); }
            }
            );
        }
    }
}
