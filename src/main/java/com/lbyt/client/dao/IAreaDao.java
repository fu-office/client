package com.lbyt.client.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.lbyt.client.entity.AreaEntity;

public interface IAreaDao extends Repository<AreaEntity, Integer>{
	
	void save(AreaEntity entity);
	
	List<AreaEntity> findAll();
	
}
