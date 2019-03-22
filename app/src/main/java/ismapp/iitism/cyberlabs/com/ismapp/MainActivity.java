package ismapp.iitism.cyberlabs.com.ismapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubListListFragment;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view.EventListFragment;
import ismapp.iitism.cyberlabs.com.ismapp.events.view.EventsFrag;
import ismapp.iitism.cyberlabs.com.ismapp.feed.view.FeedFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Drawable drawable;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.back, this.getTheme());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_contaner, new FeedFrag())
                .commit();
        getSupportActionBar().setTitle("Feeds");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        addActionBar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count=getSupportFragmentManager().getBackStackEntryCount();
            if(count==0){
            super.onBackPressed();}
            else getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.feed) {

            setFragment(new FeedFrag()); addActionBar(); addTitletoBar("Feeds");
        } else if (id == R.id.clubs) {
            setFragment(new ClubListListFragment());addActionBar(); addTitletoBar("Clubs");
        } else if (id == R.id.events) {
            setFragment(new EventListFragment());addActionBar(); addTitletoBar("Events");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_contaner, fragment)
                    .commit();
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_contaner, fragment)
                    .commit();

    }
    public void addFragmentWithSharedElement(Fragment fragment,View view) {
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_contaner, fragment).addSharedElement(view, ViewCompat.getTransitionName(view))
                    .commit();

    }


    public void addActionBar()
    {   toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    public void addTitletoBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }
    public void changeActionBar()
    {
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(drawable);
        drawer.removeDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

    }


}
