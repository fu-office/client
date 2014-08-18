package com.lbyt.client.dao;

import org.springframework.data.repository.Repository;

import com.lbyt.client.entity.GiftEntity;

public interface IGiftDao extends Repository<GiftEntity, Integer>{
	
	GiftEntity save(GiftEntity entity);

	void delete(GiftEntity entity);

	GiftEntity findByPhone(String phone);
	
}

