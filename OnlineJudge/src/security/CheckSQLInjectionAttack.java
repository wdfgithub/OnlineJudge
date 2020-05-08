package security;

import java.util.regex.Pattern;

public class CheckSQLInjectionAttack {
	private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|"
            + "insert|trancate|char|into|substr|ascii|"
            + "declare|exec|count|master|into|drop|execute)\\b)";
	private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	public static boolean isValid(String str){
		if (sqlPattern.matcher(str).find()){
			return false;
		}
		return true;
	}  
}
