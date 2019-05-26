package shiro;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzmsc.scada.dao.shiro.UserDaoImpl;
import com.hzmsc.scada.entity.shiro.User;

public class TestDao {
	
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("daoContext.xml");
		UserDaoImpl userDaoImpl = context.getBean(UserDaoImpl.class);
		
		//User user = userDaoImpl.findById(1L);
		//System.out.println(user);
		
		User user = new User("wwwwww", "12345");
		System.out.println(user);
		userDaoImpl.createUser(user);
		
		List<User> userList = userDaoImpl.findAll();
		Iterator<User> i = userList.iterator();
		while(i.hasNext()){
			System.out.println(i.next());			
		}
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
