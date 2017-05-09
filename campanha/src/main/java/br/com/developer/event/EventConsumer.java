package br.com.developer.event;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.JMSContext;
import javax.jms.Topic;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.model.Campanha;

/**
 *
 *
 */
@Singleton
public class EventConsumer implements Serializable{

    private static final long serialVersionUID = 7878907614093427129L;

    public static final String MY_TOPIC = "/jms/topic/my-topic";

    @Inject
    private CampanhaDao dao;

    @Inject
    private JMSContext context;

    @Resource(lookup = MY_TOPIC)
    private Topic topic;


    public void consumeEvent(@Observes(notifyObserver = Reception.IF_EXISTS) @Evento Campanha acionarEvento)  {

        System.out.println("testedenis1");
        System.out.println(dao.listAll(null, null));
        try {
            context.createProducer().send(topic, "Hello!");
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("testedenis222");
    }

}
