package br.com.developer.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.developer.event.EventConsumer;

@MessageDriven(name = "MyTopicMDB", activationConfig = {
@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = EventConsumer.MY_TOPIC),
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),})
public class MyTopicMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("received: " + ((TextMessage) message).getText());
        } catch (final JMSException e) {
            e.printStackTrace();
        }
    }
}
