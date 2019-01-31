package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import java.lang.reflect.Member;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.model.member;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.presenter.MemberPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.presenter.MemberPresenterImple;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMember.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMember#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMember extends Fragment implements MemberInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog progressDialog;
    EditText editText;
    CheckBox checkBox;
    Button Submit;
    Boolean admin = false;
    MemberPresenter memberPresenter;
    SharedPrefs sharedPrefs;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddMember() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMember.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMember newInstance(String param1, String param2) {
        AddMember fragment = new AddMember();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_member, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        editText = (EditText)view.findViewById(R.id.add_member_email_id);
        checkBox = (CheckBox)view.findViewById(R.id.is_member_admin);
        Submit = (Button)view.findViewById(R.id.add_member_sumbit);
        sharedPrefs = new SharedPrefs(getContext());
        memberPresenter = new MemberPresenterImple();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void submit(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){

            case  R.id.is_member_admin:
                if(checked){
                    admin = checked;
                }else {
                    admin = checked;
                }

            case R.id.add_member_sumbit:
                showProgressbar(true);
                String email = editText.getText().toString().trim();
                if(emailInvalid(email)){
                    showProgressbar(false);
                    //toast;
                }
                else{
                    memberPresenter.getresponse(sharedPrefs.getAccessToken(),sharedPrefs.getClubId(),email,admin);
            }


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

    public boolean emailInvalid(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean a = matcher.matches();
        return !a;
    }
    @Override
    public void showProgressbar(Boolean show) {
        if(show){
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }

    @Override
    public void getResult(member member) {
       if(member.getSuccess()){
           showProgressbar(false);
           //toast;
           //return to back page;
       }
    }

    @Override
    public void buttonclick(Boolean click) {
        if(click){
            Submit.setClickable(true);
        }
        else{
            Submit.setClickable(false);
        }

    }

    @Override
    public void showmessage(String msg) {
        //toast
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
