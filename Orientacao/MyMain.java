package br.com.developer;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.swagger.SwaggerArchive;

public class MyMain {
    public static void main(String[] args) throws Exception {

        final Swarm swarm = new Swarm();

        final SwaggerArchive archive = ShrinkWrap.create(SwaggerArchive.class, "socio-torcedor.war");
        final JAXRSArchive deployment = archive.as(JAXRSArchive.class).addPackage(MyMain.class.getPackage());

        archive.setResourcePackages("br.com.developer.rest");

        deployment.addAllDependencies();
        swarm.fraction(LoggingFraction.createDefaultLoggingFraction())
                .start()
                .deploy(deployment);
    }


//    public static void main(final String... args) throws Exception {
//        // Instantiate the container
//        final Container container = new Container();
//        final JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class );
//        deployment.addResource( TimeCoracao.class );
//        deployment.addResource( TimeCoracaoDao.class );
//        deployment.addResource( TimeCoracaoEndpoint.class );
//        deployment.addResource( TimeCoracaoService.class );
////        deployment.addResource( MyRest.class );
////        deployment.addResource( CORSFilter.class );
//        // Enable the swagger bits
//        final SwaggerArchive archive = deployment.as(SwaggerArchive.class);
//        // Tell swagger where our resources are
//        archive.setResourcePackages("com.matthewcasperson.swarmdemo");
//        archive.setTitle("Swagger Demo");
//        container.start();
//        container.deploy(deployment);
//    }
}
