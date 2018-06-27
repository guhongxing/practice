package com.lionel.mapper.property;

import com.lionel.model.property.DecorateCheck;
import java.util.List;

public interface DecorateCheckMapper {
    int insert(DecorateCheck record);

    List<DecorateCheck> selectAll();
}