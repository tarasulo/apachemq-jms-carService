package controller;

import authentication.Authentication;
import messageListeners.StandardizerMessageListener;
import exeptions.MyExceptionListener;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class CarStandardizerController {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "Topic2";
    private static boolean auth = false;

    public static void main(String[] args) throws JMSException {

        auth = Authentication.isValid();

        if (auth == true) {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.setExceptionListener(new MyExceptionListener());
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new StandardizerMessageListener("CarsStandardizer"));
            connection.start();
        }
    }
}
