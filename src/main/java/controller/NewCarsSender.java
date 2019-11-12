package controller;

import authentication.Authentication;
import exeptions.MyExceptionListener;
import model.Car;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NewCarsSender {

    final static Logger logger = LoggerFactory.getLogger(NewCarsSender.class);
    private static String url;
    private static String subject;
    private static boolean auth = false;

    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);
            subject = prop.getProperty("subject1");
            url = prop.getProperty("url");
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

    public static void main(String[] args) throws JMSException, InterruptedException {

        auth = Authentication.isValid();

        while (auth) {

            //Creating new car
            Car car = new Car();
            // Getting JMS connection from the server and starting it
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(new MyExceptionListener());
            //Creating a non transactional session to send/receive JMS message.
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            //Destination represents here our queue 'Topic1' on the JMS server.
            //The queue will be created automatically on the server.
            Destination destination = session.createQueue(subject);

            // MessageProducer is used for sending messages to the queue.
            MessageProducer producer = session.createProducer(destination);

            // We will send a object "car"
            ObjectMessage message = session.createObjectMessage(car);

            producer.send(message);
            connection.close();
            // We should wait for 1 sec for the next car creating
            Thread.sleep(1000);
        }
    }
}
