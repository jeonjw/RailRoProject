package kr.ac.ajou.railroproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.Board.BoardTabFragment;
<<<<<<< HEAD
import kr.ac.ajou.railroproject.MyInfo.MyInfoFragment;
=======
import kr.ac.ajou.railroproject.Course.CourseFragment;
import kr.ac.ajou.railroproject.Course.PlaceInputDialog;
>>>>>>> 741fca127de6c356e117d8e838a336af00d47d50
import kr.ac.ajou.railroproject.TourInfo.TravelInfoTabFragment;

public class MainActivity extends AppCompatActivity {
    private List<String> selectedPhotos;
    private PhotoAdapter photoAdapter;
    private Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedPhotos = new ArrayList<String>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTextView = toolbar.findViewById(R.id.toolbar_title_text_view);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PlaceInputDialog.changeData(requestCode, resultCode, data);
    }
}
