package net.h2.web.core.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class Base64EncodeDecode {

	public static String convertToBase64String(File in) throws IOException {

		DataInputStream dis = new DataInputStream(new FileInputStream(in));

		int available = dis.available();
		byte[] buffer = new byte[available];
		dis.read(buffer);
		Base64 base64 = new Base64();
		byte[] encoded = base64.encode(buffer);
		dis.close();

		System.out.println("Done");

		return new String(encoded, "UTF-8");
	}

	/*
	 * public static void convertBase64StringToFile(String data, File out)
	 * throws IOException {
	 * 
	 * DataOutputStream dos = new DataOutputStream(new FileOutputStream(out));
	 * 
	 * byte[] input = data.getBytes("UTF-8");
	 * 
	 * Base64 base64 = new Base64(); byte[] decoded = base64.decode(input);
	 * dos.write(decoded); dos.flush(); dos.close();
	 * 
	 * System.out.println("Done"); }
	 */

	public static String convertByteArrayToBase64String(byte[] input)
			throws UnsupportedEncodingException {
		if (input == null) {
			return null;
		}
		Base64 base64 = new Base64();
		byte[] encoded = base64.encode(input);
		return new String(encoded, "UTF-8");
	}

	public static String convertFileToBase64String(File file)
			throws IOException {
		byte[] byteArray = new byte[(int) file.length()];
		byteArray = FileUtils.readFileToByteArray(file);
		return convertByteArrayToBase64String(byteArray);
	}

	public static byte[] convertBase64StringToByteArray(String input)
			throws UnsupportedEncodingException {
		if (input == null) {
			return null;
		}
		Base64 base64 = new Base64();
		byte[] bytes = input.getBytes("UTF-8");
		byte[] decoded = base64.decode(bytes);
		return decoded;
	}

	public static void convertBase64StringToFile(String input, File out)
			throws IOException {
		byte[] byteArray = convertBase64StringToByteArray(input);
		FileUtils.writeByteArrayToFile(out, byteArray);
	}

}
