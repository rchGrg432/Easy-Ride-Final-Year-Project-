package com.richa.easyride.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.richa.easyride.R;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.richa.easyride.home.fragments.CartFragment;
import com.richa.easyride.home.fragments.CategoryFragment;
import com.richa.easyride.home.fragments.home.HomeFragment;
import com.richa.easyride.home.fragments.SettingFragment;
import com.richa.easyride.home.fragments.RentalFragment;

public class MainActivity extends AppCompatActivity {
    //Declaration variable
    MeowBottomNavigation bottomNavigation;
    HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.show(1, true);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.category));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.bicycle));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_baseline_dehaze_24));



        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // Initialize fragment
                Fragment fragment = null;

                //Check condition
                switch (item.getId()) {
                    case 1:
                        // when id is 1
                        //Initialize rental fragment
                        fragment = new HomeFragment();
                        break;

//                    case 2:
//                        //When id is 2
//                        //Initialize home fragment
//                        fragment = new CategoryFragment();
//                        break;

                    case 3:
                        //When id is 2
                        //Initialize home fragment
                        fragment = new RentalFragment();
                        break;

//                    case 4:
//                        //when id is 3
//                        //initialize about wish list fragment
//                        fragment = new CartFragment();
//                        break;

                    case 5:
                        fragment = new SettingFragment();
                        break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });



        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display toast
//                Toast.makeText(getApplicationContext()
//                        , "You Clicked" + item.getId()
//                        , Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display toast
//                Toast.makeText(getApplicationContext()
//                        , "You Reselected " + item.getId()
//                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //Replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bottomFL, fragment)
                .commit();
    }
}