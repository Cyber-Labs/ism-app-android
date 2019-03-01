package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubDetailsPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubDetailsPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.ClubDetailsProviderInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link ClubDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubDetailsFragment extends Fragment implements ClubDetailsFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int id;
    private String mParam2;
    ImageView clubImage,BrowserIcon;
    TextView tv_clubName, tv_description, tv_Tagline;
    ClubDetailsPresenterInterface clubDetailsPresenterInterface;
    LinearLayout lay;
    SharedPrefs sharedPrefs;
    int i=0;
    RecyclerView rv_show_members;
    MembAdapter membAdapter;
    View bottomSheet;
    ProgressBar pb_club_details;

   // private OnFragmentInteractionListener mListener;

    public ClubDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubDetailsFragment newInstance(String param1, String param2) {
        ClubDetailsFragment fragment = new ClubDetailsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            id = getArguments().getInt("id");
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_club_details, container, false);


        clubImage = (ImageView)view.findViewById(R.id.club_image);
     //   BrowserIcon = (ImageView)view.findViewById(R.id.browser_icon);
        tv_clubName = (TextView)view.findViewById(R.id.club_name);
        tv_description = (TextView)view.findViewById(R.id.club_description);
        tv_Tagline =(TextView)view.findViewById(R.id.club_tag);
        lay= view.findViewById(R.id.club_lay);
        rv_show_members= view.findViewById(R.id.rv_show_members);
        rv_show_members.setHasFixedSize(true);
        rv_show_members.setLayoutManager(new LinearLayoutManager(getContext()));
        pb_club_details=view.findViewById(R.id.pb_club_details);
        ((MainActivity)getActivity()).changeActionBar();
        sharedPrefs = new SharedPrefs(getContext());
        ((MainActivity)getActivity()).addTitletoBar(sharedPrefs.getClubName());
        clubDetailsPresenterInterface = new ClubDetailsPresenterImp(this,new ClubDetailsProviderInterface());
       ;
        clubDetailsPresenterInterface.getclubdetail(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());
        clubDetailsPresenterInterface.requestmemblist(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());

        bottomSheet =view.findViewById(R.id.bottom_sheet_members);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("amanan", "onClick: ");
               behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        return view;

    }

    @Override
    public void showProgressbar(boolean show) {
        i++;

        if(show|| i<3){
             pb_club_details.setVisibility(View.VISIBLE);
        }else{
            pb_club_details.setVisibility(View.GONE);
        }
    }

    @Override
    public void showmodel(ClubDetailsModel clubDetailsModel) {
         Picasso.get().load(clubDetailsModel.getImage_url()).into(clubImage);
         lay.setBackgroundColor(R.color.colorPrimary);
        tv_clubName.setText(clubDetailsModel.getName());
        tv_Tagline.setText(clubDetailsModel.getTagline());
        tv_description.setText(clubDetailsModel.getDescription());

//        BrowserIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //open url;
//            }
//        });
    }

    @Override
    public void showMessage(String msg) {
        //setToast;
    }

    @Override
    public void showMemberList(MemberListResponse memberListResponse) {
        membAdapter=new MembAdapter(getContext(),memberListResponse.getMember_list());
        rv_show_members.setAdapter(membAdapter);

    }

}
