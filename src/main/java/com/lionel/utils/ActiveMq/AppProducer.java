package com.lionel.utils.ActiveMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.jms.Destination;
import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {
	public static final String url = "tcp://localhost:61616";
	public static final String queueName = "queue-test ";

	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

		// 2.创建Connection
		Connection connection = connectionFactory.createConnection();

		// 3.启动连接
		connection.start();

		// 4.创建会话 第一个参数：是否在事务中去处理， 第二个参数.应答模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 5.创建一个目标
		Destination destination = session.createQueue(queueName);

		// 6创建一个生产者
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 100; i++) {
			// 7.创基建消息
			if (i % 2 == 0) {
				TextMessage textMessage = session.createTextMessage("test:" + i);
				producer.send(textMessage);
				System.out.println("发送消息:" + textMessage.getText());
			} else {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("key1", "value1");
				mapMessage.setString("key2", "value2");
				mapMessage.setString("key3", "value3");
				mapMessage.setString("key4", "value4");
				producer.send(mapMessage);
				System.out.println("发送消息:" + mapMessage.getString("key1"));
			}
		}
		// 9关闭连接
		connection.close();
	}

	public void Produce1() throws JMSException {
		// 1.创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

		// 2.创建Connection
		Connection connection = connectionFactory.createConnection();

		// 3.启动连接
		connection.start();

		// 4.创建会话 第一个参数：是否在事务中去处理， 第二个参数.应答模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 5.创建一个目标
		Destination destination = session.createQueue(queueName);
		Destination destination1 = session.createQueue("queue1");
		
		// 6创建一个生产者
		MessageProducer producer = session.createProducer(destination);
		
		for (int i = 0; i < 100; i++) {
			// 7.创基建消息
			if (i % 2 == 0) {
				TextMessage textMessage = session.createTextMessage("test:" + i);
				producer.send(textMessage);
				producer.send(destination1, textMessage);
				System.out.println("发送消息:" + textMessage.getText());
			} else {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("key1", "value1");
				mapMessage.setString("key2", "value2");
				mapMessage.setString("key3", "value3");
				mapMessage.setString("key4", "value4");
				producer.send(mapMessage);
				System.out.println("发送消息:" + mapMessage.getString("key1"));
			}
		}
		// 9关闭连接
		connection.close();
	}

}
