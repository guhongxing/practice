package com.lionel.mapper.property;

import com.lionel.model.property.Decorate;
import java.util.List;

public interface DecorateMapper {
    int insert(Decorate record);

    List<Decorate> selectAll();
}