package com.pallasli.study.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	private static String brokerURL = "tcp://localhost:61616";
	private static transient ConnectionFactory factory;
	private transient Connection connection;
	private transient Session session;

	public Consumer() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public void close() throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	public static void main(String[] args) {
		args = new String[1];
		args[0] = "ORCL";
		Consumer consumer;
		try {
			consumer = new Consumer();
			for (String stock : args) {
				Destination destination = consumer.getSession().createTopic(
						"STOCKS." + stock);
				MessageConsumer messageConsumer = consumer.getSession()
						.createConsumer(destination);
				messageConsumer.setMessageListener(new Listener());
			}
			// consumer.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

}