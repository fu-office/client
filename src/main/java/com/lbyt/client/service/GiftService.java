package com.lbyt.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbyt.client.bean.AreaBean;
import com.lbyt.client.bean.GiftBean;
import com.lbyt.client.entity.AreaEntity;
import com.lbyt.client.entity.GiftEntity;
import com.lbyt.client.error.ErrorBean;
import com.lbyt.client.persistservice.GiftPersistService;
import com.lbyt.client.util.CommUtil;
import com.lbyt.client.util.ExcelUtil.Cell;

@Service
public class GiftService {
	
	private static final int HEAD_TITLE_INDEX = 0;
	
	private static final int CONTENT_START_INDEX = 1;
	
	private static final String CLIENT_NAME = "姓名";
	
	private static final String CLIENT_PHONE = "电话";
	
	private int name_index = -1;
	
	private int phone_index = -1;
	
	@Autowired
	private GiftPersistService giftPersist;
	
	public GiftBean save(GiftBean gift) {
		String name = gift.getName();
		String phone = gift.getPhone();
		if (CommUtil.isEmpty(phone) || CommUtil.isEmpty(name)) {
			gift.setSuccess(false);
			ErrorBean e = new ErrorBean();
			e.setMessage("姓名和电话不能为空");
			gift.getErrors().add(e);
		} else {
			GiftEntity entity = bulidEntity(gift);
			GiftEntity storeEntity = giftPersist.findByPhone(entity.getPhone());
			if (storeEntity != null && storeEntity.getId() != null) {
				gift.setSuccess(false);
				ErrorBean e = new ErrorBean();
				e.setMessage("该联系电话已存在");
				gift.getErrors().add(e);
			} else {
				entity = giftPersist.save(entity);
				gift.setId(entity.getId());
				gift.setSuccess(true);
			}
		}
		return gift;
	}
	
	private GiftEntity bulidEntity (GiftBean gift) {
		GiftEntity entity = new GiftEntity();
		entity.setClientId(gift.getClientId());
		entity.setDate(gift.getDate());
		entity.setId(gift.getId());
		entity.setName(gift.getName());
		entity.setPhone(gift.getPhone());
		return entity;
	}
	
	public AreaBean bulidBean(AreaEntity entity) {
		AreaBean bean = new AreaBean();
		bean.setCity(entity.getCity());
		bean.setDate(entity.getDate());
		bean.setId(entity.getId());
		bean.setDetail(entity.getDetail());
		bean.setProv(entity.getProv());
		return bean;
	}
	
	private void setCellIndex(Cell[] cells) {
		int length = cells.length, i = 0;
		for (; i < length; i ++) {
			Cell cell = cells[i];
			if (cell != null && cell.getValue() != null) {
				
			}
		}
	}
	
	private void resetIndex(){
		this.name_index = -1;
		this.phone_index = -1;
	}
}
