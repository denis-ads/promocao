package br.com.developer.services;

import java.lang.reflect.Field;

/**
 * 
 * @author denis
 *
 */
public class ServiceTestBase {

    /**
     * Responsável por simular injeção durante os testes.
     * 
     * @param Nome do atributo privatedo que recebera a injeção.
     * @param Instancia que será injetada
     * @param Instancia que receberá a injenção
     * @throws Exception
     */
    protected void mockDependencyInjection(String namePrivateAttribute , Object objectInject, Object object) throws Exception {
        Field field;
        field = object.getClass().getDeclaredField(namePrivateAttribute );
        field.setAccessible(true);
        field.set(object, objectInject);
    }

}
