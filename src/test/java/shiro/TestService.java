package shiro;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.entity.shiro.User;
import com.hzmsc.scada.service.shiro.UserServiceImpl;

public class TestService {
	
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("daoContext.xml","applicationContext.xml");
		UserServiceImpl userServiceImpl = context.getBean(UserServiceImpl.class);
		User user = new User("wwxxhhdd","33");
		System.out.println(user);
		User resultuser = userServiceImpl.createUser(user);
		System.out.println("User inserted:");
		System.out.println(resultuser);
		/*
		user.setUsername("wwxh");
		System.out.println("User before updated:");
		System.out.println(userServiceImpl.findById(user.getId()));
		
		
		userServiceImpl.updateUser(user);
		System.out.println("User after updated:");
		System.out.println(userServiceImpl.findById(user.getId()));
		System.out.println("User expected after update:");
		System.out.println(user);
		*/
		
		
		
		/*
		user = userServiceImpl.findById(1L);
		System.out.println("the findById(1) result:");
		System.out.println(user);
		
		user = userServiceImpl.findByUsername("ww");
		System.out.println("the findByUsername(ww) result:");
		System.out.println(user);
		*/
		List<User> userList = userServiceImpl.findAll();
		System.out.println("the findAll results:");		
		Iterator<User> i = userList.iterator();
		while(i.hasNext()){
			System.out.println(i.next());			
		}
		
		((ClassPathXmlApplicationContext) context).close();
		
	}

}
