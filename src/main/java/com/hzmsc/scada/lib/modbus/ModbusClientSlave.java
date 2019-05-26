package com.hzmsc.scada.lib.modbus;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.*;

import com.hzmsc.scada.lib.Exceptions.*;


     /**
     * @author Stefan Roßmann
     */
public class ModbusClientSlave 
{
	public enum RegisterOrder { LowHigh, HighLow };
	private Socket tcpClientSocket = new Socket();
	protected String ipAddress = "190.201.100.100";
	protected int port = 502;
	private byte [] transactionIdentifier = new byte[2];
	private byte [] protocolIdentifier = new byte[2];
	private byte [] length = new byte[2];
	private byte[] crc = new byte[2];
	private byte unitIdentifier;
	private byte functionCode;
	private byte [] startingAddress = new byte[2];
	private byte [] quantity = new byte[2];
	private boolean udpFlag = false;
	private int connectTimeout = 500;
	private InputStream inStream;
	private DataOutputStream outStream;
        public byte[] receiveData;
        public byte[] sendData;  
	private List<ReceiveDataChangedListener> receiveDataChangedListener = new ArrayList<ReceiveDataChangedListener>();
	private List<SendDataChangedListener> sendDataChangedListener = new ArrayList<SendDataChangedListener>();

	public ModbusClientSlave(String ipAddress, int port)
	{
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public ModbusClientSlave()
	{
	}
	
        /**
        * Connects to ModbusServer
        * @throws UnknownHostException
        * @throws IOException
        */        
	public void Connect() throws UnknownHostException, IOException
	{
		if (!udpFlag)
		{
			tcpClientSocket.setSoTimeout(connectTimeout);
			tcpClientSocket = new Socket(ipAddress, port);
			outStream = new DataOutputStream(tcpClientSocket.getOutputStream());
			inStream = tcpClientSocket.getInputStream();
		}
	}
	
        /**
        * Connects to ModbusServer
        * @param ipAddress  IP Address of Modbus Server to connect to
        * @param port   Port Modbus Server listenning (standard 502)
        * @throws UnknownHostException
        * @throws IOException
        */   
	public void Connect(String ipAddress, int port) throws UnknownHostException, IOException
	{
		this.ipAddress = ipAddress;
		this.port = port;
		tcpClientSocket.setSoTimeout(connectTimeout);
		tcpClientSocket = new Socket(ipAddress, port);
		outStream = new DataOutputStream(tcpClientSocket.getOutputStream());
		inStream = tcpClientSocket.getInputStream();
	}
	
        /**
        * Convert two 16 Bit Registers to 32 Bit real value
        * @param        registers   16 Bit Registers
        * @return       32 bit real value
        */
    public static float ConvertRegistersToFloat(int[] registers) throws IllegalArgumentException
    {
        if (registers.length != 2)
            throw new IllegalArgumentException("Input Array length invalid");
        int highRegister = registers[1];
        int lowRegister = registers[0];
        byte[] highRegisterBytes = toByteArray(highRegister);
        byte[] lowRegisterBytes = toByteArray(lowRegister);
        byte[] floatBytes = {
                                highRegisterBytes[1],
                                highRegisterBytes[0],
                                lowRegisterBytes[1],
                                lowRegisterBytes[0]
                            };
        return ByteBuffer.wrap(floatBytes).getFloat();
    }
    
        /**
        * Convert two 16 Bit Registers to 32 Bit real value
        * @param        registers   16 Bit Registers
        * @param        registerOrder    High Register first or low Register first
        * @return       32 bit real value
        */
    public static float ConvertRegistersToFloat(int[] registers, RegisterOrder registerOrder) throws IllegalArgumentException
    {
        int [] swappedRegisters = {registers[0],registers[1]};
        if (registerOrder == RegisterOrder.HighLow) 
            swappedRegisters = new int[] {registers[1],registers[0]};
        return ConvertRegistersToFloat(swappedRegisters);
    }
    
        /**
        * Convert two 16 Bit Registers to 32 Bit long value
        * @param        registers   16 Bit Registers
        * @return       32 bit value
        */
    public static int ConvertRegistersToDouble(int[] registers) throws IllegalArgumentException
    {
        if (registers.length != 2)
            throw new IllegalArgumentException("Input Array length invalid");
        int highRegister = registers[1];
        int lowRegister = registers[0];
        byte[] highRegisterBytes = toByteArray(highRegister);
        byte[] lowRegisterBytes = toByteArray(lowRegister);
        byte[] doubleBytes = {
                                highRegisterBytes[1],
                                highRegisterBytes[0],
                                lowRegisterBytes[1],
                                lowRegisterBytes[0]
                            };
        return ByteBuffer.wrap(doubleBytes).getInt();
    }
    
        /**
        * Convert two 16 Bit Registers to 32 Bit long value
        * @param        registers   16 Bit Registers
        * @param        registerOrder    High Register first or low Register first
        * @return       32 bit value
        */
    public static int ConvertRegistersToDouble(int[] registers, RegisterOrder registerOrder) throws IllegalArgumentException
    {
        int[] swappedRegisters = { registers[0], registers[1] };
        if (registerOrder == RegisterOrder.HighLow)
            swappedRegisters = new int[] { registers[1], registers[0] };
        return ConvertRegistersToDouble(swappedRegisters);
    }
    
        /**
        * Convert 32 Bit real Value to two 16 Bit Value to send as Modbus Registers
        * @param        floatValue      real to be converted
        * @return       16 Bit Register values
        */
    public static int[] ConvertFloatToTwoRegisters(float floatValue)
    {
        byte[] floatBytes = toByteArray(floatValue);
        byte[] highRegisterBytes = 
        {
        		0,0,
            floatBytes[0],
            floatBytes[1],

        };
        byte[] lowRegisterBytes = 
        {
            0,0,
            floatBytes[2],
            floatBytes[3],

        };
        int[] returnValue =
        {
        		ByteBuffer.wrap(lowRegisterBytes).getInt(),
        		ByteBuffer.wrap(highRegisterBytes).getInt()
        };
        return returnValue;
    }
    
        /**
        * Convert 32 Bit real Value to two 16 Bit Value to send as Modbus Registers
        * @param        floatValue      real to be converted
        * @param        registerOrder    High Register first or low Register first
        * @return       16 Bit Register values
        */
    public static int[] ConvertFloatToTwoRegisters(float floatValue, RegisterOrder registerOrder)
    {
        int[] registerValues = ConvertFloatToTwoRegisters(floatValue);
        int[] returnValue = registerValues;
        if (registerOrder == RegisterOrder.HighLow)
            returnValue = new int[] { registerValues[1], registerValues[0] };
        return returnValue;
    }
    
        /**
        * Convert 32 Bit Value to two 16 Bit Value to send as Modbus Registers
        * @param        doubleValue      Value to be converted
        * @return       16 Bit Register values
        */
    public static int[] ConvertDoubleToTwoRegisters(int doubleValue)
    {
        byte[] doubleBytes = toByteArrayDouble(doubleValue);
        byte[] highRegisterBytes = 
        {
        		0,0,
            doubleBytes[0],
            doubleBytes[1],

        };
        byte[] lowRegisterBytes = 
        {
            0,0,
            doubleBytes[2],
            doubleBytes[3],

        };
        int[] returnValue =
        {
        		ByteBuffer.wrap(lowRegisterBytes).getInt(),
        		ByteBuffer.wrap(highRegisterBytes).getInt()
        };
        return returnValue;
    }
    
        /**
        * Convert 32 Bit Value to two 16 Bit Value to send as Modbus Registers
        * @param        doubleValue      Value to be converted
        * @param        registerOrder    High Register first or low Register first
        * @return       16 Bit Register values
        */
    public static int[] ConvertDoubleToTwoRegisters(int doubleValue, RegisterOrder registerOrder)
    {
        int[] registerValues = ConvertFloatToTwoRegisters(doubleValue);
        int[] returnValue = registerValues;
        if (registerOrder == RegisterOrder.HighLow)
            returnValue = new int[] { registerValues[1], registerValues[0] };
        return returnValue;
    }
    
        /**
        * Read Discrete Inputs from Server
        * @param        startingAddress      Fist Address to read; Shifted by -1	
        * @param        quantity            Number of Inputs to read
        * @return       Discrete Inputs from Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
	public boolean[] ReadDiscreteInputs(int startingAddress, int quantity, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
	{
		if (tcpClientSocket == null)
			throw new ConnectionException("connection Error");
		if (startingAddress > 65535 | quantity > 2000)
			throw new IllegalArgumentException("Starting adress must be 0 - 65535; quantity must be 0 - 2000");
		boolean[] response = null;
		this.transactionIdentifier = toByteArray(0x0001);
		this.protocolIdentifier = toByteArray(0x0000);
		this.length = toByteArray(0x0006);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = 0x02;
		this.startingAddress = toByteArray(startingAddress);
		this.quantity = toByteArray(quantity);
		byte[] data = new byte[]
				{
					this.transactionIdentifier[1],
					this.transactionIdentifier[0],
					this.protocolIdentifier[1],
					this.protocolIdentifier[0],
					this.length[1],
					this.length[0],
					this.unitIdentifier,
					this.functionCode,
					this.startingAddress[1],
					this.startingAddress[0],
					this.quantity[1],
					this.quantity[0],
                    this.crc[0],
                    this.crc[1]					
				};
		
		if (tcpClientSocket.isConnected() | udpFlag)
		{
			if (udpFlag)
			{
				InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
				DatagramPacket sendPacket = new DatagramPacket(data, data.length-2, ipAddress, this.port);
				DatagramSocket clientSocket = new DatagramSocket();
				clientSocket.setSoTimeout(500);
			    clientSocket.send(sendPacket);
			    data = new byte[2100];
			    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			    clientSocket.receive(receivePacket);
			    data = receivePacket.getData();
			    clientSocket.close();
			}
			else
			{
				outStream.write(data, 0, data.length-2);
				if (sendDataChangedListener.size() > 0)
				{
					sendData = new byte[data.length-2];
					System.arraycopy(data, 0, sendData, 0, data.length-2);
					for (SendDataChangedListener hl : sendDataChangedListener)
						hl.SendDataChanged();
				}
				data = new byte[2100];
				int numberOfBytes = inStream.read(data, 0, data.length);
				if (receiveDataChangedListener.size() > 0)
				{
					receiveData = new byte[numberOfBytes];
					System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
					for (ReceiveDataChangedListener hl : receiveDataChangedListener)
						hl.ReceiveDataChanged();
				}
			}
			if (((byte) data[7]) == 0x82 & ((byte) data[8]) == 0x01)
				throw new FunctionCodeNotSupportedException("Function code not supported by master");
			if (((byte) data[7]) == 0x82 & ((byte) data[8]) == 0x02)
				throw new StartingAddressInvalidException("Starting adress invalid or starting adress + quantity invalid");
			if (((byte) data[7]) == 0x82 & ((byte) data[8]) == 0x03)
				throw new QuantityInvalidException("Quantity invalid");
			if (((byte) data[7]) == 0x82 & ((byte) data[8]) == 0x04)
				throw new ModbusException("Error reading");
			response = new boolean [quantity];
			for (int i = 0; i < quantity; i++)
			{
				int intData = data[9 + i/8];
				int mask = (int)Math.pow(2, (i%8));
				intData = ((intData & mask)/mask);
				if (intData >0)
					response[i] = true;
				else
					response[i] = false;
			}
			
		}
		return (response);
	}
	
        /**
        * Read Coils from Server
        * @param        startingAddress      Fist Address to read; Shifted by -1	
        * @param        quantity            Number of Inputs to read
        * @return       coils from Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
	public boolean[] ReadCoils(int startingAddress, int quantity, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
	{
		if (tcpClientSocket == null)
			throw new ConnectionException("connection Error");
		if (startingAddress > 65535 | quantity > 2000)
			throw new IllegalArgumentException("Starting adress must be 0 - 65535; quantity must be 0 - 2000");
		boolean[] response = new boolean[quantity];
		this.transactionIdentifier = toByteArray(0x0001);
		this.protocolIdentifier = toByteArray(0x0000);
		this.length = toByteArray(0x0006);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = 0x01;
		this.startingAddress = toByteArray(startingAddress);
		this.quantity = toByteArray(quantity);
		byte[] data = new byte[]
				{
					this.transactionIdentifier[1],
					this.transactionIdentifier[0],
					this.protocolIdentifier[1],
					this.protocolIdentifier[0],
					this.length[1],
					this.length[0],
					this.unitIdentifier,
					this.functionCode,
					this.startingAddress[1],
					this.startingAddress[0],
					this.quantity[1],
					this.quantity[0],
                    this.crc[0],
                    this.crc[1]		
				};
		if (tcpClientSocket.isConnected() | udpFlag)
		{
			if (udpFlag)
			{
				InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
				DatagramSocket clientSocket = new DatagramSocket();
				clientSocket.setSoTimeout(500);
			    clientSocket.send(sendPacket);
			    data = new byte[2100];
			    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			    clientSocket.receive(receivePacket);
			    data = receivePacket.getData();
			    clientSocket.close();
			}
			else
			{
				outStream.write(data, 0, data.length-2);
				if (sendDataChangedListener.size() > 0)
				{
					sendData = new byte[data.length-2];
					System.arraycopy(data, 0, sendData, 0, data.length-2);
					for (SendDataChangedListener hl : sendDataChangedListener)
						hl.SendDataChanged();
				}
				data = new byte[2100];
				int numberOfBytes = inStream.read(data, 0, data.length);
				if (receiveDataChangedListener.size() > 0)
				{
					receiveData = new byte[numberOfBytes];
					System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
					for (ReceiveDataChangedListener hl : receiveDataChangedListener)
						hl.ReceiveDataChanged();
				}
			}
			if (((byte) data[7]) == 0x81 & ((byte) data[8]) == 0x01)
				throw new FunctionCodeNotSupportedException("Function code not supported by master");
			if (((byte) data[7]) == 0x81 & ((byte) data[8]) == 0x02)
				throw new StartingAddressInvalidException("Starting adress invalid or starting adress + quantity invalid");
			if (((byte) data[7]) == 0x81 & ((byte) data[8]) == 0x03)
				throw new QuantityInvalidException("Quantity invalid");
			if (((byte) data[7]) == 0x81 & ((byte) data[8]) == 0x04)
				throw new ModbusException("Error reading");
			for (int i = 0; i < quantity; i++)
			{
				int intData = (int) data[9 + i/8];
				int mask = (int)Math.pow(2, (i%8));
				intData = ((intData & mask)/mask);
				if (intData >0)
					response[i] = true;
				else
					response[i] = false;
			}
			
		}
		return (response);
	}
        
        /**
        * Read Holding Registers from Server
        * @param        startingAddress      Fist Address to read; Shifted by -1	
        * @param        quantity            Number of Inputs to read
        * @return       Holding Registers from Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
	public int[] ReadHoldingRegisters(int startingAddress, int quantity, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
	{
		if (tcpClientSocket == null)
			throw new ConnectionException("connection Error");
		if (startingAddress > 65535 | quantity > 125)
			throw new IllegalArgumentException("Starting adress must be 0 - 65535; quantity must be 0 - 125");
		int[] response = new int[quantity];
		this.transactionIdentifier = toByteArray(0x0001);
		this.protocolIdentifier = toByteArray(0x0000);
		this.length = toByteArray(0x0006);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = 0x03;
		this.startingAddress = toByteArray(startingAddress);
		this.quantity = toByteArray(quantity);
		byte[] data = new byte[]
				{
					this.transactionIdentifier[1],
					this.transactionIdentifier[0],
					this.protocolIdentifier[1],
					this.protocolIdentifier[0],
					this.length[1],
					this.length[0],
					this.unitIdentifier,
					this.functionCode,
					this.startingAddress[1],
					this.startingAddress[0],
					this.quantity[1],
					this.quantity[0],
                    this.crc[0],
                    this.crc[1]		
				};
		if (tcpClientSocket.isConnected() | udpFlag)
		{
			if (udpFlag)
			{
				InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
				DatagramSocket clientSocket = new DatagramSocket();
				clientSocket.setSoTimeout(500);
			    clientSocket.send(sendPacket);
			    data = new byte[2100];
			    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			    clientSocket.receive(receivePacket);
			    data = receivePacket.getData();
			    clientSocket.close();
			}
			else
			{
				outStream.write(data, 0, data.length-2);
				if (sendDataChangedListener.size() > 0)
				{
					sendData = new byte[data.length-2];
					System.arraycopy(data, 0, sendData, 0, data.length-2);
					for (SendDataChangedListener hl : sendDataChangedListener)
						hl.SendDataChanged();
				}
				data = new byte[2100];
				int numberOfBytes = inStream.read(data, 0, data.length);
				if (receiveDataChangedListener.size() > 0)
				{
					receiveData = new byte[numberOfBytes];
					System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
					for (ReceiveDataChangedListener hl : receiveDataChangedListener)
						hl.ReceiveDataChanged();
				}
			}
			if (((byte) data[7]) == 0x83 & ((byte) data[8]) == 0x01)
				throw new FunctionCodeNotSupportedException("Function code not supported by master");
			if (((byte) data[7]) == 0x83 & ((byte) data[8]) == 0x02)
				throw new StartingAddressInvalidException("Starting adress invalid or starting adress + quantity invalid");
			if (((byte) data[7]) == 0x83 & ((byte) data[8]) == 0x03)
				throw new QuantityInvalidException("Quantity invalid");
			if (((byte) data[7]) == 0x83 & ((byte) data[8]) == 0x04)
				throw new ModbusException("Error reading");
			for (int i = 0; i < quantity; i++)
			{
				byte[] bytes = new byte[2];
				bytes[0] = data[9+i*2];
				bytes[1] = data[9+i*2+1];
				ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
				
				/**
				 * changed by james, 2016-10-18
				 * response[i] = byteBuffer.getShort();
				 * for deal with unsigned short
				 */
				response[i] = byteBuffer.getShort() & 0xFFFF;
			}
			
		}
		return (response);
	}
	
	/**
        * Read Input Registers from Server
        * @param        startingAddress      Fist Address to read; Shifted by -1	
        * @param        quantity            Number of Inputs to read
        * @return       Input Registers from Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
	public int[] ReadInputRegisters(int startingAddress, int quantity, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
	{
		if (tcpClientSocket == null)
			throw new ConnectionException("connection Error");
		if (startingAddress > 65535 | quantity > 125)
			throw new IllegalArgumentException("Starting adress must be 0 - 65535; quantity must be 0 - 125");
		int[] response = new int[quantity];
		this.transactionIdentifier = toByteArray(0x0001);
		this.protocolIdentifier = toByteArray(0x0000);
		this.length = toByteArray(0x0006);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = 0x04;
		this.startingAddress = toByteArray(startingAddress);
		this.quantity = toByteArray(quantity);
		byte[] data = new byte[]
				{
					this.transactionIdentifier[1],
					this.transactionIdentifier[0],
					this.protocolIdentifier[1],
					this.protocolIdentifier[0],
					this.length[1],
					this.length[0],
					this.unitIdentifier,
					this.functionCode,
					this.startingAddress[1],
					this.startingAddress[0],
					this.quantity[1],
					this.quantity[0],
                    this.crc[0],
                    this.crc[1]		
				};
		if (tcpClientSocket.isConnected() | udpFlag)
		{
			if (udpFlag)
			{
				InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
				DatagramSocket clientSocket = new DatagramSocket();
				clientSocket.setSoTimeout(500);
			    clientSocket.send(sendPacket);
			    data = new byte[2100];
			    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			    clientSocket.receive(receivePacket);
			    data = receivePacket.getData();
			    clientSocket.close();
			}
			else
			{
				outStream.write(data, 0, data.length-2);
				if (sendDataChangedListener.size() > 0)
				{
					sendData = new byte[data.length-2];
					System.arraycopy(data, 0, sendData, 0, data.length-2);
					for (SendDataChangedListener hl : sendDataChangedListener)
						hl.SendDataChanged();
				}
				data = new byte[2100];
				int numberOfBytes = inStream.read(data, 0, data.length);
				if (receiveDataChangedListener.size() > 0)
				{
					receiveData = new byte[numberOfBytes];
					System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
					for (ReceiveDataChangedListener hl : receiveDataChangedListener)
						hl.ReceiveDataChanged();
				}
			}
			if (((byte) data[7]) == 0x84 & ((byte) data[8]) == 0x01)
				throw new FunctionCodeNotSupportedException("Function code not supported by master");
			if (((byte) data[7]) == 0x84 & ((byte) data[8]) == 0x02)
				throw new StartingAddressInvalidException("Starting adress invalid or starting adress + quantity invalid");
			if (((byte) data[7]) == 0x84 & ((byte) data[8]) == 0x03)
				throw new QuantityInvalidException("Quantity invalid");
			if (((byte) data[7]) == 0x84 & ((byte) data[8]) == 0x04)
				throw new ModbusException("Error reading");
			for (int i = 0; i < quantity; i++)
			{
				byte[] bytes = new byte[2];
				bytes[0] = (byte) data[9+i*2];
				bytes[1] = (byte) data[9+i*2+1];
				ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
				response[i] = byteBuffer.getShort();
			}
			
		}
		return (response);
	}
	
        /**
        * Write Single Coil to Server
        * @param        startingAddress      Address to write; Shifted by -1	
        * @param        value            Value to write to Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
    public void WriteSingleCoil(int startingAddress, boolean value, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
    {
        if (tcpClientSocket == null & !udpFlag)
            throw new ConnectionException("connection error");
        byte[] coilValue = new byte[2];
		this.transactionIdentifier = toByteArray(0x0001);
		this.protocolIdentifier = toByteArray(0x0000);
		this.length = toByteArray(0x0006);
		this.unitIdentifier = unitIdentifier;
		this.functionCode = 0x05;
		this.startingAddress = toByteArray(startingAddress);
        if (value == true)
        {
            coilValue = toByteArray((int)0xFF00);
        }
        else
        {
            coilValue = toByteArray((int)0x0000);
        }
        byte[] data = new byte[]{	this.transactionIdentifier[1],
						this.transactionIdentifier[0],
						this.protocolIdentifier[1],
						this.protocolIdentifier[0],
						this.length[1],
						this.length[0],
						this.unitIdentifier,
						this.functionCode,
						this.startingAddress[1],
						this.startingAddress[0],
						coilValue[1],
						coilValue[0],
	                    this.crc[0],
	                    this.crc[1]		
                        };
        if (tcpClientSocket.isConnected() | udpFlag)
        {
			if (udpFlag)
			{
				InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
				DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
				DatagramSocket clientSocket = new DatagramSocket();
				clientSocket.setSoTimeout(500);
			    clientSocket.send(sendPacket);
			    data = new byte[2100];
			    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			    clientSocket.receive(receivePacket);
			    data = receivePacket.getData();
			    clientSocket.close();
			}
			else
			{
				outStream.write(data, 0, data.length-2);
				if (sendDataChangedListener.size() > 0)
				{
					sendData = new byte[data.length-2];
					System.arraycopy(data, 0, sendData, 0, data.length-2);
					for (SendDataChangedListener hl : sendDataChangedListener)
						hl.SendDataChanged();
				}
				data = new byte[2100];
				int numberOfBytes = inStream.read(data, 0, data.length);
				if (receiveDataChangedListener.size() > 0)
				{
					receiveData = new byte[numberOfBytes];
					System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
					for (ReceiveDataChangedListener hl : receiveDataChangedListener)
						hl.ReceiveDataChanged();
				}
			}
        }
        if (data[7] == 0x85 & data[8] == 0x01)
            throw new FunctionCodeNotSupportedException("Function code not supported by master");
        if (data[7] == 0x85 & data[8] == 0x02)
            throw new StartingAddressInvalidException("Starting address invalid or starting address + quantity invalid");
        if (data[7] == 0x85 & data[8] == 0x03)
            throw new QuantityInvalidException("quantity invalid");
        if (data[7] == 0x85 & data[8] == 0x04)
            throw new ModbusException("error reading");
    }
    
        /**
        * Write Single Register to Server
        * @param        startingAddress      Address to write; Shifted by -1	
        * @param        value            Value to write to Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
    public void WriteSingleRegister(int startingAddress, int value, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
    {
        if (tcpClientSocket == null & !udpFlag)
            throw new ConnectionException("connection error");
        byte[] registerValue = new byte[2];
        this.transactionIdentifier = toByteArray((int)0x0001);
        this.protocolIdentifier = toByteArray((int)0x0000);
        this.length = toByteArray((int)0x0006);
        this.unitIdentifier = unitIdentifier;
        this.functionCode = 0x06;
        this.startingAddress = toByteArray(startingAddress);
            registerValue = toByteArray((short)value);

        byte[] data = new byte[]{	this.transactionIdentifier[1],
						this.transactionIdentifier[0],
						this.protocolIdentifier[1],
						this.protocolIdentifier[0],
						this.length[1],
						this.length[0],
						this.unitIdentifier,
						this.functionCode,
						this.startingAddress[1],
						this.startingAddress[0],
						registerValue[1],
						registerValue[0],
	                    this.crc[0],
	                    this.crc[1]		
                        };
		if (udpFlag)
		{
			InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(500);
		    clientSocket.send(sendPacket);
		    data = new byte[2100];
		    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		    clientSocket.receive(receivePacket);
		    data = receivePacket.getData();
		    clientSocket.close();
		}
		else
		{
			outStream.write(data, 0, data.length-2);
			if (sendDataChangedListener.size() > 0)
			{
				sendData = new byte[data.length-2];
				System.arraycopy(data, 0, sendData, 0, data.length-2);
				for (SendDataChangedListener hl : sendDataChangedListener)
					hl.SendDataChanged();
			}
			data = new byte[2100];
			int numberOfBytes = inStream.read(data, 0, data.length);
			if (receiveDataChangedListener.size() > 0)
			{
				receiveData = new byte[numberOfBytes];
				System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
				for (ReceiveDataChangedListener hl : receiveDataChangedListener)
					hl.ReceiveDataChanged();
			}
		}
        if (data[7] == 0x86 & data[8] == 0x01)
            throw new FunctionCodeNotSupportedException("Function code not supported by master");
        if (data[7] == 0x86 & data[8] == 0x02)
            throw new StartingAddressInvalidException("Starting address invalid or starting address + quantity invalid");
        if (data[7] == 0x86 & data[8] == 0x03)
            throw new QuantityInvalidException("quantity invalid");
        if (data[7] == 0x86 & data[8] == 0x04)
            throw new ModbusException("error reading");
    }
    
       /**
        * Write Multiple Coils to Server
        * @param        startingAddress      Firts Address to write; Shifted by -1	
        * @param        values           Values to write to Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
    public void WriteMultipleCoils(int startingAddress, boolean[] values, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
    {
        byte byteCount = (byte)(values.length/8+1);
        byte[] quantityOfOutputs = toByteArray((int)values.length);
        byte singleCoilValue = 0;
        if (tcpClientSocket == null & !udpFlag)
            throw new ConnectionException("connection error");
        this.transactionIdentifier = toByteArray((int)0x0001);
        this.protocolIdentifier = toByteArray((int)0x0000);
        this.length = toByteArray((int)(7+(values.length/8+1)));
        this.unitIdentifier = unitIdentifier;
        this.functionCode = 0x0F;
        this.startingAddress = toByteArray(startingAddress);

        byte[] data = new byte[16 + values.length/8];
        data[0] = this.transactionIdentifier[1];
        data[1] = this.transactionIdentifier[0];
        data[2] = this.protocolIdentifier[1];
        data[3] = this.protocolIdentifier[0];
		data[4] = this.length[1];
		data[5] = this.length[0];
		data[6] = this.unitIdentifier;
		data[7] = this.functionCode;
		data[8] = this.startingAddress[1];
		data[9] = this.startingAddress[0];
        data[10] = quantityOfOutputs[1];
        data[11] = quantityOfOutputs[0];
        data[12] = byteCount;
        for (int i = 0; i < values.length; i++)
        {
            if ((i % 8) == 0)
                singleCoilValue = 0;
            byte CoilValue;
            if (values[i] == true)
                CoilValue = 1;
            else
                CoilValue = 0;


            singleCoilValue = (byte)((int)CoilValue<<(i%8) | (int)singleCoilValue);

            data[13 + (i / 8)] = singleCoilValue;            
        }
        data[data.length - 2] = this.crc[0];
        data[data.length - 1] = this.crc[1];
		if (udpFlag)
		{
			InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(500);
		    clientSocket.send(sendPacket);
		    data = new byte[2100];
		    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		    clientSocket.receive(receivePacket);
		    data = receivePacket.getData();
		    clientSocket.close();
		}
		else
		{
			outStream.write(data, 0, data.length-2);
			if (sendDataChangedListener.size() > 0)
			{
				sendData = new byte[data.length-2];
				System.arraycopy(data, 0, sendData, 0, data.length-2);
				for (SendDataChangedListener hl : sendDataChangedListener)
					hl.SendDataChanged();
			}
			data = new byte[2100];
			int numberOfBytes = inStream.read(data, 0, data.length);
			if (receiveDataChangedListener.size() > 0)
			{
				receiveData = new byte[numberOfBytes];
				System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
				for (ReceiveDataChangedListener hl : receiveDataChangedListener)
					hl.ReceiveDataChanged();
			}
		}
        if (data[7] == 0x8F & data[8] == 0x01)
            throw new FunctionCodeNotSupportedException("Function code not supported by master");
        if (data[7] == 0x8F & data[8] == 0x02)
            throw new StartingAddressInvalidException("Starting address invalid or starting address + quantity invalid");
        if (data[7] == 0x8F & data[8] == 0x03)
            throw new QuantityInvalidException("quantity invalid");
        if (data[7] == 0x8F & data[8] == 0x04)
            throw new ModbusException("error reading");
    }
    
        /**
        * Write Multiple Registers to Server
        * @param        startingAddress      Firts Address to write; Shifted by -1	
        * @param        values           Values to write to Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */    
    public void WriteMultipleRegisters(int startingAddress, int[] values, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException

    {
        byte byteCount = (byte)(values.length * 2);
        byte[] quantityOfOutputs = toByteArray((int)values.length);
        if (tcpClientSocket == null & !udpFlag)
            throw new ConnectionException("connection error");
        this.transactionIdentifier = toByteArray((int)0x0001);
        this.protocolIdentifier = toByteArray((int)0x0000);
        this.length = toByteArray((int)(7+values.length*2));
        this.unitIdentifier = unitIdentifier;
        this.functionCode = 0x10;
        this.startingAddress = toByteArray(startingAddress);

        byte[] data = new byte[15 + values.length*2];
        data[0] = this.transactionIdentifier[1];
        data[1] = this.transactionIdentifier[0];
        data[2] = this.protocolIdentifier[1];
        data[3] = this.protocolIdentifier[0];
        data[4] = this.length[1];
        data[5] = this.length[0];
        data[6] = this.unitIdentifier;
        data[7] = this.functionCode;
        data[8] = this.startingAddress[1];
        data[9] = this.startingAddress[0];
        data[10] = quantityOfOutputs[1];
        data[11] = quantityOfOutputs[0];
        data[12] = byteCount;
        for (int i = 0; i < values.length; i++)
        {
            byte[] singleRegisterValue = toByteArray((int)values[i]);
            data[13 + i*2] = singleRegisterValue[1];
            data[14 + i*2] = singleRegisterValue[0];
        }
        data[data.length - 2] = this.crc[0];
        data[data.length - 1] = this.crc[1];
		if (udpFlag)
		{
			InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(500);
		    clientSocket.send(sendPacket);
		    data = new byte[2100];
		    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		    clientSocket.receive(receivePacket);
		    data = receivePacket.getData();
		    clientSocket.close();
		}
		else
		{
			outStream.write(data, 0, data.length-2);
			if (sendDataChangedListener.size() > 0)
			{
				sendData = new byte[data.length-2];
				System.arraycopy(data, 0, sendData, 0, data.length-2);
				for (SendDataChangedListener hl : sendDataChangedListener)
					hl.SendDataChanged();
			}
			data = new byte[2100];
			int numberOfBytes = inStream.read(data, 0, data.length);
			if (receiveDataChangedListener.size() > 0)
			{
				receiveData = new byte[numberOfBytes];
				System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
				for (ReceiveDataChangedListener hl : receiveDataChangedListener)
					hl.ReceiveDataChanged();
			}
		}
        if (data[7] == 0x90 & data[8] == 0x01)
            throw new FunctionCodeNotSupportedException("Function code not supported by master");
        if (data[7] == 0x90 & data[8] == 0x02)
            throw new StartingAddressInvalidException("Starting address invalid or starting address + quantity invalid");
        if (data[7] == 0x90 & data[8] == 0x03)
            throw new QuantityInvalidException("quantity invalid");
        if (data[7] == 0x90 & data[8] == 0x04)
            throw new ModbusException("error reading");
    }
	
        /**
        * Read and Write Multiple Registers to Server
        * @param        startingAddressRead      Firts Address to Read; Shifted by -1	
        * @param        quantityRead            Number of Values to Read
        * @param        startingAddressWrite      Firts Address to write; Shifted by -1	
        * @param        values                  Values to write to Server
        * @return       Register Values from Server
        * @throws ModbusException
        * @throws UnknownHostException
        * @throws SocketException
        */
    public int[] ReadWriteMultipleRegisters(int startingAddressRead, int quantityRead, int startingAddressWrite, int[] values, byte unitIdentifier) throws ModbusException,
                UnknownHostException, SocketException, IOException
    {
        byte [] startingAddressReadLocal = new byte[2];
	    byte [] quantityReadLocal = new byte[2];
        byte[] startingAddressWriteLocal = new byte[2];
        byte[] quantityWriteLocal = new byte[2];
        byte writeByteCountLocal = 0;
        if (tcpClientSocket == null & !udpFlag)
            throw new ConnectionException("connection error");
        if (startingAddressRead > 65535 | quantityRead > 125 | startingAddressWrite > 65535 | values.length > 121)
            throw new IllegalArgumentException("Starting address must be 0 - 65535; quantity must be 0 - 125");
        int[] response;
        this.transactionIdentifier = toByteArray((int)0x0001);
        this.protocolIdentifier = toByteArray((int)0x0000);
        this.length = toByteArray((int)0x0006);
        this.unitIdentifier = unitIdentifier;
        this.functionCode = 0x17;
        startingAddressReadLocal = toByteArray(startingAddressRead);
        quantityReadLocal = toByteArray(quantityRead);
        startingAddressWriteLocal = toByteArray(startingAddressWrite);
        quantityWriteLocal = toByteArray(values.length);
        writeByteCountLocal = (byte)(values.length * 2);
        byte[] data = new byte[19+ values.length*2];
        data[0] =               this.transactionIdentifier[1];
        data[1] =   		    this.transactionIdentifier[0];
		data[2] =   			this.protocolIdentifier[1];
		data[3] =   			this.protocolIdentifier[0];
		data[4] =   			this.length[1];
		data[5] =   			this.length[0];
		data[6] =   			this.unitIdentifier;
		data[7] =   		    this.functionCode;
		data[8] =   			startingAddressReadLocal[1];
		data[9] =   			startingAddressReadLocal[0];
		data[10] =   			quantityReadLocal[1];
		data[11] =   			quantityReadLocal[0];
        data[12] =               startingAddressWriteLocal[1];
		data[13] =   			startingAddressWriteLocal[0];
		data[14] =   			quantityWriteLocal[1];
		data[15] =   			quantityWriteLocal[0];
        data[16] =              writeByteCountLocal;

        for (int i = 0; i < values.length; i++)
        {
            byte[] singleRegisterValue = toByteArray((int)values[i]);
            data[17 + i*2] = singleRegisterValue[1];
            data[18 + i*2] = singleRegisterValue[0];
        }
        data[data.length - 2] = this.crc[0];
        data[data.length - 1] = this.crc[1];
		if (udpFlag)
		{
			InetAddress ipAddress = InetAddress.getByName(this.ipAddress);
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, this.port);
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(500);
		    clientSocket.send(sendPacket);
		    data = new byte[2100];
		    DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		    clientSocket.receive(receivePacket);
		    data = receivePacket.getData();
		    clientSocket.close();
		}
		else
		{
			outStream.write(data, 0, data.length-2);
			if (sendDataChangedListener.size() > 0)
			{
				sendData = new byte[data.length-2];
				System.arraycopy(data, 0, sendData, 0, data.length-2);
				for (SendDataChangedListener hl : sendDataChangedListener)
					hl.SendDataChanged();
			}
			data = new byte[2100];
			int numberOfBytes = inStream.read(data, 0, data.length);
			if (receiveDataChangedListener.size() > 0)
			{
				receiveData = new byte[numberOfBytes];
				System.arraycopy(data, 0, receiveData, 0, numberOfBytes);
				for (ReceiveDataChangedListener hl : receiveDataChangedListener)
					hl.ReceiveDataChanged();
			}
		}
        if (data[7] == 0x97 & data[8] == 0x01)
            throw new FunctionCodeNotSupportedException("Function code not supported by master");
        if (data[7] == 0x97 & data[8] == 0x02)
            throw new StartingAddressInvalidException("Starting address invalid or starting address + quantity invalid");
        if (data[7] == 0x97 & data[8] == 0x03)
            throw new QuantityInvalidException("quantity invalid");
        if (data[7] == 0x97 & data[8] == 0x04)
            throw new ModbusException("error reading");
        response = new int[quantityRead];
        for (int i = 0; i < quantityRead; i++)
        {
            byte lowByte;
            byte highByte;
            highByte = data[9 + i * 2];
            lowByte = data[9 + i * 2 + 1];
            
            byte[] bytes = new byte[] {highByte, lowByte};
            
            
			ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
			response[i] = byteBuffer.getShort();
        }
        return (response);
    }
    
        /**
        * Close connection to Server
        * @throws IOException
        */
	public void Disconnect() throws IOException
	{
		
		inStream.close();
		outStream.close();
		tcpClientSocket.close();
	}
	
	
	public static byte[] toByteArray(int value)
    {
		byte[] result = new byte[2];
	    result[1] = (byte) (value >> 8);
		result[0] = (byte) (value);
	    return result;
	}

	public static byte[] toByteArrayDouble(int value)
    {
		return ByteBuffer.allocate(4).putInt(value).array();
	}
	
	public static byte[] toByteArray(float value)
    {
		 return ByteBuffer.allocate(4).putFloat(value).array();
	}
	
        /**
        * client connected to Server
        * @return  if Client is connected to Server
        */
	public boolean isConnected()
	{
		boolean returnValue = false;
		if (tcpClientSocket == null)
			returnValue = false;
		else
		{
			if (tcpClientSocket.isConnected())
				returnValue = true;
			else
				returnValue = false;
		}
		return returnValue;
	}
	
        /**
        * Returns ip Address of Server
        * @return ip address of server
        */
	public String getipAddress()
	{
		return ipAddress;
	}
        
         /**
        * sets IP-Address of server
        * @param        ipAddress                  ipAddress of Server
        */
	public void setipAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	
        /**
        * Returns port of Server listening
        * @return port of Server listening
        */
	public int getPort()
	{
		return port;
	}
        
        /**
        * sets Portof server
        * @param        port                  Port of Server
        */
	public void setPort(int port)
	{
		this.port = port;
	}	
	
        /**
        * Returns UDP-Flag which enables Modbus UDP and disabled Modbus TCP
        * @return UDP Flag
        */
	public boolean getUDPFlag()
	{
		return udpFlag;
	}
        
        /**
        * sets UDP-Flag which enables Modbus UDP and disables Mopdbus TCP
        * @param        udpFlag      UDP Flag
        */
	public void setUDPFlag(boolean udpFlag)
	{
		this.udpFlag = udpFlag;
	}
	
	public int getConnectionTimeout()
	{
		return connectTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout)
	{
		this.connectTimeout = connectionTimeout;
	}
        
        public void addReveiveDataChangedListener(ReceiveDataChangedListener toAdd) 
        {
            receiveDataChangedListener.add(toAdd);
        }
        public void addSendDataChangedListener(SendDataChangedListener toAdd) 
        {
            sendDataChangedListener.add(toAdd);
        }	
	
}                                                                                                