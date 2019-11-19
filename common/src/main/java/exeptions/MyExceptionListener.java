package exeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

public class MyExceptionListener implements javax.jms.ExceptionListener {
    final static Logger logger = LoggerFactory.getLogger(MyExceptionListener.class);

    @Override
    public void onException(JMSException e) {
    logger.error(String.valueOf(e));
    }
}
