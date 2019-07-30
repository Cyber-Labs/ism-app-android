package ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginViewImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Model.VerifyOtpModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Presenter.VerifyOtpPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Presenter.VerifyOtpPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.verifyotp.Provider.VerifyOtpProviderImp;

public class VerifyOtpActivity extends AppCompatActivity implements VerifyOtpActivityInterface {
    private String email;
    @BindView(R.id.verify_otp_email)
    TextView emailTv;
    @BindView(R.id.verify_otp_otp)
    EditText otp;
    @BindView(R.id.btn_verify_otp)
    Button button;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_otp);
        ButterKnife.bind(this);
        setTitle("Verify OTP");
        Intent i=getIntent();
        email=i.getStringExtra("email");
        Log.e("email", email );
        emailTv.setText(email);
        final VerifyOtpPresenter verifyOtpPresenter=new VerifyOtpPresenterImp(this,new VerifyOtpProviderImp());
        emailTv.setText(email);
        alertDialog= new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        button.setOnClickListener(v -> {
            if(otp.getText().toString().isEmpty())
                Toast.makeText(getApplicationContext(),"Enter the Otp",Toast.LENGTH_LONG).show();
            else
            {
             verifyOtpPresenter.getOtpVerificationResponse(email,Integer.parseInt(otp.getText().toString().trim()));

            }

        });
    }

    @Override
    public void showProgressBar(boolean show) {

        if(show)
            alertDialog.show();
        else
            alertDialog.dismiss();
    }

    @Override
    public void setIntent(VerifyOtpModel verifyOtpModel) {
        if( verifyOtpModel.getSuccess()) {
            Intent i = new Intent(this, LoginViewImp.class);
            Toast.makeText(this, "Your account is Created, activity_login to continue", Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }
        else
            Toast.makeText(this,verifyOtpModel.getMessage(),Toast.LENGTH_SHORT).show();


    }
}
