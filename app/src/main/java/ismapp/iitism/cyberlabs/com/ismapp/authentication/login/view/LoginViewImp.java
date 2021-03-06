package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import ismapp.iitism.cyberlabs.com.ismapp.activities.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotPassword.view.ForgotPasswordActivity;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.presenter.LoginPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.presenter.LoginPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.provider.LoginProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.view.SignUpActivity;
import ismapp.iitism.cyberlabs.com.ismapp.helper.Guest;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class LoginViewImp extends AppCompatActivity implements LoginView {
    private SharedPrefs sharedPrefs;
    @BindView(R.id.login_email)
    EditText email;
    @BindView(R.id.login_pass)
    EditText pass;
    @BindView(R.id.btn_login_login)
    Button login;
    @BindView(R.id.btn_login_signup)
    Button signUp;
    @BindView(R.id.btn_login_forgot_pass)
    TextView forgot;
    @BindView(R.id.btn_login_guest)
    TextView guestLogin;
    private AlertDialog alertDialog;
    private Boolean exit = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
//        splash.setBackgroundImage(getResources().getDrawable(R.drawable.splash));
//        splash.setSplashImage(getResources().getDrawable(R.drawable.institutelogo));
//        splash.perform();
         sharedPrefs  = new SharedPrefs(this);
         if(sharedPrefs.getLogin())
         {
             startActivity(new Intent(LoginViewImp.this,MainActivity.class));
             finish();
         }
        alertDialog= new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        final LoginPresenter loginPresenter=new LoginPresenterImp(this,new LoginProviderImp());
        signUp.setOnClickListener(v -> startActivity(new Intent(LoginViewImp.this, SignUpActivity.class)));
        forgot.setOnClickListener(v -> startActivity(new Intent(LoginViewImp.this, ForgotPasswordActivity.class)));
        login.setOnClickListener(v -> {
            String e=email.getText().toString().trim();
            String p=pass.getText().toString();

            if(e.isEmpty()||p.isEmpty())

                Toast.makeText(getBaseContext(),"Enter the inputs",Toast.LENGTH_LONG).show();
             else
                 loginPresenter.getLoginResponse(e,p);
        });
        guestLogin.setOnClickListener(v -> {
            String e= Guest.guestEmail;
            String p=Guest.guestPassword;
            loginPresenter.getLoginResponse(e,p);
        });
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show)
            alertDialog.show();
        else alertDialog.dismiss();

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public void setIntent(LoginModel loginModel) {
        //Inent and Save Data to shared preferences
        if(loginModel.isSuccess()){
            sharedPrefs.setAccessToken(loginModel.getAccess_token());
            sharedPrefs.setLogin(true);
            startActivity(new Intent(LoginViewImp.this,MainActivity.class));
            finish();

        }

       else
           Toast.makeText(this,loginModel.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
