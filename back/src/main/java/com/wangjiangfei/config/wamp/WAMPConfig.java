package com.wangjiangfei.config.wamp;

/**
 * @author wangjiangfei
 * @date 2019/6/2 18:37
 * @description
 */
import io.crossbar.autobahn.wamp.Client;
import io.crossbar.autobahn.wamp.Session;
import io.crossbar.autobahn.wamp.interfaces.ISession;
import io.crossbar.autobahn.wamp.types.CloseDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiangfei
 * @date 2019/1/14
 */
//@Configuration
//public class WAMPConfig {
//    @Value("${wamp.url}")
//    private String url;
//    @Value("${wamp.realm}")
//    private String realm;
//    private static volatile boolean canSend = false;
//    @Bean
//    public Session getSession(){
//        Session session = new Session();
//        session.addOnConnectListener(new ISession.OnConnectListener() {
//            @Override
//            public void onConnect(Session session) {
//                int a = 0;
//                if(session.isConnected()){
//                    canSend = true;//连接后 检查是否能发送消息  并把canSend置为true
//                }
//                System.out.println("connect");
//            }
//        });
//        session.addOnLeaveListener(new ISession.OnLeaveListener() {
//            @Override
//            public void onLeave(Session session, CloseDetails closeDetails) {
//                System.out.println("onLeave");
//                canSend = false;
//            }
//        });
//        session.addOnDisconnectListener(new ISession.OnDisconnectListener() {
//            @Override
//            public void onDisconnect(Session session, boolean b) {
//                System.out.println("disconnect");
//            }
//        });
//        Client client = new Client(session,url,realm);
//        client.connect();
//        return session;
//    }
//
//    /**
//     * 检查是否能发送消息
//     * @return
//     */
//    public static boolean isCanSend() {
//        return canSend;
//    }
//}
