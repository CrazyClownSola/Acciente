package com.sola.android.acciente.main.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sola.android.acciente.main.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * author: Sola
 * 2015/11/4
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    DrawerLayout id_drawer_layout;

    @ViewById
    NavigationView id_navigation;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_game:
                Toast.makeText(this, "menu_test click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                Toast.makeText(this, "menu_help click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_download:
                Toast.makeText(this, "menu_settings click", Toast.LENGTH_SHORT).show();
                return true; // true 代表这个Id相关的处理由customer自定义
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @AfterViews
    public void afterViews() {
        id_tool_bar.setTitle("");
        setSupportActionBar(id_tool_bar);
        id_navigation.setNavigationItemSelectedListener(menuItem -> {
            id_drawer_layout.closeDrawers();
            return false;
        });
    }

    @Click
    public void id_tool_bar() {
        navigator.navigatorToItemActivity(this, 1);
    }

    @Click
    public void id_text_user_name() {
        Log.d("Sola", "id_text_user_name Click");
        if (id_drawer_layout.isDrawerOpen(GravityCompat.START))
            id_drawer_layout.closeDrawer(GravityCompat.START);
        else
            id_drawer_layout.openDrawer(GravityCompat.START);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
