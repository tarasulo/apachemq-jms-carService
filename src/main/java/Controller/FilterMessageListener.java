package Controller;

import Exeptions.MyExceptionListener;
import model.Car;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class FilterMessageListener implements MessageListener {
    final static Logger logger = Logger.getLogger(FilterMessageListener.class);
    private String consumerName;
    private Car tempCar = null;
    private  String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private  String subject = "Topic2";

    public FilterMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            System.out.println(consumerName + " received "
                    + objectMessage.getObject().toString());
            logger.info(consumerName + " received "
                    + objectMessage.getObject().toString());
            tempCar = (Car) objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error(e);
        }

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
                logger.error(e);
            }
        } else {
            System.out.println("Car " + tempCar + " does not meet the filtering requirements" );
        }
    }
}
