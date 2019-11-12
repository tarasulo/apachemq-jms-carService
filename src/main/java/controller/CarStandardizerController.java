package controller;

import authentication.Authentication;
import messageListeners.StandardizerMessageListener;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CarStandardizerController {
    final static Logger logger = LoggerFactory.getLogger(CarStandardizerController.class);
    private static String url;
    private static String subject;
    private static boolean auth = false;

    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);
            subject = prop.getProperty("subject2");
            url = prop.getProperty("url");
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

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
