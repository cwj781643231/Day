package main;

import com.hzmsc.scada.utils.OperationUtils;

public class TestMain {
	public static void main(String[] args) {
		int a = 0x8000FFFF;
		int e = 0x0F0FFFFF;
		//System.out.println(e);
		System.out.println(OperationUtils.getLowIndexByteFromInt(e, 1));
		System.out.println(OperationUtils.getLowIndexByteFromInt(e, 2));
		System.out.println(OperationUtils.getLowIndexByteFromInt(e, 3));
		System.out.println(OperationUtils.getLowIndexByteFromInt(e, 4));
		System.out.println(a);
		a = a & e;
		System.out.println(a);
		long c = (long) a;
		a = a << 16;
		System.out.println(a);
		int b = 0x0000FFFF;
		long d = (long) b;
		System.out.println(c + d);
		c = c << 16;
		System.out.println(c);
		System.out.println(c + d);
	}

}
