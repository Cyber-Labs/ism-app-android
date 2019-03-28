package ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view.AddMember;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.model.RemoveMember;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.presenter.ManageMemberPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.presenter.ManageMemberPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.managemember.memberlist.provider.ManageMemberListProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link ManageMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageMemberFragment extends android.support.v4.app.Fragment implements ManageMemberViewInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    ManageMemberPresenterInterface manageMemberPresenterInterface;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Context context;
    private String mParam2;


    public ManageMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageMemberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageMemberFragment newInstance(String param1, String param2) {
        ManageMemberFragment fragment = new ManageMemberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manage_member, container, false);
        context = getContext();
        FloatingActionButton addmember = (FloatingActionButton)view.findViewById(R.id.fab_add_member);
        addmember.setOnClickListener(v -> ((MainActivity)getActivity()).addFragment(new AddMember()));
        SharedPrefs sharedPrefs = new SharedPrefs(getContext());
         recyclerView = (RecyclerView)view.findViewById(R.id.rv_member_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        manageMemberPresenterInterface = new ManageMemberPresenter(getContext(),this,new ManageMemberListProvider());
        manageMemberPresenterInterface.getMemberResponse(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());

        return view;
    }

    @Override
    public void showProgressBar(boolean show) {
       if(show){
           progressDialog.show();
       }else{
           progressDialog.dismiss();
       }
    }


    @Override
    public void getMemberList(MemberListResponse memberListResponse) {

        MemberListAdapter memberListAdapter = new MemberListAdapter(memberListResponse.getMember_list(), context,this);

    }


    @Override
    public void getRemoveMemberResponse(RemoveMember removeMember) {
           MemberListAdapter memberListAdapter = new MemberListAdapter(context,this,removeMember);
    }


    // TODO: Rename method, update argument and hook method into UI event



}
