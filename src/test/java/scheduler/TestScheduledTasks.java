package scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScheduledTasks {
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("scheduler.xml","daoContext.xml", "applicationContext.xml");
		
		//((ClassPathXmlApplicationContext) context).close();
	}

}
