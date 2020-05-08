package util;

public class Escape {
	public static String transform(String s) {
		String res=new String (s);
		res=res.replaceAll("&", "&amp;");
		res=res.replaceAll("<", "&lt;");
		res=res.replaceAll(">", "&gt;");
		res=res.replaceAll("'", "&apos;");
		res=res.replaceAll("\"", "&quot;");
		return res;
	}

}
