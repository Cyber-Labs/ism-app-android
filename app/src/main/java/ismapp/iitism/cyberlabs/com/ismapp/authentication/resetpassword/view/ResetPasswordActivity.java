package ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginViewImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.model.ResetPasswordModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.presenter.ResetPasswordPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.presenter.ResetPasswordPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.provider.ResetPasswordProviderImp;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordActivityInterface {

    EditText ed_confirm_password;
    private TextView tv_email;
    private EditText ed_password;
    private EditText ed_otp;
    private Button submit;
    private ProgressDialog progressDialog;
    private String email, otp, password, newpassword;
    private Dialog dialog;

    private boolean connected;
    private ResetPasswordPresenterInterface ResetPasswordPresenterInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset);
        setTitle("Reset Password");
        initialised();
    }

    private void initialised() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);

        email = Objects.requireNonNull(getIntent().getExtras()).getString("email");
        System.out.println(email);
        tv_email = findViewById(R.id.rs_email);
        tv_email.setText(email);

        ed_password = findViewById(R.id.rs_pass);
        ed_otp = findViewById(R.id.rs_otp);
        submit = findViewById(R.id.btn_rs_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed_info();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = Objects.requireNonNull(connectivityManager)
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    @Override
    public void showProgressbar(boolean check) {
        if (check) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

    }

    @Override
    public void checkConnection() {
        if (!connected) {
            //dialog box;
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_coon);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules5 = (TextView) dialog.findViewById(R.id.rules5);
            btn.setText("Retry");
            rules5.setText("No internet connection.Please try again.");
            dialog.setTitle("Connectivity Failed");
            dialog.setCancelable(false);
            dialog.show();
            btn.setOnClickListener(v -> {

                ResetPasswordPresenterInterface = new ResetPasswordPresenterImp(ResetPasswordActivity.this, new ResetPasswordProviderImp());
                ResetPasswordPresenterInterface.sendResponse(email, password, Integer.parseInt(otp));
                dialog.dismiss();
            });

        }
    }

    public void proceed_info() {


        password = ed_password.getText().toString();
        otp = ed_otp.getText().toString().trim();
        int Otp = Integer.parseInt(otp);
        if (password.isEmpty() || otp.isEmpty()) {
            Toast.makeText(this, "All Fields Are required", Toast.LENGTH_LONG).show();
        } else if (password.length() < 6)
            Toast.makeText(this, "Password Must Contain min 6 letters!!!", Toast.LENGTH_SHORT).show();

        else {
            ResetPasswordPresenterInterface = new ResetPasswordPresenterImp(this, new ResetPasswordProviderImp());
            ResetPasswordPresenterInterface.sendResponse(email, password, Otp);
        }
    }


    @Override
    public void showResponse(ResetPasswordModel resetPasswordModel) {
        if (resetPasswordModel.isSuccess()) {
            //intent to activity_login;
            startActivity(new Intent(ResetPasswordActivity.this,LoginViewImp.class));
        } else {
            Toast.makeText(this, resetPasswordModel.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void messagerror(String msg) {

    }

    @Override
    public void verifyBtnClickable() {

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
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
}
