package com.hzmsc.scada.utils;

public class OperationUtils {
	
	//取high的后8位做高字节，后8位做低字节，返回长整形，以避免出现负数
	public static long catTowHalfWord(int high16, int low16){
		long high = (long)(high16 & 0x0000FFFF);
		long low = (long)(low16 & 0x0000FFFF);
		
		high = high << 16;		
		long result = high + low;
		
		return result;
	}
	
	
	//从低位到高位字节为lowIndex
	public static int getLowIndexByteFromInt(int source, int lowIndex){

		int result = source;
		
		if(lowIndex >= 1 && lowIndex <= 4){
			if(lowIndex == 1){
				result = source & 0x000000FF;
			}
			if(lowIndex == 2){
				result = source & 0x0000FF00;
			}
			if(lowIndex == 3){
				result = source & 0x00FF0000;
			}
			if(lowIndex == 4){
				result = source & 0xFF000000;
			}
			for(int i = 1; i < lowIndex; i ++){
				result = result >> 8;
			}
		}
		
		return result;
	}

}
