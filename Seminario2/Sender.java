package org.sample.jms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sasikala on 5/6/16.
 */
public class Sender {
    public static final String QPID_ICF = "org.wso2.andes.jndi.PropertiesFileInitialContextFactory";
    private static final String CF_NAME_PREFIX = "connectionfactory.";
    private static final String QUEUE_NAME_PREFIX = "queue.";
    private static final String CF_NAME = "qpidConnectionfactory";
    String userName = "admin";
    String password = "admin";
    private static String CARBON_CLIENT_ID = "carbon";
    private static String CARBON_VIRTUAL_HOST_NAME = "carbon";
    private static String CARBON_DEFAULT_HOSTNAME = "157.88.125.106";
    private static String CARBON_DEFAULT_PORT = "5672";
    String queueName = "Ejemplo";
    private QueueConnection queueConnection;
    private QueueSession queueSession;

    public void sendMessages(int numberOfMessagesToSend) throws NamingException, JMSException, UnknownHostException, UnknownHostException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, QPID_ICF);
        properties.put(CF_NAME_PREFIX + CF_NAME, getTCPConnectionURL(userName, password));
        properties.put(QUEUE_NAME_PREFIX + queueName, queueName);
        InitialContext ctx = new InitialContext(properties);
        // Lookup connection factory
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup(CF_NAME);
        queueConnection = connFactory.createQueueConnection();
        queueConnection.start();
        queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue)ctx.lookup(queueName);
        javax.jms.QueueSender queueSender = queueSession.createSender(queue);

        // Send message
        for (int i=0; i<numberOfMessagesToSend; i++) {
            // create the messages to send
            TextMessage textMessage = queueSession.createTextMessage(InetAddress.getLocalHost().getHostAddress() + " - Abel");
            queueSender.send(textMessage);
        }
        System.out.println("Sent message count: " + numberOfMessagesToSend);
        queueSender.close();
        queueSession.close();
        queueConnection.close();
    }
    private String getTCPConnectionURL(String username, String password) {
        // amqp://{username}:{password}@carbon/carbon?brokerlist='tcp://{hostname}:{port}'
        return new StringBuffer()
                .append("amqp://").append(username).append(":").append(password)
                .append("@").append(CARBON_CLIENT_ID)
                .append("/").append(CARBON_VIRTUAL_HOST_NAME)
                .append("?brokerlist='tcp://").append(CARBON_DEFAULT_HOSTNAME).append(":").append(CARBON_DEFAULT_PORT).append("'")
                .toString();
    }

    public static void main(String[] args) throws JMSException, NamingException {
        Sender publisher = new Sender();
        try {
            while(true) {
                publisher.sendMessages(1);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getCause().toString());
        }
    }
}
