package br.com.ucsal.meta.agil.util;

import java.time.LocalDate;

public class DataUtils {
	
	public static LocalDate stringToLocalDate(String data) {
		LocalDate lc;
		try { 
			if(!data.isEmpty() || data != null) {
				 lc = LocalDate.parse(data);
				return lc;
			}
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}

}
