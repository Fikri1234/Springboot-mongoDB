package com.example.demo.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	public static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	public String convertDateISO8601ToStringSlash(Date tanggal) {
		if (tanggal != null) {
			logger.info("tanggal: "+tanggal);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String st = df.format(tanggal);
			long tgl = tanggal.getTime();
			logger.info("long: "+tgl+" tgl: "+st);
			Date time = new Date(tgl);
			String date = String.valueOf(time);
			
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			String day = date.substring(8, 10);
			
			date = year + "/" + month + "/" + day;
			
			return date;
		}
		
		return "";
	}

	public String convertDateToStringStripMM(Date tanggal) {
		if (tanggal != null) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String tgl = df.format(tanggal);
			
			return tgl;
		}
		
		return "";
	}
	
	public String convertDateToStringStripMMM(Date tanggal) {
		if (tanggal != null) {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			String tgl = df.format(tanggal);
			
			return tgl;
		}
		
		return "";
	}
	
	public String convertDateToStringSlash(Date tanggal) {
		if (tanggal != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String tgl = df.format(tanggal);
			
			return tgl;
		}
		
		return "";
	}
}
