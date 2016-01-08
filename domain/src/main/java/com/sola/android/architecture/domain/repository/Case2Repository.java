package com.sola.android.architecture.domain.repository;

import com.sola.android.architecture.domain.BaseResultDTO;
import com.sola.android.architecture.domain.Function1ResultDTO;
import com.sola.android.architecture.domain.Function4ResultDTO;

import java.util.Map;

import rx.Observable;

/**
 * 请不要纠结命名的问题，因为并没有什么特别好的业务需求支持
 *
 * author: Sola
 * 2016/1/8
 */
public interface Case2Repository {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Observable<Function1ResultDTO> function1(Map<String, String> params);

    Observable<BaseResultDTO> function2(Map<String, String> params);

    Observable<Function4ResultDTO> function4(Map<String, String> params);

}
