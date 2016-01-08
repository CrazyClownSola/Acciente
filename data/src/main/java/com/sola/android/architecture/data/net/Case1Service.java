package com.sola.android.architecture.data.net;

import com.sola.android.architecture.data.entity.BannerResultEntity;

import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * author: Sola
 * 2015/11/13
 */
public interface Case1Service {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    String BASE_URL = RestConfig.BASE_URL;

    // ===========================================================
    // Methods
    // ===========================================================

    @POST("/cms/f/imageList")
    Observable<BannerResultEntity> getImageList();

    @POST("/cms/f/articleList")
    Observable<BannerResultEntity> getArticleList(@Query("categoryId") String categoryId);

}
