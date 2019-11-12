package messageListeners;

import exeptions.MyExceptionListener;
import model.Car;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilterMessageListener implements MessageListener {
    final static Logger logger = LoggerFactory.getLogger(FilterMessageListener.class);
    private String consumerName;
    private Car tempCar = null;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = getPropValues("subject2");

    public String getPropValues(String prop) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(input);
        return properties.getProperty(prop);
    }

    public FilterMessageListener(String consumerName) throws IOException {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            logger.info(consumerName + " received "
                    + objectMessage.getObject().toString());
            tempCar = (Car) objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }

        // Cars filter starts
        if (tempCar.getEngine() > 2.0 & tempCar.getYear() > 2000) {
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                Connection connection = connectionFactory.createConnection();
                connection.start();
                connection.setExceptionListener(new MyExceptionListener());
                Session session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE);

                Destination destination = session.createQueue(subject);

                MessageProducer producer = session.createProducer(destination);

                ObjectMessage message2 = session.createObjectMessage(tempCar);

                producer.send(message2);
                connection.close();
            } catch (JMSException e) {
                logger.error(String.valueOf(e));
            }
        } else {
            logger.info("Sorry, these " + tempCar + ", does not meet the filtering requirements");
        }
    }
}
