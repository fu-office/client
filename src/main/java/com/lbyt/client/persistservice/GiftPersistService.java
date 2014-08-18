package com.lbyt.client.persistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbyt.client.dao.IGiftDao;
import com.lbyt.client.entity.GiftEntity;

@Repository
public class GiftPersistService {
	@Autowired
	private IGiftDao giftDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void save(List<GiftEntity> list) {
		for (GiftEntity entity : list) {
			save(entity);
		}
	}
	
	@Transactional
	public GiftEntity save(GiftEntity entity) {
		return giftDao.save(entity);
	}
	
	@Transactional
	public void delete(GiftEntity entity) {
		giftDao.delete(entity);
	}
	
	public GiftEntity findByPhone(String phone) {
		return giftDao.findByPhone(phone);
	}
	
}
