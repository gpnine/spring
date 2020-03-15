package com.zcl.study.spring.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class CurrentServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true){
				Socket socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
