package br.com.developer.jms;

import org.jboss.msc.service.ServiceActivator;
import org.jboss.msc.service.ServiceActivatorContext;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceRegistryException;
import org.jboss.msc.service.ServiceTarget;

public class MyServiceActivator implements ServiceActivator {
    public void activate(ServiceActivatorContext context) throws ServiceRegistryException {

        System.err.println("activating services");
        final ServiceTarget target = context.getServiceTarget();

        target.addService(ServiceName.of("my", "service", "1"), new MyService("/jms/topic/my-topic"))
                .install();
    }
}