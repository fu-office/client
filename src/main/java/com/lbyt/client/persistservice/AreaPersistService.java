package com.lbyt.client.persistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbyt.client.dao.IAreaDao;
import com.lbyt.client.entity.AreaEntity;

@Repository
public class AreaPersistService {
	@Autowired
	private IAreaDao areaDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void save(List<AreaEntity> list) {
		for (AreaEntity entity : list) {
			save(entity);
		}
	}
	
	@Transactional
	public void save(AreaEntity entity) {
		areaDao.save(entity);
	}
	
	public List<AreaEntity> findAll() {
		return areaDao.findAll();
	}
	
}
