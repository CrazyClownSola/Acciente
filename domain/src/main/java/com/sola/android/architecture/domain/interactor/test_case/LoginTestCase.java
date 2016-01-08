package com.sola.android.architecture.domain.interactor.test_case;

import com.sola.android.architecture.domain.BaseResultDTO;
import com.sola.android.architecture.domain.Function3ResultDTO;
import com.sola.android.architecture.domain.Function1ResultDTO;
import com.sola.android.architecture.domain.Function4ResultDTO;
import com.sola.android.architecture.domain.executor.PostExecutionThread;
import com.sola.android.architecture.domain.executor.ThreadExecutor;
import com.sola.android.architecture.domain.repository.Case1Repository;
import com.sola.android.architecture.domain.repository.Case2Repository;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

/**
 * author: Sola
 * 2016/1/8
 */
public class LoginTestCase extends Case2Base {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Case1Repository case1Repository;

    private final Case2Repository case2Repository;

    /**
     * // TODO: 2016/1/8 这里需要考虑个问题，由于这个Case当中并不是只有一次链接，所以这个回调的处理需要谨慎考虑
     */
    Subscription subscription = Subscriptions.empty();


    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    public LoginTestCase(ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread,
                            Case1Repository case1Repository,
                            Case2Repository case2Repository) {
        super(threadExecutor, postExecutionThread);
        this.case1Repository = case1Repository;
        this.case2Repository = case2Repository;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void function1(Map<String, String> params,
                          Action1<Function1ResultDTO> onNext,
                          Action1<Throwable> onError) {
        execute(case2Repository.function1(params), onNext, onError);
    }

    @Override
    public void function2(Map<String, String> params,
                          Action1<BaseResultDTO> onNext,
                          Action1<Throwable> onError) {
        execute(case2Repository.function2(params), onNext, onError);
    }

    @Override
    public void function3(Map<String, String> params,
                          Action1<Function3ResultDTO> onNext,
                          Action1<Throwable> onError) {
        execute(case1Repository.getUserInfo(params.get("userId")), onNext, onError);
    }

    @Override
    public void function4(Map<String, String> params,
                          Action1<Function4ResultDTO> onNext,
                          Action1<Throwable> onError) {
        execute(case2Repository.function4(params), onNext, onError);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
