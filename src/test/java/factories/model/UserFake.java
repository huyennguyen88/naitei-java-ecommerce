package factories.model;

import com.triplet.bean.UserInfo;
import com.triplet.model.User;

public class UserFake {
	private User user;
	
	public UserFake() {
		user = new User();
	}
	
	public User getUser() {
		user.setId(1);
		user.setUsername("ngocanh");
		user.setPassword("123456");
		user.setAddress("23 Dinh Tien Hoang");
		user.setEmail("ngocanh@gmail.com");
		return user;
	}
	
	public UserInfo getUserInfo() {
		UserInfo userInfo = new UserInfo(user);
		return userInfo;
	}
}
