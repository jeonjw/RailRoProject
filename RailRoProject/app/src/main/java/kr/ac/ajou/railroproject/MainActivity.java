package kr.ac.ajou.railroproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        toolbar.setTitle("TEST");
        setSupportActionBar(toolbar);

        final FragmentManager fm = getSupportFragmentManager();
        final Fragment courseFragment = new CourseFragment();
        fm.beginTransaction().add(R.id.main_container, courseFragment).commit();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_course:
                        fragment = new CourseFragment();
                        break;
                    case R.id.nav_info:
                        fragment = new TravelInfoTabFragment();
                        break;
                    case R.id.nav_community:
                        fragment = new CommunityFragment();
                        break;
                    case R.id.nav_my_info:
                        fragment = new MyInfoFragment();
                        break;
                }

                fm.beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });



    }


}
