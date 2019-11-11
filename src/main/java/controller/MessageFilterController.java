package controller;

import authentication.Authentication;
import messageListeners.FilterMessageListener;
import exeptions.MyExceptionListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class MessageFilterController {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    // Name of the queue we will receive messages from
    private static String subject = "Topic1";

    private static boolean auth = false;

    public static void main(String[] args) throws JMSException {

        auth = Authentication.isValid();

        if (auth == true) {
            // Getting JMS connection from the server
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.setExceptionListener(new MyExceptionListener());
            // Creating session for receiving messages
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // Getting the queue 'Topic1'
            Destination destination = session.createQueue(subject);

            // MessageConsumer is used for receiving (consuming) messages
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new FilterMessageListener("CarsFilter"));
            connection.start();
        }
    }
}
