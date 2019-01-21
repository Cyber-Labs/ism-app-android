package ismapp.iitism.cyberlabs.com.ismapp.HomeActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.appus.splash.Splash;

import ismapp.iitism.cyberlabs.com.ismapp.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
        splash.setBackgroundImage(getResources().getDrawable(R.drawable.splash));
        splash.setSplashImage(getResources().getDrawable(R.drawable.logo));
        splash.perform();
    }
}
