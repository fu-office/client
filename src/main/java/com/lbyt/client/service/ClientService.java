package com.lbyt.client.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lbyt.client.bean.ClientBean;
import com.lbyt.client.bean.ClientSearchBean;
import com.lbyt.client.entity.ClientEntity;
import com.lbyt.client.error.ErrorBean;
import com.lbyt.client.persistservice.ClientPersistService;
import com.lbyt.client.util.BeanUtil;
import com.lbyt.client.util.DateUtil;
import com.lbyt.client.util.ExcelUtil;
import com.lbyt.client.util.ExcelUtil.Cell;
import com.lbyt.client.util.ExcelUtil.Sheet;

@Service
public class ClientService {
	
	private static final String MODIFY_DATE = "登记日期";
	
	private static final String CARD_NO = "卡号";
	
	private static final String CLIENT_NAME = "姓名";
	
	private static final String CLIENT_PHONE = "手机号";
	
	private static final String CLIENT_TEL = "电话";
	
	private static final String CLIENT_ADDR = "联系地址";
	
	private static final String POST_CODE = "邮编";
	
	private static final String BIRTHDAY = "生日";
	
	private static final String AREA = "区域";
	
	private static final String SHOP_NAME = "专柜名称";
	
	private static final int CONTENT_START_INDEX = 1;
	
	private int modify_date_index = -1;
	
	private int card_no_index = -1;
	
	private int client_name_index = -1;
	
	private int client_phone_index = -1;
	
	private int client_tel_index = -1;
	
	private int client_addr_index = -1;
	
	private int post_code_index = -1;
	
	private int birthday_index = -1;
	
	private int area_index = -1;
	
	private int shop_name_index = -1;
	
	@Autowired
	ClientPersistService clientPersistService;
	
	public ClientSearchBean importExcel(MultipartFile file){
		ClientSearchBean jsonBean = new ClientSearchBean();
		jsonBean.setSuccess(false);
		List<ClientEntity> entities = new ArrayList<ClientEntity>();
		try {
			List<Sheet> sheets = ExcelUtil.parseExcel(file.getInputStream());
			for (Sheet st : sheets) {
				restIndex();
				int last = st.getLastNumber();
				Cell[] titles = st.getRow(0);
				setCellIndexs(titles);
				for (int i = CONTENT_START_INDEX; i < last; i ++) {
					Cell[] cells = st.getRow(i);
					ClientEntity entity = new ClientEntity();
					entity.setCardNum(card_no_index == -1 || cells[card_no_index] == null ? null  :  cells[card_no_index].getValue());
					entity.setAddress(client_addr_index == -1 || cells[client_addr_index] == null ? null : cells[client_addr_index].getValue());
					entity.setCity(area_index == -1 || cells[area_index] == null ?  null : cells[area_index].getValue());
					entity.setPostCode(post_code_index == -1 || cells[post_code_index] == null ? null : cells[post_code_index].getValue());
					entity.setName(client_name_index == -1 || cells[client_name_index] == null ? null : cells[client_name_index].getValue());
					entity.setPhoneNumber(client_phone_index == -1 || cells[client_phone_index] == null ? null : cells[client_phone_index].getValue());
					entity.setTelNumber(client_tel_index == -1 || cells[client_tel_index] == null ? null : cells[client_tel_index].getValue());
					entity.setShopName(shop_name_index == -1 || cells[shop_name_index] == null  ? null : cells[shop_name_index].getValue());
					try {
						entity.setBirthday(birthday_index == -1 || cells[birthday_index] == null  ? null : DateUtil.parseDate(cells[birthday_index].getValue()));
					} catch (ParseException e) {}
					try {
						entity.setModifyDate(modify_date_index == -1 || cells[modify_date_index] == null  ? null : DateUtil.parseDate(cells[modify_date_index].getValue()));
					} catch (ParseException e) {}
					entities.add(entity);
				}
			}
			jsonBean.setList(bulidClientList(entities));
			jsonBean.setSuccess(true);
		} catch (IOException e) {
			ErrorBean error = new ErrorBean();
			error.setMessage("文件读取失败，请确保文件内容");
			jsonBean.getErrors().add(error);
			jsonBean.setSuccess(false);
		}
		return jsonBean;
	}
	
	private void restIndex() {
		this.area_index = -1;
		this.birthday_index = -1;
		this.card_no_index = -1;
		this.client_addr_index = -1;
		this.client_name_index = -1;
		this.client_phone_index = -1;
		this.client_tel_index = -1;
		this.modify_date_index = -1;
		this.post_code_index = -1;
		this.shop_name_index = -1;
	}
	
	private void setCellIndexs(Cell[] cells) {
		int length = cells.length, i =0;
		for (; i < length; i++) {
			Cell cell = cells[i];
			String str = cell.getValue();
			if  (str != null) {
				str = str.replaceAll(" ", "");
				if (MODIFY_DATE.equals(str)) {
					this.modify_date_index = i;
				} else if (CARD_NO.equals(str)) {
					this.card_no_index = i;
				} else if (CLIENT_NAME.equals(str)) {
					this.client_name_index = i;
				} else if (CLIENT_PHONE.equals(str)) {
					this.client_phone_index = i;
				} else if (CLIENT_TEL.equals(str)) {
					this.client_tel_index = i;
				} else if (CLIENT_ADDR.equals(str)) {
					this.client_addr_index = i;
				} else if (POST_CODE.equals(str)) {
					this.post_code_index = i;
				} else if (BIRTHDAY.equals(str)) {
					this.birthday_index = i;
				} else if (AREA.equals(str)) {
					this.area_index = i;
				} else if (SHOP_NAME.equals(str)) {
					this.shop_name_index = i;
				}
			} 
		}
	}
	
	private List<ClientBean> bulidClientList(List<ClientEntity> entities) {
		List<ClientBean> list = new ArrayList<ClientBean>();
		for (ClientEntity e : entities) {
			try {
				list.add((ClientBean) BeanUtil.bulidBean(new ClientBean(), e));
			} catch (IllegalAccessException | SecurityException
					| ClassNotFoundException | NoSuchMethodException
					| InvocationTargetException e1) {
				list.add(null);
			}
		}
		return list;
	}
}
