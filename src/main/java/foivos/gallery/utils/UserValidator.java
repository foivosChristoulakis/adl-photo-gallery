package foivos.gallery.utils;

public class UserValidator {

	
	public static boolean isUserAccepted(String username,String password) {
		
		return (username.equals("adluser") && password.equals("accesstophotos"))?true:false;
	}
}
