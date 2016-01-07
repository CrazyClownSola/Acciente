package com.sola.android.acciente.main.internal.di.modules;

import android.app.Activity;

import com.sola.android.acciente.main.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * author: Sola
 * 2015/11/4
 */
@Module
public class ActivityModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Activity mActivity;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
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

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
