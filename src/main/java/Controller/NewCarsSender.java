package Controller;

import Exeptions.MyExceptionListener;
import model.Car;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class NewCarsSender {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "Topic1";

    public static void main(String[] args) throws JMSException, InterruptedException {

        while (true) {

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

            //Destination represents here our queue 'JCG_QUEUE' on the JMS server.
            //The queue will be created automatically on the server.
            Destination destination = session.createQueue(subject);

            // MessageProducer is used for sending messages to the queue.
            MessageProducer producer = session.createProducer(destination);

            // We will send a object "book"

            ObjectMessage message = session.createObjectMessage(car);

            // Here we are sending our message!
            producer.send(message);
            connection.close();
            Thread.sleep(1000);
        }
    }
}
