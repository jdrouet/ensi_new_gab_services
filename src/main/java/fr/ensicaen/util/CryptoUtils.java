package fr.ensicaen.util;

public class CryptoUtils {

	public static byte[] xor(byte[] data, byte[] key) {
		byte[] ret = new byte[data.length];
		for (int i = 0; i < data.length; i++) {
			ret[i] = (byte) (((int) data[i] ^ (int) key[i % key.length]) & 0xff);
		}
		return ret;
	}

}
