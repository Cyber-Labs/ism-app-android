package ismapp.iitism.cyberlabs.com.ismapp.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.authentication.login.view.LoginViewImp;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubListListFragment;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view.EventListFragment;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ApiClient;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view.NewsList;
import ismapp.iitism.cyberlabs.com.ismapp.viewfragments.AboutFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Drawable drawable;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiClient.instantiateRetrofitWithAccessToken(this,new SharedPrefs(this).getAccessToken());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.back, this.getTheme());

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       setFragment(new NewsList(),true); addActionBar(); addTitletoBar("Feeds");

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.feed);

        //Debug
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count=getSupportFragmentManager().getBackStackEntryCount();
            if(count==0){
                if(getSupportFragmentManager().findFragmentByTag("Base")==null)
                { setFragment(new NewsList(),true); addActionBar(); addTitletoBar("Feeds");
                  navigationView.setCheckedItem(R.id.feed);
                }
           else super.onBackPressed();}
            else getSupportFragmentManager().popBackStack();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.feed) {

            setFragment(new NewsList(),true); addActionBar(); addTitletoBar("Feeds");
        } else if (id == R.id.clubs) {
            setFragment(new ClubListListFragment(),false);addActionBar(); addTitletoBar("Clubs");
        } else if (id == R.id.events) {
            setFragment(new EventListFragment(),false);addActionBar(); addTitletoBar("Events");
        }
        else if (id == R.id.nav_about) {
            setFragment(new AboutFragment(),false); addActionBar(); addTitletoBar("About");
        }
        else if(id == R.id.nav_logout){
            SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
            sharedPrefs.clear();
            startActivity(new Intent(getApplicationContext(), LoginViewImp.class));
            finish();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment,boolean base) {
        String tag;
        if (fragment != null)
        {  if(base)
               tag="Base";
                       else
                           tag="Not Base";

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_contaner, fragment,tag)
                    .commit();}
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_contaner, fragment)
                    .commitAllowingStateLoss();

    }


    public void addActionBar()
    {   toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    public void addTitletoBar(String title)
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
    public void changeActionBar()
    {
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(drawable);
        drawer.removeDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setToolbarNavigationClickListener(v -> onBackPressed());

    }


}
