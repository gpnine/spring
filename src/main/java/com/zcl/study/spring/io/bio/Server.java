package com.zcl.study.spring.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept(); // 没有客户端时，accept方法会一直停在这
			InputStream in = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				System.out.println(new String(buffer, 0, len));
			}
			OutputStream out = socket.getOutputStream();
			out.write("hello".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
