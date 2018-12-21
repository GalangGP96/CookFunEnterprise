package com.example.asus.cookfun;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.example.asus.cookfun.Fragment.*;

public class Home extends AppCompatActivity {

//    ImageView imgHome, imgLove, imgAdd, imgProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navbar_botom);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();

//        imgHome = findViewById(R.id.imgHome);
//        imgLove = findViewById(R.id.imgLove);
//        imgAdd = findViewById(R.id.imgAdd);
//        imgProfil = findViewById(R.id.imgProfil);

//        imgAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent add = new Intent(Home.this, Add.class);
//                startActivity(add);
//            }
//        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new FragmentHome();
                    break;
                case R.id.navigation_favorite:
                    selectedFragment = new FragmentFavorite();
                    break;
                case R.id.navigation_add:
                    selectedFragment = new FragmentAdd();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new FragmentProfile();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };
}
