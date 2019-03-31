package ismapp.iitism.cyberlabs.com.ismapp.splashactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginViewImp;

public class SplashActivity extends Activity {
    private static int splashTimeOut=4000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, LoginViewImp.class);
            startActivity(i);
            finish();

        },splashTimeOut);
        Animation splashanim = AnimationUtils.loadAnimation(this,R.anim.fadeinanim);
        findViewById(R.id.iv_splash_logo).startAnimation(splashanim);
        findViewById(R.id.tv_splash_text).startAnimation(splashanim);
    }
}
