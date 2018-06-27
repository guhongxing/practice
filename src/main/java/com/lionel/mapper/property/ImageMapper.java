package com.lionel.mapper.property;

import com.lionel.model.property.Image;
import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(String primaryId);

    int insert(Image record);

    Image selectByPrimaryKey(String primaryId);

    List<Image> selectAll();

    int updateByPrimaryKey(Image record);
}