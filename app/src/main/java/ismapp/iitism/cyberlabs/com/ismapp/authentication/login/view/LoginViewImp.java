package ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appus.splash.Splash;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.forgotpassword.view.ForgotPasswordActivity;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.model.LoginModel;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.presenter.LoginPresenter;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.presenter.LoginPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.provider.LoginProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.signup.View.SignUpViewImp;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class LoginViewImp extends AppCompatActivity implements LoginView {
    SharedPrefs sharedPrefs;
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
    AlertDialog alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
        splash.setBackgroundImage(getResources().getDrawable(R.drawable.splash));
        splash.setSplashImage(getResources().getDrawable(R.drawable.logo));
       splash.perform();
         sharedPrefs  = new SharedPrefs(this);
         if(sharedPrefs.getLogin())
         {
             startActivity(new Intent(LoginViewImp.this,MainActivity.class));
             finish();
         }
        alertDialog= new AlertDialog.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        final LoginPresenter loginPresenter=new LoginPresenterImp(this,new LoginProviderImp());
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginViewImp.this,SignUpViewImp.class));
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginViewImp.this, ForgotPasswordActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=email.getText().toString().trim();
                String p=pass.getText().toString();

                if(e.isEmpty()||p.isEmpty())

                    Toast.makeText(getBaseContext(),"Enter the inputs",Toast.LENGTH_LONG).show();
                 else
                     loginPresenter.getLoginResponse(e,p);
            }
        });
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show)
            alertDialog.show();
        else alertDialog.dismiss();

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
