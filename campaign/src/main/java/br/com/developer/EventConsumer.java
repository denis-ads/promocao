package br.com.developer;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.model.Campanha;

@RequestScoped
public class EventConsumer {
    @Inject
    private CampanhaDao dao;


    public void consumeEvent(@Observes(notifyObserver = Reception.IF_EXISTS) @Evento Campanha acionarEvento)  {

        System.out.println("testedenis1");
        System.out.println(dao.listAll(null, null));
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("testedenis222");
    }

}
