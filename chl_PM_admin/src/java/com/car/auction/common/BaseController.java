package com.car.auction.common;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 数据类型封装控制器
 * @author ANLVE
 *
 */
public class BaseController {
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	}

	private class DateEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = null;
			try {
				date = format.parse(text);
			} catch (ParseException e) {
				format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = format.parse(text);
				} catch (ParseException e1) {
					
				}
			}
			setValue(date);
		}
	}

	public class DoubleEditor extends PropertiesEditor {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			Double parseDouble = null;
			try {
				parseDouble = Double.parseDouble(text);
			} catch (Exception e) {
				parseDouble = null;
			}
			setValue(parseDouble);
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}
	}

	public class IntegerEditor extends PropertiesEditor {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			Integer parseInt = null;
			try {
				parseInt = Integer.parseInt(text);
			} catch (Exception e) {
				parseInt = null;
			}
			setValue(parseInt);
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}
	}
	
	public class BigDecimalEditor extends PropertiesEditor {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			BigDecimal bigDecimal =null;
			try {
				bigDecimal = new BigDecimal(text);
			} catch (Exception e) {
				bigDecimal =null;
			}
			setValue(bigDecimal);
		}

		@Override
		public String getAsText() {
			return getValue().toString();
		}
	}
}
