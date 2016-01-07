package com.sola.android.acciente.main;

import android.app.Application;

import com.sola.android.acciente.main.internal.di.components.ApplicationComponent;
import com.sola.android.acciente.main.internal.di.components.DaggerApplicationComponent;
import com.sola.android.acciente.main.internal.di.modules.ApplicationModule;


/**
 * author: Sola
 * 2015/11/3
 */
public class MainApplication extends Application {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    ApplicationComponent mComponent;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public ApplicationComponent getApplicationComponent() {
        return mComponent;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
