package com.project.naveen.chess10x10;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.naveen.chess10x10.chess10x10camel.chess10x10camel;
import com.project.naveen.chess10x10.chess10x10empty.chess10x10;
import com.project.naveen.chess10x10.chess8x8.chess;

public class drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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

        if (id == R.id.tactic) {
            Intent intent = new Intent(drawer.this, tactic.class);
            startActivity(intent);

        } else if (id == R.id.Feed) {
            Intent intent = new Intent(drawer.this, tactic.class);
            startActivity(intent);

        } else if (id == R.id.theme) {
            Intent intent = new Intent(drawer.this, theme.class);
            startActivity(intent);

        } else if (id == R.id.demo) {
            Intent intent = new Intent(drawer.this, videos.class);
            startActivity(intent);

        } else if (id == R.id.buy) {
            Intent intent = new Intent(drawer.this, buyboard.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnChess8x8:
                intent = new Intent(this,chess.class);
                /*
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle extras = new Bundle();
                extras.putString("userId",userId);
                intent.putExtras(extras);
                */
                startActivity(intent);
                break;
            case R.id.btnChess10x10:
                intent = new Intent(this,chess10x10.class);
                startActivity(intent);
                break;

            case R.id.btnChess10x10Camel:
                intent = new Intent(this,chess10x10camel.class);
                startActivity(intent);
                break;
        }
    }
}
