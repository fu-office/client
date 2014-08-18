package com.lbyt.client.persistservice;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lbyt.client.constant.ClientConstants;
import com.lbyt.client.dao.IClientDao;
import com.lbyt.client.entity.AreaEntity;
import com.lbyt.client.entity.ClientEntity;
import com.lbyt.client.util.CommUtil;

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
	public ClientEntity save(ClientEntity entity) {
		return clientDao.save(entity);
	}

	public List<ClientEntity> search(final ClientEntity entity, final String shopState) {
		return clientDao.findAll(new Specification<AreaEntity>(){

			@Override
			public Predicate toPredicate(Root<AreaEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> prov = root.get("province");
				Path<String> city = root.get("city");
				Path<String> shopName = root.get("shopName");
				Predicate predicate = null;
				if (!CommUtil.isEmpty(entity.getProvince())) {
					predicate = cb.equal(prov, entity.getProvince());
				}
				if (!CommUtil.isEmpty(entity.getCity())) {
					predicate = predicate == null ? cb.equal(city, entity.getCity()) : 
							cb.and(predicate, cb.equal(city, entity.getCity()));
				}
				if (!CommUtil.isEmpty(shopState)) {
					if (ClientConstants.HAS_SHOP.equals(shopState)) {
						predicate = predicate == null ? cb.isNotNull(shopName) : cb.and(predicate, cb.isNotNull(shopName));
					} else {
						predicate = predicate == null ? cb.isNull(shopName) : cb.and(predicate, cb.isNull(shopName));
					}
				}
				return predicate;
			}
			
		});
	}

	public ClientEntity findByCardNo(ClientEntity entity) {
		return clientDao.findByCardNo(entity.getCardNum());
	}
	
}
