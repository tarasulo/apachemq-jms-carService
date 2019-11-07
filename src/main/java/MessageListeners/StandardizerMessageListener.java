package MessageListeners;

import model.Car;
import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class StandardizerMessageListener implements MessageListener {
    final static Logger logger = Logger.getLogger(StandardizerMessageListener.class);
    private String consumerName;
    private Car standardizerCar = null;

    public StandardizerMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            System.out.println(consumerName + " received after filtration "
                    + objectMessage.getObject().toString());
            logger.info(consumerName + " received after filtration"
                    + objectMessage.getObject().toString());
            standardizerCar = (Car) objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error(e);
        }

        // Cars standardizer starts work
        standardizerCar.setBrand(standardizerCar.getBrand().toUpperCase());
        standardizerCar.setModel(standardizerCar.getModel().toUpperCase());

        System.out.println(" Hello Mates! we got new car: " + standardizerCar.getBrand()
                + " model: " + standardizerCar.getModel() + " with engine " + standardizerCar.getEngine()
                + " from year - " + standardizerCar.getYear());
    }
}
