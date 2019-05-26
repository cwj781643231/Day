package modbus;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hzmsc.scada.lib.Exceptions.ModbusException;
import com.hzmsc.scada.lib.modbus.ModbusClientSlave;

public class TestModbusClientSlave {
	
	private ModbusClientSlave modbusClient;

	@Before
	public void testModbusClientSlaveConnect() {
		modbusClient = new ModbusClientSlave("127.0.0.1", 502);
		System.out.println(modbusClient.getipAddress());
		System.out.println(modbusClient.getPort());
		try {
			modbusClient.Connect();
			System.out.println("modbusClient connected!");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			//e.printStackTrace();
		}
		//fail("Not yet implemented");
	}

	@Test
	public void testReadDiscreteInputs() {
		//fail("Not yet implemented");
	}

	@Test
	public void testReadCoils() {
		//fail("Not yet implemented");
	}

	@Test
	public void testReadHoldingRegisters() {
		int [] holdingRegister = null;
		try {
			holdingRegister = modbusClient.ReadHoldingRegisters(41000, 10, (byte) 203);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModbusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<holdingRegister.length; i++ ){
			System.out.println(holdingRegister[i]);
		}
	}

	@Test
	public void testReadInputRegisters() {
		int value = 1;
		try {
			modbusClient.WriteSingleRegister(41000, value, (byte)203);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModbusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteSingleCoil() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteSingleRegister() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteMultipleCoils() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteMultipleRegisters() {
		//fail("Not yet implemented");
	}
	
	@After
	public void testModbusClientSlaveClose(){
		try {
			modbusClient.Disconnect();
			System.out.println("modbusClient disconnected!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
