package com.lbyt.client.persistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lbyt.client.dao.IClientDao;
import com.lbyt.client.entity.ClientEntity;

@Repository
public class ClientPersistService {
	@Autowired
	private IClientDao clientDao;
	
	public void save(List<ClientEntity> clients){
		clientDao.save(clients);
	}
	
	public void save(ClientEntity client){
		clientDao.save(client);
	}
	
}
