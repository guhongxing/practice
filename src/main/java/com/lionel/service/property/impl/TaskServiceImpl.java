package com.lionel.service.property.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lionel.mapper.property.CheckStaffMapper;
import com.lionel.mapper.property.CheckTaskMapper;
import com.lionel.mapper.property.PointMapper;
import com.lionel.model.property.CheckStaff;
import com.lionel.model.property.CheckTask;
import com.lionel.model.property.Point;
import com.lionel.service.property.TaskService;
import com.lionel.utils.common.DateUtil;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private CheckStaffMapper checkStaffMapper;
	@Autowired
	private PointMapper pointMapper;
	@Autowired
	private CheckTaskMapper checkTaskMapper;
	
	@Override
	//@Scheduled(cron = "0 0/2 14 * * ?")
	public void checkTask() {
		List<CheckStaff> staffList= checkStaffMapper.selectAll();
		int i=1;
		for (CheckStaff checkStaff : staffList) {
			String pointId=checkStaff.getPointId();
			Point point=pointMapper.selectByPrimaryKey(pointId);
			Date date=new Date();
			String taskId=checkStaff.getPropertyId()+DateUtil.parseDateToStr(date, DateUtil.DATE_FORMAT_YYYYMMDD)+i;
			CheckTask record=new CheckTask();
			record.setTaskId(taskId);
			record.setmallId(point.getmallId());
			record.setPropertyId(checkStaff.getPropertyId());
			record.setStaffId(checkStaff.getStaffId());
			record.setPointId(pointId);
			record.setPointLocation(point.getPointLocation());
			record.setCheckDate(date);
			record.setCheckTimePlan(checkStaff.getCheckNumber());
			record.setCheckTimeActual(0);
			checkTaskMapper.insert(record);
			i++;
		}
	}

	
}
