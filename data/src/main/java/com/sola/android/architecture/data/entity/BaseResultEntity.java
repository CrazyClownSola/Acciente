package com.sola.android.architecture.data.entity;

/**
 * author: Sola
 * 2016/1/8
 */
public class BaseResultEntity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /**
     * 响应的代码Code
     */
    String code;

    /**
     * 服务响应的信息
     */
    String msg;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getFailure() {
        return isSuccess() ? "" : String.format("Connect Failed Code[%s],Msg[%s]",
                getCode(), getMsg());
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public boolean isSuccess() {
        return code != null && code.equals("200");
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}