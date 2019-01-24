package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Presenter.SignUpPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Presenter.SignUpPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.Provider.SignUpProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public class SignUpViewImp extends AppCompatActivity implements SignUpView {
    @BindView(R.id.signup_name)
    EditText name;
    @BindView(R.id.signup_email)
    EditText email;
    @BindView(R.id.signup_pass)
    EditText pass;
    @BindView(R.id.signup_next)
    Button next;
    @BindView(R.id.signup_pb)
    ProgressBar pb;
    @BindView(R.id.signup_coordlay)
    CoordinatorLayout clayout;
    @BindView(R.id.signup_name_lay)
    TextInputLayout nameLay;
    @BindView(R.id.signup_email_lay)
    TextInputLayout emailLay;
    @BindView(R.id.signup_pass_lay)
    TextInputLayout passLay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);
        // nameLay.setErrorEnabled(true);
        //  emailLay.setErrorEnabled(true);
        //  passLay.setErrorEnabled(true);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(name.getText().toString().trim().isEmpty())
                    nameLay.setError("Enter the Name");
                if(email.getText().toString().trim().isEmpty())
                    emailLay.setError("Enter the Email-Id");
                if(!name.getText().toString().trim().isEmpty()&&!email.getText().toString().trim().isEmpty()&&pass.getText().toString().trim().length()>=6)
                {next.setBackgroundColor(getColor(R.color.colorPrimaryDark));next.setClickable(true);}
                else
                {next.setBackgroundColor(getColor(R.color.grey));next.setClickable(false);}



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!name.getText().toString().trim().isEmpty()&&!email.getText().toString().trim().isEmpty()&&pass.getText().toString().trim().length()>=6)
                {next.setBackgroundColor(getColor(R.color.colorPrimaryDark));next.setClickable(true);}
                else
                {next.setBackgroundColor(getColor(R.color.grey));next.setClickable(false);}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!name.getText().toString().trim().isEmpty()&&!email.getText().toString().trim().isEmpty()&&pass.getText().toString().trim().length()>=6)
                {next.setBackgroundColor(getColor(R.color.colorPrimaryDark));next.setClickable(true);}
                else
                {next.setBackgroundColor(getColor(R.color.grey));next.setClickable(false);}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpPresenter signUpPresenter =new SignUpPresenterImp(SignUpViewImp.this,new SignUpProviderImp(),
                        email.getText().toString().trim(),
                        name.getText().toString().trim(),
                        pass.getText().toString().trim());

                name.setText("");
                email.setText("");
                pass.setText("");
                signUpPresenter.getSignUpResponse();

            }
        });

    }

    @Override
    public void showProgressBar(boolean show) {
        if(show==true)
            pb.setVisibility(View.VISIBLE);
        else
            pb.setVisibility(View.GONE);

    }

    @Override
    public void setIntent(SignUpResponseModel signUpResponseModel) {
        if(signUpResponseModel.getSuccess()==false)
            Snackbar.make(clayout,signUpResponseModel.getMessage(),Snackbar.LENGTH_LONG).setAction("Ok",null)
                    .show();
        else
        {
            //Intent
        }

    }
}
