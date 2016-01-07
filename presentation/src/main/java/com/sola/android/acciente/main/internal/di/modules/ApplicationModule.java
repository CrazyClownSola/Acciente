package com.sola.android.acciente.main.internal.di.modules;

import android.app.Application;
import android.content.Context;

import com.sola.android.acciente.main.executor.JobExecutor;
import com.sola.android.acciente.main.executor.UIThread;
import com.sola.android.acciente.main.navigator.Navigator;
import com.sola.android.architecture.data.repository.UserDataRepository;
import com.sola.android.architecture.domain.executor.PostExecutionThread;
import com.sola.android.architecture.domain.executor.ThreadExecutor;
import com.sola.android.architecture.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: Sola
 * 2015/11/4
 */
@Module
public class ApplicationModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Application mApplication;


    // ===========================================================
    // Constructors
    // ===========================================================

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @return 返回Application的实例
     */
    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }

    /**
     * 向Dagger声明，将JobExecutor的实例绑定到ThreadExecutor的调用方去
     *
     * @param executor 官方文档上会有这个带参的存在，但是尝试了下把参数去掉，直接返回实例，也能达到目的，意义不明
     * @return 线程池对象
     */
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    /**
     * 向Dagger声明，将UIThread的实例绑定到PostExecutionThread的调用方上去
     *
     * @param mainThread 改变指向，将PostExecutionThread的指向转变为UIThread
     * @return 返回实例
     */
    @Provides
    @Singleton
    PostExecutionThread provideExecutionThread(UIThread mainThread) {
        return mainThread;
    }

    /**
     * @return 返回Navigator导航的实例
     */
    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    /**
     * 这是段非常精髓的代码，就这样一句话，就将domain和data层联系了起来，让原本只和domain有联系的presentation层
     * 间接指向了data层，并且进行数据交互。
     *
     * @param userDataRepository data层仓储
     * @return 返回domain的仓储
     */
    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
