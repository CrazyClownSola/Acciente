package com.sola.android.architecture.data.entity.mapper;

import com.sola.android.architecture.data.entity.BannerEntity;
import com.sola.android.architecture.data.entity.BannerResultEntity;
import com.sola.android.architecture.domain.BannerResultDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: Sola
 * 2016/1/8
 */
@Singleton
public class BannerEntityDataMapper {
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
    public BannerEntityDataMapper() {
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

    public BannerResultDTO transform(BannerEntity entity) {
        BannerResultDTO retDto = null;
        if (entity != null) {
            retDto = new BannerResultDTO();
            retDto.setDescription(entity.getDescription());
            retDto.setId(entity.getId());
            retDto.setImageSrc(entity.getImageSrc());
            retDto.setLink(entity.getLink());
            retDto.setTitle(entity.getTitle());
        }
        return retDto;
    }

    public List<BannerResultDTO> transform(Collection<BannerEntity> userEntityCollection) {
        List<BannerResultDTO> dtoList = new ArrayList<>();
        BannerResultDTO user;
        for (BannerEntity userEntity : userEntityCollection) {
            user = transform(userEntity);
            if (user != null) {
                dtoList.add(user);
            }
        }
        return dtoList;
    }

    public List<BannerResultDTO> transform(BannerResultEntity resultEntity) {
        return transform(resultEntity.getResult());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
