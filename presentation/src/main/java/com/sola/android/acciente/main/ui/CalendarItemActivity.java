package com.sola.android.acciente.main.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sola.android.acciente.main.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * author: Sola
 * 2016/1/5
 */
@EActivity(R.layout.activity_calendar_item)
public class CalendarItemActivity extends BaseActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    CollapsingToolbarLayout id_collapsing_tool;

    @ViewById
    AppBarLayout app_bar_layout;

    @ViewById
    TextView id_collapsing_text;

    @Extra
    int itemId;

    int cacheScroll = 0;

    AppBarLayout.OnOffsetChangedListener mListener = (appBarLayout, i) -> {
        int toolHeight = ViewCompat.getMeasuredHeightAndState(id_collapsing_tool);
        int miniHeight = ViewCompat.getMinimumHeight(id_collapsing_tool);
        Log.d("Sola", "App Bar Offset [" + i + "][" + toolHeight + "][" + miniHeight + "]");
        if (i == cacheScroll)
            return;
        else
            cacheScroll = i;
        if (i < (miniHeight - toolHeight + 20)) {
            id_tool_bar.setTitle("");
            id_collapsing_text.setVisibility(View.VISIBLE);
        } else {
            id_tool_bar.setTitle("av121240");
            id_collapsing_text.setVisibility(View.GONE);
        }
    };

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
    protected void onPause() {
        super.onPause();
        app_bar_layout.removeOnOffsetChangedListener(mListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_test:
//                Toast.makeText(this, "menu_test click", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_help:
//                Toast.makeText(this, "menu_help click", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_settings:
//                Toast.makeText(this, "menu_settings click", Toast.LENGTH_SHORT).show();
//                return true; // true 代表这个Id相关的处理由customer自定义
            case android.R.id.home:
                onBackPressed();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @AfterViews
    public void afterViews() {
        setSupportActionBar(id_tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        app_bar_layout.addOnOffsetChangedListener(mListener);
    }

    @Click
    public void id_show_dialog() {

    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
