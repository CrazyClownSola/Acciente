package com.sola.android.acciente.main.navigator;

import android.content.Context;
import android.content.Intent;

import com.sola.android.acciente.main.ui.CalendarItemActivity_;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: Sola
 * 2015/11/4
 */
@Singleton
public class Navigator {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    public Navigator() {

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void navigatorToItemActivity(Context context, int itemId) {
        Intent intent = new Intent();
        intent.setClass(context, CalendarItemActivity_.class);
        intent.putExtra("itemId", itemId);
        context.startActivity(intent);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
