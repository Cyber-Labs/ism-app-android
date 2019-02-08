package ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.view;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubDetailsPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.presenter.ClubPresenInter;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.provider.RetroClubDetail;
import ismapp.iitism.cyberlabs.com.ismapp.club.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link ClubDetailsImpl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubDetailsImpl extends Fragment implements ClubDetailInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int id;
    private String mParam2;
    ImageView clubImage,BrowserIcon;
    TextView ClubName,Description,Tagline;

    AlertDialog alertDialog;
    ClubPresenInter clubPresenInter;
    LinearLayout lay;
    SharedPrefs sharedPrefs;


   // private OnFragmentInteractionListener mListener;

    public ClubDetailsImpl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubDetailsImpl.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubDetailsImpl newInstance(String param1, String param2) {
        ClubDetailsImpl fragment = new ClubDetailsImpl();

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
        View view = inflater.inflate(R.layout.club_description, container, false);


        clubImage = (ImageView)view.findViewById(R.id.club_image);
     //   BrowserIcon = (ImageView)view.findViewById(R.id.browser_icon);
        ClubName = (TextView)view.findViewById(R.id.club_name);
        Description = (TextView)view.findViewById(R.id.club_description);
        Tagline=(TextView)view.findViewById(R.id.club_tag);
        lay=(LinearLayout)view.findViewById(R.id.club_lay);

        ((MainActivity)getActivity()).changeActionBar();
        alertDialog= new AlertDialog.Builder(getContext()).setView(LayoutInflater.from(getContext()).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        sharedPrefs = new SharedPrefs(getContext());
        ((MainActivity)getActivity()).addTitletoBar(sharedPrefs.getClubName());
        clubPresenInter = new ClubDetailsPresenter(this,new RetroClubDetail());
       ;
        clubPresenInter.getclubdetail(sharedPrefs.getAccessToken(),sharedPrefs.getClubId());
        return view;

    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
      /*  if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    /*    if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showProgressbar(boolean show) {
        if(show){
             alertDialog.show();
        }else{
            alertDialog.dismiss();
        }
    }

    @Override
    public void showmodel(ClubDetails ClubDetails) {
         Picasso.get().load(ClubDetails.getImage_url()).into(clubImage);
         lay.setBackgroundColor(R.color.colorPrimary);
        ClubName.setText(ClubDetails.getName());
        Tagline.setText(ClubDetails.getTagline());
        Description.setText(ClubDetails.getDescription());

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




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
