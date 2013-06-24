package me.huzorro.simple;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class ByteBufferTest implements Serializable {
	private static final long serialVersionUID = -2108924530736607020L;
	private  byte[] bytes;
	public ByteBufferTest() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the byteBuffer
	 */
	public byte[] getByteBuffer() {
		return bytes;
	}
	/**
	 * @param byteBuffer the byteBuffer to set
	 */
	public void setByteBuffer(byte[] bytes) {
		this.bytes = bytes;
	}
	
	
	
}
