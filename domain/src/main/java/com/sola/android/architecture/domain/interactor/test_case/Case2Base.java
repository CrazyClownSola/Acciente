package com.sola.android.architecture.domain.interactor.test_case;

import com.sola.android.architecture.domain.BaseResultDTO;
import com.sola.android.architecture.domain.Function3ResultDTO;
import com.sola.android.architecture.domain.Function1ResultDTO;
import com.sola.android.architecture.domain.Function4ResultDTO;
import com.sola.android.architecture.domain.executor.PostExecutionThread;
import com.sola.android.architecture.domain.executor.ThreadExecutor;
import com.sola.android.architecture.domain.interactor.ComplexConnectionCase;

import java.util.Map;

import rx.functions.Action1;

/**
 * 这里预设考虑有这么一个业务逻辑
 * 就简单的登录做例子
 * 这个Case中包含这样几个场景：
 * (如果是真实的项目，这部分推荐多抽象一些方法在里面，
 * 因为这不是最终的实现类)
 * 1、登录
 * 2、登出
 * 3、获取用户信息
 * 4、注册
 * 然后假设实现这些场景需要对多个服务进行访问。转换到代码级别上就是对多个Repository进行切换
 * <p>
 * author: Sola
 * 2016/1/8
 */
public abstract class Case2Base extends ComplexConnectionCase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    protected Case2Base(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    /**
     * 方法1
     *
     * @param params  登录所需的参数
     * @param onNext  回调成功
     * @param onError 回调失败
     */
    public abstract void function1(Map<String, String> params,
                                   Action1<Function1ResultDTO> onNext,
                                   Action1<Throwable> onError);

    /**
     * 方法2
     *
     * @param params  访问所需的参数
     * @param onNext  回调成功
     * @param onError 回调失败
     */
    public abstract void function2(Map<String, String> params,
                                   Action1<BaseResultDTO> onNext,
                                   Action1<Throwable> onError);

    /**
     * 方法3
     *
     * @param params  访问所需的参数
     * @param onNext  回调成功
     * @param onError 回调失败
     */
    public abstract void function3(Map<String, String> params,
                                   Action1<Function3ResultDTO> onNext,
                                   Action1<Throwable> onError);

    /**
     * 方法4
     *
     * @param params  访问所需的参数
     * @param onNext  回调成功
     * @param onError 回调失败
     */
    public abstract void function4(Map<String, String> params,
                                   Action1<Function4ResultDTO> onNext,
                                   Action1<Throwable> onError);

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
