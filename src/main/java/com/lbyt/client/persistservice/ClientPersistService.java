package com.lbyt.client.persistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbyt.client.dao.IClientDao;
import com.lbyt.client.entity.ClientEntity;

@Repository
public class ClientPersistService {
	
	@Autowired
	private IClientDao clientDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void save(List<ClientEntity> list) {
		for (ClientEntity entity : list) {
			save(entity);
		}
	}
	
	@Transactional
	public void save(ClientEntity entity) {
		clientDao.save(entity);
	}
	
}
