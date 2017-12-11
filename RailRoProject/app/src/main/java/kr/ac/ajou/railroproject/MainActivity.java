package kr.ac.ajou.railroproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

import kr.ac.ajou.railroproject.Board.BoardTabFragment;
import kr.ac.ajou.railroproject.Retrofit.TourApiReader;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TourApiReader().start();

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTextView = toolbar.findViewById(R.id.toolbar_title_text_view);

        toolbarTextView.setText("RAILRONET");
        setSupportActionBar(toolbar);

        final FragmentManager fm = getSupportFragmentManager();
        final Fragment courseFragment = new CourseFragment();
        fm.beginTransaction().add(R.id.main_container, courseFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_navigation);
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
                        fragment = new BoardTabFragment();
                        break;
                    case R.id.nav_my_info:
                        fragment = new MyInfoFragment();
                        break;
                    default:
                        break;
                }

                fm.beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });


    }


}
