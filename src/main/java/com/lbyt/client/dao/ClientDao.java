package com.lbyt.client.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.lbyt.client.entity.ClientEntity;

public interface ClientDao extends Repository<ClientEntity, Integer>{

	void save(ClientEntity client);
	
	void save(List<ClientEntity> clients);

}
