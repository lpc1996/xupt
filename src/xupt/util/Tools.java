package xupt.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;

public class Tools {
		
	public String Split(String str) {
		String [] strArray = str.split(" ");
		return strArray[0];
	}
	
	public void setSelectedItem(JComboBox box,String item) {
		for(int i = 0; i < box.getItemCount(); i++) {
			if(item.equals(Split(box.getItemAt(i)+"")))
				box.setSelectedIndex(i);
		}
	}
	
	public String getNowTime() {
			Calendar now = Calendar.getInstance(); 
			String year = now.get(Calendar.YEAR)+"";
			String month = now.get(Calendar.MONTH)+"";
			String day = now.get(Calendar.DAY_OF_MONTH)+"";
			return year+"-"+month+"-"+day;
	}
	
	public List<String> splitEnumValue(String value) {
		List<String> enumValue = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int m=0;
		for(int i = 0; i < value.length(); i++) {
			if(value.charAt(i) == '\'' && m==0) {
				m=1;
			}else if(value.charAt(i) == '\'' && m==1) {
				enumValue.add(sb.toString());
				sb.delete(0, sb.length());
				m=0;
			}else {
				if(m==1) {
					sb.append(value.charAt(i));
				}
			}
		}
		return enumValue;
	}
	
	public static void main(String[] argv) {
		Tools tool = new Tools();
		tool.splitEnumValue("enum('ÄÐ','Å®','±£ÃÜ')");
	}
}
