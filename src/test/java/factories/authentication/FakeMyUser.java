package factories.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.triplet.service.impl.MyUser;

public class FakeMyUser {
     public FakeMyUser() {
    	 
     }
     
     public MyUser getCurrentUser() {
 		List<GrantedAuthority> authorities = new ArrayList<>();
 		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
 		MyUser currentUser = new MyUser("ngocanh", "123456", true, true, true, true, authorities);
 		currentUser.setId(1);
 		return currentUser;
     }
}
