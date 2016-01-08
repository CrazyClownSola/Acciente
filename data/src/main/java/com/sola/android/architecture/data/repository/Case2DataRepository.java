package com.sola.android.architecture.data.repository;

import com.sola.android.architecture.domain.BaseResultDTO;
import com.sola.android.architecture.domain.Function1ResultDTO;
import com.sola.android.architecture.domain.Function4ResultDTO;
import com.sola.android.architecture.domain.repository.Case2Repository;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * author: Sola
 * 2016/1/8
 */
public class Case2DataRepository implements Case2Repository {

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
    public Case2DataRepository() {
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public Observable<Function1ResultDTO> function1(Map<String, String> params) {
        return null;
    }

    @Override
    public Observable<BaseResultDTO> function2(Map<String, String> params) {
        return null;
    }

    @Override
    public Observable<Function4ResultDTO> function4(Map<String, String> params) {
        return null;
    }
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
