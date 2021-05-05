package factories.model;

import java.util.ArrayList;
import java.util.List;

import com.triplet.bean.UserInfo;
import com.triplet.model.User;

public class UserFake {
	private User user;

	public UserFake() {
		user = new User();
		user.setId(1);
		user.setUsername("ngocanh");
		user.setPassword("123456");
		user.setAddress("23 Dinh Tien Hoang");
		user.setEmail("ngocanh@gmail.com");
	}

	public User getUser() {
		return user;
	}

	public UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo(user);
		return userInfo;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(user);
		return users;
	}
}
