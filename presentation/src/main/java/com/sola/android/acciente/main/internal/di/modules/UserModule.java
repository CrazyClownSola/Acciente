package com.sola.android.acciente.main.internal.di.modules;

import com.sola.android.acciente.main.internal.di.PerActivity;
import com.sola.android.architecture.domain.interactor.test_case.Case2Base;
import com.sola.android.architecture.domain.interactor.test_case.LoginTestCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: Sola
 * 2016/1/8
 */
@Module
public class UserModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

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
    Case2Base provideUserCase(LoginTestCase loginTestCase) {
        return loginTestCase;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
