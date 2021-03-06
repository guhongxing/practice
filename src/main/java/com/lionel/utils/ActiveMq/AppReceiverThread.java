package com.lionel.utils.ActiveMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppReceiverThread extends Thread{
	
	private String name;
	AppReceiverThread(String name){
		this.name=name;
	}
	
	public String getThreadName() {
		return name;
	}
	
	@Override
	public void run() {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 客户端到JMS Provider 的连接  
        Connection connection = null;  
        // Session： 一个发送或接收消息的线程  
        Session session;  
        // Destination ：消息的目的地;消息发送给谁.  
        Destination destination;  
        // 消费者，消息接收者  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
        try {  
            // 构造从工厂得到连接对象  
            connection = connectionFactory.createConnection();  
            // 启动  
            connection.start();  
            // 获取操作连接  
            session = connection.createSession(Boolean.FALSE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
            destination = session.createQueue("queue-test ");  
            consumer = session.createConsumer(destination);  
            while (true) {  
                // 设置接收者接收消息的时间，为了便于测试，这里谁定为100s  
                Message message = consumer.receive(100000);  
                if (null != message) {
                	if(message instanceof TextMessage) {
                		TextMessage textMessage=(TextMessage)message;
                		System.out.println(name +"收到消息" + textMessage.getText());  
                	}else if(message instanceof MapMessage) {
                		MapMessage mapMessage=(MapMessage)message;
                		System.out.println(name +"收到消息" + mapMessage.getString("key1"));
                	}
                	
                } else {  
                    break;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection)  
                    connection.close();  
            } catch (Throwable ignore) {  
            }  
        }  
	}

}
