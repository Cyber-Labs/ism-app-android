package ismapp.iitism.cyberlabs.com.ismapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginViewImp;

public class SplashActivity extends Activity {
    private static final int splashTimeOut=4000;
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
