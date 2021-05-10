package com.zcl.study;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class TestNio {
    @Test
    public void client() throws IOException {
        //1. 获取通道
        SocketChannel schannel
                = SocketChannel.open(new InetSocketAddress("192.168.206.8", 9898));
        //2.切换非阻塞模式
        schannel.configureBlocking(false);
        //3.分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4.发送数据给服务端
        System.out.println("Input: ");
        Scanner scanner = new Scanner(System.in);
        String strs = scanner.next();
        System.out.println(strs);
        while (scanner.hasNext()) {
            String str = scanner.next();
            byteBuffer.put((new Date().toString() + str).getBytes());
            byteBuffer.flip();
            schannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //5.关闭通道
        schannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);
        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //4. 获取选择器
        Selector selector = Selector.open();
        //5. 将通道注册到选择器上面，并指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6. 轮询 选择上面已经准备就绪的事件
        while (selector.select() > 0) {
            //7. 获取当前选择器中所有注册的选择键(已就绪的监听事件)
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8. 获取准备 就绪 的事件
                SelectionKey sk = it.next();
                //9. 判断具体是什么事件
                if (sk.isAcceptable()) {
                    //10. 若是 连接事件就绪， 获取客户端连接
                    SocketChannel schannel = ssChannel.accept();
                    //11. 切换非阻塞模式
                    schannel.configureBlocking(false);
                    //12. 注册到选择器上面
                    schannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13. 若是读就绪 获取通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14. 读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                //15. 取消选择键 SelectionKey
                it.remove();
            }
        }
    }
}
