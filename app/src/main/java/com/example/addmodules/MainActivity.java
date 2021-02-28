package com.example.addmodules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import Fragment.Computing_Fragment;
import Fragment.EngeeneringFragment;
import Fragment.HealthAndAppliedScienceFragment;
import Fragment.HumanScienceFragment;
import Fragment.ManagementScienceFragment;
import Fragment.Natural_ResourcesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.course_one:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Computing_Fragment()).commit();
                break;
            case R.id.course_two:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EngeeneringFragment()).commit();
                break;
            case R.id.course_three:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HealthAndAppliedScienceFragment()).commit();
                break;
            case R.id.course_four:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HumanScienceFragment()).commit();
                break;
            case R.id.course_five:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ManagementScienceFragment()).commit();
            case R.id.course_six:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Natural_ResourcesFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
