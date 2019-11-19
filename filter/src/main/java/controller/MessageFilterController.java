package controller;

import authentication.Authentication;
import messageListeners.FilterMessageListener;
import exeptions.MyExceptionListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageFilterController {

    private final static Logger logger = LoggerFactory.getLogger(MessageFilterController.class);
    private static String url;
    private static String subject;
    private static boolean auth = false;

    static {
        try {
            subject = getPropValues("subject1");
            url = getPropValues("url");
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

    private static String getPropValues(String prop) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(input);
        return properties.getProperty(prop);
    }

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {

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
