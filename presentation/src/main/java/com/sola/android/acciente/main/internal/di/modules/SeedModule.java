package com.sola.android.acciente.main.internal.di.modules;

import com.sola.android.acciente.main.internal.di.PerActivity;
import com.sola.android.architecture.domain.BannerResultDTO;
import com.sola.android.architecture.domain.interactor.ConnectionCase;
import com.sola.android.architecture.domain.interactor.GetImageList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * author: Sola
 * 2016/1/7
 */
@Module
public class SeedModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /*****
     * 可以考虑构造带点参数，一些可能用到的
     *****/

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
    @Named("imageList")
    ConnectionCase<BannerResultDTO> provideGetImageList(GetImageList imageList) {
        return imageList;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
