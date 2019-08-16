package com.wangjiangfei.config.wamp;

/**
 * @author wangjiangfei
 * @date 2019/6/2 18:38
 * @description
 */

import io.crossbar.autobahn.wamp.Session;
import io.crossbar.autobahn.wamp.types.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangjiangfei
 * @date 2019/1/14
 */
//@Component
//public class WAMPUtils {
//    @Autowired
//    Session session;
//    public String sendMsg(String topic,String content){
//        if(WAMPConfig.isCanSend()){
//            CompletableFuture<Publication> pubFuture =  session.publish(topic,content);
//            pubFuture.thenAccept(accept->{
//                System.out.println("publish to " + topic);
//            });
//            return "ok";
//        }else{
//            return "error";
//        }
//    }
//}
//    public String subscribe(String topic){
//        if(WAMPConfig.isCanSend()){
//            CompletableFuture<Subscription> subFuture = session.subscribe(topic,
//                    this::onEvent);
//            subFuture.whenComplete((subscription, throwable) -> {
//                if (throwable == null) {
//                    // We have successfully subscribed.
//                    System.out.println("Subscribed to topic " + subscription.topic);
//                } else {
//                    // Something went bad.
//                    throwable.printStackTrace();
//                }
//            });
//            return "ok";
//        }else{
//            return "error";
//        }
//    }
//    private void onEvent(List<Object> args) {
//        System.out.println(String.format("Got event: %s", args.get(0)));
//    }
