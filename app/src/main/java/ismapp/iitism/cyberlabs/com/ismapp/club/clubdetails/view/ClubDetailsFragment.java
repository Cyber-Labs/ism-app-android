package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.adminsettings.view.AdminSettingsFragment;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetailsModel;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.MemberListResponse;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubDetailsPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubDetailsPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.RetrofitClubDetailsProvider;
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
    private ImageView clubImage;
    ImageView BrowserIcon;
    private TextView tv_clubName;
    private TextView tv_description;
    private TextView tv_Tagline;
    private ClubDetailsPresenterInterface clubDetailsPresenterInterface;
    private LinearLayout lay;
    private SharedPrefs sharedPrefs;
    private int i=0;
    private RecyclerView rv_show_members;
    private MembAdapter membAdapter;
    private View bottomSheet;
    private ProgressBar pb_club_details;
    private View view;
    private String fb_link;
    private int club_id;
    private boolean club_admin;
    private Menu menu;

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
        view = inflater.inflate(R.layout.fragment_club_details, container, false);
        setHasOptionsMenu(true);

        clubImage = view.findViewById(R.id.club_image);
        /* BrowserIcon = (ImageView)view.findViewById(R.id.browser_icon); */
        tv_clubName = view.findViewById(R.id.club_name);
        tv_description = view.findViewById(R.id.club_description);
        tv_Tagline = view.findViewById(R.id.club_tag);
        lay= view.findViewById(R.id.club_lay);
        rv_show_members= view.findViewById(R.id.rv_show_members);
        rv_show_members.setHasFixedSize(true);
        rv_show_members.setLayoutManager(new LinearLayoutManager(getContext()));
        pb_club_details=view.findViewById(R.id.pb_club_details);
        view.findViewById(R.id.fab_fb_link).setVisibility(View.GONE);

        ((MainActivity) Objects.requireNonNull(getActivity())).changeActionBar();
        sharedPrefs = new SharedPrefs(getContext());
        ((MainActivity)getActivity()).addTitletoBar(sharedPrefs.getClubName());
        clubDetailsPresenterInterface = new ClubDetailsPresenterImp(this,new RetrofitClubDetailsProvider());
       ;
        clubDetailsPresenterInterface.getclubdetail(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());
        clubDetailsPresenterInterface.requestMemberList(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());

        bottomSheet =view.findViewById(R.id.bottom_sheet_members);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheet.setOnClickListener(v -> behavior.setState(BottomSheetBehavior.STATE_EXPANDED));
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        this.menu = menu;
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                    AdminSettingsFragment adminSettingsFragment=new AdminSettingsFragment();
                    Bundle bundle=new Bundle();
                    bundle.putInt("club_id",club_id);
                    bundle.putBoolean("club_admin",club_admin);

                    adminSettingsFragment.setArguments(bundle);
                    ((MainActivity) Objects.requireNonNull(getActivity())).addFragment(adminSettingsFragment);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Picasso.get().load(clubDetailsModel.getImage_url()).error(R.drawable.cyberlabs).into(clubImage);
         lay.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tv_clubName.setText(clubDetailsModel.getName());
        tv_Tagline.setText(clubDetailsModel.getTagline());
        tv_description.setText(clubDetailsModel.getDescription());
        fb_link = clubDetailsModel.getFb_link();
        view.findViewById(R.id.fab_fb_link).setVisibility(View.VISIBLE);
        view.findViewById(R.id.fab_fb_link).setOnClickListener(v -> {
            Uri uri = Uri.parse(fb_link); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
//        if(clubDetailsModel.getIs_admin())
//            view.findViewById(R.id.fab_add_member).setVisibility(View.VISIBLE);
        if(clubDetailsModel.getIs_admin())
        {
//      view.findViewById(R.id.fab_add_member).setVisibility(View.VISIBLE);
        club_id=clubDetailsModel.getId();
        club_admin = clubDetailsModel.getIs_admin();
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(true);
        }




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
