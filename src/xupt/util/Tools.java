package xupt.util;

import java.util.Calendar;

import javax.swing.JComboBox;

public class Tools {
	
	public static String Split(String str) {
		String [] strArray = str.split(" ");
		return strArray[0];
	}
	
	public static void setSelectedItem(JComboBox box,String item) {
		for(int i = 0; i < box.getItemCount(); i++) {
			if(item.equals(Split(box.getItemAt(i)+"")))
				box.setSelectedIndex(i);
		}
	}
	
	public static String getNowTime() {
		Calendar now = Calendar.getInstance(); 
		String year = now.get(Calendar.YEAR)+"";
		String month = now.get(Calendar.MONTH)+"";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		return year+"-"+month+"-"+day;
	}
}
