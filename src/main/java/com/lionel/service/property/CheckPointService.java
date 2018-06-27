package com.lionel.service.property;

import java.util.List;

import com.lionel.model.property.Condition;
import com.lionel.model.property.Point;

public interface CheckPointService {
	
	public void addPoint(Point point);
	
	public List<Point> slectPoint(Condition condition);
	
	public Point getPoint(String pointId);
	
	public void update(Point point);

}
