package main;

import java.sql.Timestamp;

public class TempTest {
	public static void main(String[] args) {

		int repeatTimes = 10;
		while (repeatTimes > 0) {
			System.out.println("helloworld!" + repeatTimes);
			Timestamp timestamp1=new Timestamp(System.currentTimeMillis());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Timestamp timestamp2=new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp2.getTime()-timestamp1.getTime());
			repeatTimes--;
		}

	}

}
