package messageListeners;

import model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class StandardizerMessageListener implements MessageListener {
    final static Logger logger = LoggerFactory.getLogger(StandardizerMessageListener.class);
    private String consumerName;
    private Car standardizerCar = null;

    public StandardizerMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            logger.info(consumerName + " received after filtration "
                    + objectMessage.getObject().toString());
            standardizerCar = (Car) objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }

        // Cars standardizer starts work
        standardizerCar.setBrand(standardizerCar.getBrand().toUpperCase());
        standardizerCar.setModel(standardizerCar.getModel().toUpperCase());

        logger.info(" Hello Mates! we got new car: " + standardizerCar.getBrand()
                + " model: " + standardizerCar.getModel() + " with engine " + standardizerCar.getEngine()
                + " from year - " + standardizerCar.getYear());
    }
}
