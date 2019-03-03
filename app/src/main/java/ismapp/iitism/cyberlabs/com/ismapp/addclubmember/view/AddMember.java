package ismapp.iitism.cyberlabs.com.ismapp.addclubmember.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider.RetroMember;
import ismapp.iitism.cyberlabs.com.ismapp.addclubmember.provider.memberprointer;
import ismapp.iitism.cyberlabs.com.ismapp.helper.PresenterCallback;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

import static android.support.v4.content.ContextCompat.getSystemService;


public class AddMember extends Fragment implements MemberInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog progressDialog;
    TextInputEditText editText;
    CheckBox checkBox;
    Button Submit;
    Boolean admin = false;
    MemberPresenter memberPresenter;
    SharedPrefs sharedPrefs;
    TextInputLayout lay_add_member_email_id;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        final View view = inflater.inflate(R.layout.fragment_add_member, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        editText = view.findViewById(R.id.add_member_email_id);
        checkBox = (CheckBox) view.findViewById(R.id.is_member_admin);
        Submit = (Button) view.findViewById(R.id.add_member_sumbit);
        sharedPrefs = new SharedPrefs(getContext());
        memberPresenter = new MemberPresenterImple(this, new RetroMember());
        lay_add_member_email_id = view.findViewById(R.id.lay_add_member_email_id);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    public void submit() {

                showProgressbar(true);
                String email = editText.getText().toString().trim();
                if (email.isEmpty())
                {showProgressbar(false);  lay_add_member_email_id.setError("Empty Field");}

                else if (emailInvalid(email)) {
                    showProgressbar(false);
                    lay_add_member_email_id.setError("Not Valid");
                } else {
                    memberPresenter.getresponse(sharedPrefs.getAccessToken(), sharedPrefs.getClubId(), email, checkBox.isChecked());



        }


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
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void getResult(member member) {
        if (member.getSuccess()) {
            showProgressbar(false);
            //toast;
            //return to back page;
        }
    }

    @Override
    public void buttonclick(Boolean click) {
        if (click) {
            Submit.setClickable(true);
        } else {
            Submit.setClickable(false);
        }

    }

    @Override
    public void showmessage(String msg) {
        //toast
    }

}
