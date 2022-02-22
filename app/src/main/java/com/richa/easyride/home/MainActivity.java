package com.richa.easyride.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.richa.easyride.R;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.richa.easyride.home.fragments.home.HomeFragment;
import com.richa.easyride.home.fragments.ProfileFragment;
import com.richa.easyride.home.fragments.RentalFragment;
import com.richa.easyride.home.fragments.WishFragment;

public class MainActivity extends AppCompatActivity {
    //Declaration variable
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_nav);


        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.bicycle));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_heart));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profile));

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
                    case 2:
                        //When id is 2
                        //Initialize home fragment
                        fragment = new RentalFragment();
                        break;
                    case 3:
                        //when id is 3
                        //initialize about wish list fragment
                        fragment = new WishFragment();
                        break;
                    case 4:
                        fragment = new ProfileFragment();
                        break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });

        //Set notification count
        bottomNavigation.setCount(1, "10");
        //Set home fragment initially selected
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display toast
                Toast.makeText(getApplicationContext()
                        , "You Clicked" + item.getId()
                        , Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display toast
                Toast.makeText(getApplicationContext()
                        , "You Reselected " + item.getId()
                        , Toast.LENGTH_SHORT).show();
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