package com.zcl.study.spring.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class ExecutorsCurrentServer {

	public static void main(String[] args) {
		// 不适用于高并发
		ExecutorService executorService = Executors.newCachedThreadPool(); // newFixedThreadPool(60);
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true){
				Socket socket = serverSocket.accept();
				executorService.submit(new ServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
