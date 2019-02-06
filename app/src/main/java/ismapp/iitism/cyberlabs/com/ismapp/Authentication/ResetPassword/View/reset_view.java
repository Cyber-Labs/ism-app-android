package ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.View;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Model.NewPassword;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Presenter.reset_presenter_impl;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Presenter.reset_presenter_interface;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.ResetPassword.Provider.Retrofit_reset_imple;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public class reset_view extends AppCompatActivity implements reset_interface {

    EditText ed_email,ed_password,ed_confirm_password,ed_otp;
    Button submit;
    ProgressDialog progressDialog;
    private String email,otp,password,newpassword;
    Dialog dialog;

     boolean connected;
     reset_presenter_interface reset_presenter_interface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset);
        initialised();
    }

    private void initialised() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        ed_email = (EditText)findViewById(R.id.rs_email);
        ed_password = (EditText)findViewById(R.id.rs_ps);
        //ed_confirm_password = (EditText)findViewById(R.id.rs_cps);
        ed_otp = (EditText)findViewById(R.id.rs_otp);
        submit = (Button)findViewById(R.id.rs_submit);

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
    public void showProgressbar(boolean check) {
        if(check){
          progressDialog.show();
        }else{
           progressDialog.dismiss();
        }

    }

    @Override
    public void checkConnection() {
     if(connected == false){
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
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 reset_presenter_interface = new reset_presenter_impl(reset_view.this,new Retrofit_reset_imple());
                 reset_presenter_interface.sendResponse(email,password, Integer.parseInt(otp));
                 dialog.dismiss();
             }
         });

     }
    }

   public void proceed_info(){

             email = ed_email.getText().toString().trim();
             password = ed_password.getText().toString();
            // newpassword = ed_confirm_password.getText().toString().trim();
             otp = ed_otp.getText().toString().trim();
            int Otp = Integer.parseInt(otp);
            if(email.isEmpty() || password.isEmpty() || otp.isEmpty()){
                Toast.makeText(this,"All Fields Are required",Toast.LENGTH_LONG);
            }
            else if(password.length()<6)
                Toast.makeText(this,"Password Must Contain min 6 letters!!!",Toast.LENGTH_SHORT).show();

            else{
                reset_presenter_interface = new reset_presenter_impl(this,new Retrofit_reset_imple());
                reset_presenter_interface.sendResponse(email,password,Otp);
            }
        }




    @Override
    public void showResponse(NewPassword newPassword) {
        if(newPassword.isSuccess()){
            //intent to login;
        }
        else{
            Toast.makeText(this,newPassword.getMessage().toString(),Toast.LENGTH_LONG);
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
