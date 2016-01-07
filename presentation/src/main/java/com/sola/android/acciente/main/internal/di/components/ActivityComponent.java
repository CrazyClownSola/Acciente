package com.sola.android.acciente.main.internal.di.components;

import android.app.Activity;

import com.sola.android.acciente.main.internal.di.PerActivity;
import com.sola.android.acciente.main.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Activity的基础组件
 *
 * author: Sola
 * 2015/11/4
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Activity getActivity();

}
