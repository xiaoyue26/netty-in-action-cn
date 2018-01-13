package nia.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 1.3 使用Future, 在连接事件上添加监听器Listener
 */
public class ConnectExample {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect() {
        Channel channel = CHANNEL_FROM_SOMEWHERE; //reference form somewhere

        // (1)获得连接事件Future
        ChannelFuture future = channel.connect(
                new InetSocketAddress("192.168.0.1", 25));

        // (2)注册Listener
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {

                    ByteBuf buffer = Unpooled.copiedBuffer(
                            "Hello", Charset.defaultCharset());
                    // (3)获得写事件Future
                    ChannelFuture wf = future.channel()
                            .writeAndFlush(buffer);
                    // ... 链式,没玩没了获得Future
                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}