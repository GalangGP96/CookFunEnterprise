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

import com.example.asus.cookfun.Fragment.FragmentAdd;
import com.example.asus.cookfun.Fragment.FragmentFavorite;
import com.example.asus.cookfun.Fragment.FragmentHome;
import com.example.asus.cookfun.Fragment.FragmentProfile;

public class Add extends AppCompatActivity {

    ImageView imgHome1, imgLove1, imgAdd1, imgProfil1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

//        imgHome1 = findViewById(R.id.imgHome1);
//        imgLove1 = findViewById(R.id.imgLove1);
//        imgAdd1 = findViewById(R.id.imgAdd1);
//        imgProfil1 = findViewById(R.id.imgProfil1);

//        imgHome1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent home = new Intent(Add.this, Home.class);
//                startActivity(home);
//            }
//        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navbar_botom2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new FragmentAdd()).commit();
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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,selectedFragment).commit();

            return true;
        }
    };
}
