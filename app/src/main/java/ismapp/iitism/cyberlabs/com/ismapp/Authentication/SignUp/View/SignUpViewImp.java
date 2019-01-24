package ismapp.iitism.cyberlabs.com.ismapp.Authentication.SignUp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
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
