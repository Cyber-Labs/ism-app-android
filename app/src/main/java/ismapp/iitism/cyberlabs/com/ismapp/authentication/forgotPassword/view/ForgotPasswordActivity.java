package ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ismapp.iitism.cyberlabs.com.ismapp.authentication.resetpassword.view.reset_view;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.model.ForgotPasswordResponse;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.presenter.ForgotPasswordPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.presenter.ForgotPasswordPresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.provider.RetrofitForgotPasswordProvider;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordView {
     EditText et_email;
     Button bt_send;
     ImageView imageView;
    // ProgressBar progressBar;
     Dialog dialog;
     boolean connected;
     String email;
     ForgotPasswordPresenter forgotPasswordPresenter_;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        initialise();



    }

    private void initialise() {
//        imageView = (ImageView)findViewById(R.id.img_reset);
        et_email = (EditText)findViewById(R.id.et_email);
        bt_send = (Button) findViewById(R.id.forgot_submit);
      //  progressBar = (ProgressBar)findViewById(R.id.progressBar);
        dialog = new Dialog(this);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
    }

    @Override
    public void showProgressBar(boolean check) {
        if(check == true){
           // progressBar.setVisibility(View.GONE);
        }
         else{
           // progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showOtpResponse(ForgotPasswordResponse forgotPasswordResponse) {
        if(forgotPasswordResponse.isSuccess()){
            //intent to reset page
            startActivity(new Intent(getBaseContext(), reset_view.class));
        }else{
            Toast.makeText(ForgotPasswordActivity.this, forgotPasswordResponse.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void proceed(View v) {
         email = et_email.getText().toString().trim();
       if(emailInvalid(email)){
            Toast.makeText(this, "ENTER CORRECT EMAIL ID!",
                    Toast.LENGTH_LONG).show();
        }
        else {
              forgotPasswordPresenter_ = new ForgotPasswordPresenterImpl(new RetrofitForgotPasswordProvider(), ForgotPasswordActivity.this);
              forgotPasswordPresenter_.getResponse(email);


            hideKeyboard();
        }
            }

    @Override
    public void checkConnection() {
        //check internet connection
        if(connected == false){
            //show dialog box;
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_coon);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules5 = (TextView) dialog.findViewById(R.id.rules5);
            btn.setText("Retry");
            rules5.setText("No internet connection.Please try again.");
            dialog.setTitle("Connectivity Failed");
            dialog.setCancelable(false);
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    forgotPasswordPresenter_ = new ForgotPasswordPresenterImpl(new RetrofitForgotPasswordProvider(), ForgotPasswordActivity.this);
                    forgotPasswordPresenter_.getResponse(email);

                    dialog.dismiss();
                }
            });
        }


    }

    @Override
    public void showError(String message) {
             Toast.makeText(this, message,Toast.LENGTH_SHORT);
    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
