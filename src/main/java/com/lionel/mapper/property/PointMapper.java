package com.lionel.mapper.property;

import java.util.List;

import com.lionel.model.property.Condition;
import com.lionel.model.property.Point;

public interface PointMapper {
    int deleteByPrimaryKey(String pointId);

    int insert(Point record);

    Point selectByPrimaryKey(String pointId);

    List<Point> selectAll(Condition condition);

    int updateByPrimaryKey(Point point);
    
    String primaryKeyMax(String condition);
}