package ismapp.iitism.cyberlabs.com.ismapp.clubdetails.View;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Presenter.ClubDetailsPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.Presenter.ClubPresenInter;
import ismapp.iitism.cyberlabs.com.ismapp.clubdetails.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClubDetailsImpl.OnFragmentInteractionListener} interface
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
    TextView ClubName,Description;
    BottomNavigationView bottomNavigationView;
    ProgressDialog progressDialog;
    ClubPresenInter clubPresenInter;
    SharedPrefs sharedPrefs;

    private OnFragmentInteractionListener mListener;

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
        if (getArguments() != null) {
            id = getArguments().getInt("id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_club_details_impl, container, false);
        clubImage = (ImageView)view.findViewById(R.id.club_image);
        BrowserIcon = (ImageView)view.findViewById(R.id.browser_icon);
        ClubName = (TextView)view.findViewById(R.id.clubname);
        Description = (TextView)view.findViewById(R.id.club_description);
        bottomNavigationView = (BottomNavigationView)view.findViewById(R.id.bottom_nav);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        sharedPrefs = new SharedPrefs(getContext());
        clubPresenInter = new ClubDetailsPresenter();
        clubPresenInter.getclubdetail(sharedPrefs.getAccessToken(),id);
        return view;

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgressbar(boolean show) {
        if(show){
             progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }

    @Override
    public void showmodel(ClubDetails clubDetails) {
        Picasso.get().load(clubDetails.getImage_url()).into(clubImage);
        ClubName.setText(clubDetails.getName());
        Description.setText(clubDetails.getDescription());
        BrowserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open url;
            }
        });
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}