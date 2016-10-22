package validator;

import com.beydilli.todolist.form.UserForm;
import com.beydilli.todolist.model.User;
import com.beydilli.todolist.util.StringUtils;

public class UserCredentialsValidator {

	private final static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public static boolean validateUserCredentials(UserForm userForm) {
		if (StringUtils.isBlank(userForm.getName()) || StringUtils.isBlank(userForm.getSurname()) || StringUtils.isBlank(userForm.getEmail()) || StringUtils.isBlank(userForm.getPassword())) {
			return false;
		}
		if (!isEmailValid(userForm.getEmail())) {
			return false;
		}
		return true;
	}

	public static boolean validateLoginCredentials(String email, String password) {
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			return false;
		}
		if (!isEmailValid(email)) {
			return false;
		}
		return true;
	}

	private static boolean isEmailValid(String email) {
		return !email.matches(EMAIL_REGEX);
	}
}
