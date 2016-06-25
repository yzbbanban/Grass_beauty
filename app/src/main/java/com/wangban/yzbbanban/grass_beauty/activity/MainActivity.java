package com.wangban.yzbbanban.grass_beauty.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.wangban.yzbbanban.grass_beauty.R;
import com.wangban.yzbbanban.grass_beauty.consat.Contast;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentAttract;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentAutodyne;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentBikini;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentModel;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentRecommend;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentSexy;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentStar;
import com.wangban.yzbbanban.grass_beauty.fragment.FragmentYoung;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Contast {
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragments;
    private TextView tvTitle;
    private int position;


    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();

        initData();

        //setListener();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);


    }

    private Object lastFragment;
    private int lastPosition;



    private void initData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentSexy());
        fragments.add(new FragmentBikini());
        fragments.add(new FragmentAttract());
        fragments.add(new FragmentModel());
        fragments.add(new FragmentAutodyne());
        fragments.add(new FragmentYoung());
        fragments.add(new FragmentStar());
        fragments.add(new FragmentRecommend());
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        selectFragment(0);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 性感美女sexy     比基尼bikini     美女诱惑attract     性感车模model
     * 美女自拍autodyne     清纯美女young     明星图片star     推荐图片recommend
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beauty_sexy) {
            //Toast.makeText(this, "new", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(NEW);
            position = 0;

            tvTitle.setText("性感美女");
        } else if (id == R.id.nav_beauty_bikini) {
            // Toast.makeText(this, "sexy", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(SEXY);
            position = 1;
            tvTitle.setText("比基尼");
        } else if (id == R.id.nav_beauty_attract) {

            //Toast.makeText(this, "young", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(YOUNG);
            position = 2;

            tvTitle.setText("美女诱惑");
        } else if (id == R.id.nav_beauty_model) {

            //Toast.makeText(this, "bu", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(PU);
            position = 3;

            tvTitle.setText("性感车模");
        } else if (id == R.id.nav_beauty_autodyne) {

            //   Toast.makeText(this, "leg", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(LEG);
            position = 4;

            tvTitle.setText("美女自拍");
        } else if (id == R.id.nav_beauty_young) {

            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(PORTRAIT);
            position = 5;
            tvTitle.setText("清纯美女");
        }else if (id == R.id.nav_beauty_star) {

            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(PORTRAIT);
            position = 6;
            tvTitle.setText("明星图片");
        } else if (id == R.id.nav_beauty_recommend) {

            //  Toast.makeText(this, "paper", Toast.LENGTH_SHORT).show();
//            MainPicture pic = new MainPicture(PAPER);
            position = 7;

            tvTitle.setText("推荐图片");
        }
        selectFragment(position);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private FrameLayout flContainer;
    private void selectFragment(int position) {
        //destort another fragment,falg set null
        if (lastFragment != null) {
            adapter.destroyItem(flContainer, lastPosition, lastFragment);
            lastFragment = null;
        }

        //set local frgment
        Object fragment = adapter.instantiateItem(flContainer, position);
        //
        adapter.setPrimaryItem(flContainer, 0, fragment);
        //
        adapter.finishUpdate(flContainer);
        //
        lastFragment = fragment;
        lastPosition = position;
    }


}
