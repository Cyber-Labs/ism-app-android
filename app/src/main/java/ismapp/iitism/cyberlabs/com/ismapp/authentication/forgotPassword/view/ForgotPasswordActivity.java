package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.model.ForgotPasswordResponse;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.presenter.ForgotPasswordPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.presenter.ForgotPasswordPresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.provider.RetrofitForgotPasswordProvider;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view.ResetPasswordActivity;
import ismapp.iitism.cyberlabs.com.ismapp.mvp.BaseActivity;

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordView {
    private EditText et_email;
    private String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        initialise();

    }

    private void initialise() {
        setTitle("Forgot Password");
        et_email = findViewById(R.id.et_email);
        Button bt_send = findViewById(R.id.forgot_submit);
        bt_send.setOnClickListener(this::submitEmail);
    }

    @Override
    protected void callPresenter() {
        if(isConnected())
        {
            ForgotPasswordPresenter forgotPasswordPresenter_ = new ForgotPasswordPresenterImpl(new RetrofitForgotPasswordProvider(), ForgotPasswordActivity.this);
            forgotPasswordPresenter_.getResponse(email);
        }
        else{
            showConnectionFailureDialog();
        }
    }

    @Override
    public void showOtpResponse(ForgotPasswordResponse forgotPasswordResponse) {
        if (forgotPasswordResponse.isSuccess()) {
            Intent intent = new Intent(getBaseContext(), ResetPasswordActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        }
        else
            showError(forgotPasswordResponse.getMessage());

    }

    public void submitEmail(View v) {
        email = et_email.getText().toString().trim();
        if (emailInvalid(email)) {
            showError("ENTER CORRECT EMAIL ID!");
        } else {
            callPresenter();
            hideKeyboard();
        }
    }

}
