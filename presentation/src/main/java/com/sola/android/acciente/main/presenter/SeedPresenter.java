package com.sola.android.acciente.main.presenter;

import com.sola.android.acciente.main.internal.di.PerActivity;
import com.sola.android.architecture.domain.BannerResultDTO;
import com.sola.android.architecture.domain.interactor.ConnectionCase;
import com.sola.android.architecture.domain.interactor.test_case.Case2Base;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * author: Sola
 * 2016/1/7
 */
@PerActivity
public class SeedPresenter implements Presenter {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final ConnectionCase<BannerResultDTO> connectionCase;

    private final Case2Base case2Base;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    public SeedPresenter(@Named("imageList") ConnectionCase<BannerResultDTO> getImageList,
                         Case2Base case2Base) {
        this.connectionCase = getImageList;
        this.case2Base = case2Base;
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
    // ===========================================================
    // Methods
    // ===========================================================

    public void initialize() {
        loadImageList();
    }

    private void loadImageList() {
        connectionCase.execute(bannerResultDTO -> {
        }, Throwable::printStackTrace);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
