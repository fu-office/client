package com.lbyt.client.persistservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lbyt.client.dao.IClientDao;

@Repository
public class ClientPersistService {
	@Autowired
	private IClientDao clientDao;
	
}
