package br.com.developer.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
//import io.swagger.jaxrs.config.BeanConfig;
//import io.swagger.models.Contact;
//import io.swagger.models.Info;

@ApplicationPath("/rest")
public class RestApplication extends Application {
    /*Caso precisa gerar json swagger da API
    
    public RestApplication() {
      BeanConfig beanConfig = new BeanConfig();
      beanConfig.setTitle("Campanha API Documentation");
      beanConfig.setVersion("1.0.0.0");
      beanConfig.setDescription("API de comunicacao dominio Campanha");
      beanConfig.setContact("contact@developer.com");
      
      
      beanConfig.setSchemes(new String[] { "http" });
      beanConfig.setBasePath("/campaing/rest");
      beanConfig.setHost("localhost:8082");
      beanConfig.setResourcePackage("br.com.developer.rest");
      beanConfig.setScan(true);
  }
  
  @Override
  public Set<Class<?>> getClasses() {
      HashSet<Class<?>> set = new HashSet<Class<?>>();
      set.add(HealthCheckResource.class);
      set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
      set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

      return set;
  }
  */
}