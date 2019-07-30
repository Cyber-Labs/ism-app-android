package ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Model.SignUpResponseModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Presenter.SignUpPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Presenter.SignUpPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.Provider.SignUpProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.view.VerifyOtpActivity;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityInterface {
    @BindView(R.id.signup_name)
    EditText name;
    @BindView(R.id.et_signup_email)
    EditText email;
    @BindView(R.id.et_signup_pass)
    EditText pass;
    @BindView(R.id.btn_signup_next)
    Button signUpBtn;
    @BindView(R.id.signup_coordlay)
    CoordinatorLayout clayout;
    @BindView(R.id.signup_name_lay)
    TextInputLayout nameLay;
    @BindView(R.id.signup_email_lay)
    TextInputLayout emailLay;
    @BindView(R.id.signup_pass_lay)
    TextInputLayout passLay;
    private String e;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setTitle("Create an Account");
        // nameLay.setErrorEnabled(true);
        //  emailLay.setErrorEnabled(true);
        //  passLay.setErrorEnabled(true);
        alertDialog = new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar, null)).setCancelable(false).create();

        signUpBtn.setOnClickListener(v -> {

            if (email.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty() || name.getText().toString().trim().isEmpty()) {
                ViewUtils.showToast(getApplicationContext(), "All Fields Are required");
            } else if (pass.getText().toString().trim().length() < 6) {
                ViewUtils.showToast(getApplicationContext(), "Password Must Contain min 6 letters!!!");
            } else {
                SignUpPresenter signUpPresenter = new SignUpPresenterImp(SignUpActivity.this, new SignUpProviderImp(),
                        email.getText().toString().trim(),
                        name.getText().toString().trim(),
                        pass.getText().toString());


                signUpPresenter.getSignUpResponse();

            }
        });
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show)
            alertDialog.show();
        else
            alertDialog.dismiss();

    }

    @Override
    public void setIntent(SignUpResponseModel signUpResponseModel) {
        if (!signUpResponseModel.getSuccess())
            Snackbar.make(clayout, signUpResponseModel.getMessage(), Snackbar.LENGTH_LONG).setAction("Ok", null)
                    .show();
        else {
            Intent i = new Intent(this, VerifyOtpActivity.class);
            i.putExtra("email", e);
            startActivity(i);
        }

    }
}
