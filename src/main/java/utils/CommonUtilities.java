package utils;

import java.util.Date;

public class CommonUtilities {

	public static String generateDummyMail() {
		Date date=new Date();
		String newMail=date.toString().replaceAll("\\s","").replaceAll("\\:","")+"@gmail.com";
		return newMail;
	}
}
