package com.sola.android.acciente.main.presenter;

/**
 * 个人理解为代理的意思
 * 代替Fragment去处理自身的数据逻辑
 * 完成数据与Fragment之间的松耦合
 *
 * author: Sola
 * 2016/1/7
 */
public interface Presenter {
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
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void onResume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void onPause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void onDestroy();
}
