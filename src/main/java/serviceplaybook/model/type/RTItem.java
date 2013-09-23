package serviceplaybook.model.type;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

public class RTItem {
	static public String convertToHtml(String in) {
		if (in==null)return "";
		LineNumberReader lnr = new LineNumberReader(new StringReader(in));
		String line;
		String rtItem="";
		
		boolean listFlag=false;
		try {
			while ((line = lnr.readLine()) != null) {
				
				if (!line.startsWith("-")) {
					if (listFlag) {
						rtItem += "</ul>";
						listFlag = false;
					}
					rtItem += "<p>"+line+"</p>";
				}
				else {
					if (!listFlag) {
						rtItem += "<ul class='disc'>";
						listFlag = true;
					}
					rtItem += "<li class='disc'>"+line.substring(1).trim()+"</li>";
				}
			}
			if (listFlag) {
				rtItem += "</ul>";
			}
			return rtItem;
		} catch (IOException ioException) {
return rtItem;
		}
	}
}
