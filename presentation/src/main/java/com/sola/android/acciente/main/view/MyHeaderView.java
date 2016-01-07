package com.sola.android.acciente.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sola.module.recycle.fix_container.RecyclerContainerBase;
import com.sola.module.recycle.fix_container.tools.IPullToRefreshUIHandler;

/**
 * author: Sola
 * 2015/11/4
 */
public class MyHeaderView extends LinearLayout implements IPullToRefreshUIHandler {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================
    public MyHeaderView(Context context) {
        super(context);
    }

    public MyHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public void onUIReset(RecyclerContainerBase recyclerContainerBase) {

    }

    @Override
    public void onUIRefreshPrepare(RecyclerContainerBase recyclerContainerBase) {

    }

    @Override
    public void onUIRefreshBegin(RecyclerContainerBase recyclerContainerBase) {

    }

    @Override
    public void onUIRefreshComplete(RecyclerContainerBase recyclerContainerBase) {

    }

    @Override
    public void onUIPositionChange(RecyclerContainerBase recyclerContainerBase, boolean b, byte b1, int i, int i1, int i2) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
