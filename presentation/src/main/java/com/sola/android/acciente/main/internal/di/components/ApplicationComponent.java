package com.sola.android.acciente.main.internal.di.components;

import android.content.Context;

import com.sola.android.acciente.main.internal.di.modules.ApplicationModule;
import com.sola.android.acciente.main.ui.BaseActivity;
import com.sola.android.architecture.domain.executor.PostExecutionThread;
import com.sola.android.architecture.domain.executor.ThreadExecutor;
import com.sola.android.architecture.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: Sola
 * 2015/11/4
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 向Application的组件注入自身的方法
     *
     * @param activity 注入方,注意这里传参调整为BaseActivity的原因是为了将Navigator注入组件当中去
     */
    void inject(BaseActivity activity);

    /**
     * @return 返回Application的实例
     */
    Context getContext();

    /**
     * @return 返回线程池的实例
     */
    ThreadExecutor getThreadExecutor();

    /**
     * @return 返回UI主线程的实例
     */
    PostExecutionThread getPostExecutionThread();

    /**
     * 这里是个很有趣，也非常值得研究的地方。
     * 如果你把这段话注释掉，然后编译，观察以下DaggerApplicationComponent类
     * 然后在把注释放开，再编译，如果你能了解到这两者之间的区别和原因，@Inject这部分基本就能明确了
     *
     * @return 返回仓储
     */
    UserRepository userRepository();

}
