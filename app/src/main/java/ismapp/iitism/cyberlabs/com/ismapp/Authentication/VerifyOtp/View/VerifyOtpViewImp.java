package ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Model.VerifyOtpModel;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Presenter.VerifyOtpPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Presenter.VerifyOtpPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.Authentication.VerifyOtp.Provider.VerifyOtpProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.R;

public class VerifyOtpViewImp extends AppCompatActivity implements VerifiOtpView {
    String email;
    @BindView(R.id.verify_otp_email)
    TextView emailTv;
    @BindView(R.id.verify_otp_otp)
    EditText otp;
    @BindView(R.id.verify_otp_button)
    Button button;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_otp);
        ButterKnife.bind(this);
        Intent i=getIntent();
        email=i.getStringExtra("email");
        Log.e("email", email );
        emailTv.setText(email);
        final VerifyOtpPresenter verifyOtpPresenter=new VerifyOtpPresenterImp(this,new VerifyOtpProviderImp());
        emailTv.setText(email);
        alertDialog= new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Enter the Otp",Toast.LENGTH_LONG).show();
                else
                {
                 verifyOtpPresenter.getOtpVerificationResponse(email,Integer.parseInt(otp.getText().toString().trim()));

                }

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


    }
}
