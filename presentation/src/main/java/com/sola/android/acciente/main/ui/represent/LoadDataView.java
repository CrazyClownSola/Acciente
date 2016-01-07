package com.sola.android.acciente.main.ui.represent;

import android.content.Context;

/**
 * View的代理，代理处理界面逻辑，有继承方去实现
 * <p>
 * author: Sola
 * 2016/1/7
 */
public interface LoadDataView {
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
     * 等待框显示的处理
     */
    void showLoading();

    /**
     * 等待框隐藏的处理
     */
    void hideLoading();

    /**
     * 数据访问出错的处理
     *
     * @param message 出错提示
     */
    void showError(String message);

    /**
     * @return 主控件
     */
    Context getContext();

}
