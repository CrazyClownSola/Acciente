package com.sola.android.acciente.main.internal.di.components;

import com.sola.android.acciente.main.internal.di.PerActivity;
import com.sola.android.acciente.main.internal.di.modules.ActivityModule;
import com.sola.android.acciente.main.internal.di.modules.SeedModule;
import com.sola.android.acciente.main.ui.fragments.DirectSeedingFragment;

import dagger.Component;

/**
 * author: Sola
 * 2016/1/7
 */
@PerActivity // 依赖于Activity的生命周期
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, SeedModule.class})
public interface SeedComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    void inject(DirectSeedingFragment fragment);
}
