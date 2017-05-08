package br.com.developer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.model.Campanha;
import br.com.developer.model.TimeCoracao;

public class CampanhaServiceTest extends ServiceTestBase{

    private CampanhaService service;

    private CampanhaDao dao;

    @Before
    public void setUp() {
        service = new CampanhaService();
        dao = Mockito.mock(CampanhaDao.class);
    }

    @Test
    public void testCreate() {
        Campanha campanha = new Campanha();
        campanha.setNome("Teste 01");
        campanha.setInicioVigencia(new Date());
        campanha.setFimVigencia(new Date());
        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        campanha.setTimeCoracao(timeCoracao);

        try {
            mockDependencyInjection("dao", dao, service);

            service.create(campanha);
            Mockito.verify(dao).create(campanha);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteById() {
        long id = 1L;
        Campanha campanha = new Campanha();
        campanha.setId(id);
        campanha.setNome("Teste 01");
        campanha.setInicioVigencia(new Date());
        campanha.setFimVigencia(new Date());
        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        campanha.setTimeCoracao(timeCoracao);

        try {
            mockDependencyInjection("dao", dao, service);
            Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);
            campanhaRecuperado.setNome("Teste 01");
            campanhaRecuperado.setInicioVigencia(new Date());
            campanhaRecuperado.setFimVigencia(new Date());
            campanhaRecuperado.setTimeCoracao(timeCoracao);
            
            Mockito.when(dao.findById(id)).thenReturn(campanhaRecuperado);

            service.deleteById(campanha.getId());
            Mockito.verify(dao).delete(campanha);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        long id = 1L;
        Campanha campanha = null;

        try {
            mockDependencyInjection("dao", dao, service);

            Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);
            
            Mockito.when(dao.findById(id)).thenReturn(campanhaRecuperado);

            campanha = service.findById(id);
            Assert.assertNotNull("Campanha não pode ser null.", campanha);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        Campanha campanha = new Campanha();

        try {
            mockDependencyInjection("dao", dao, service);

            Mockito.when(dao.update(campanha)).thenReturn(new Campanha());

            service.update(campanha);
            Mockito.verify(dao).update(campanha);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testListAll() {
        long id = 1L;

        try {
            mockDependencyInjection("dao", dao, service);

            List<Campanha> campanhas = new ArrayList<Campanha>();
            Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);

            campanhas.add(campanhaRecuperado);

            Integer startPosition=0;
            Integer maxResult=10;
            Mockito.when(dao.listAll(startPosition, maxResult)).thenReturn(campanhas);

            List<Campanha> campanhasConsultados = service.listAll(startPosition, maxResult);
            Assert.assertNotNull("Campanha não pode ser null.", campanhasConsultados);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    
    @Test
    public void testVerificarDataVencida() {
        long id = 1L;

        try {
            mockDependencyInjection("dao", dao, service);

            List<Campanha> campanhas = new ArrayList<Campanha>();
            Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);

            campanhas.add(campanhaRecuperado);

            Integer startPosition=0;
            Integer maxResult=10;
            Mockito.when(dao.listAll(startPosition, maxResult)).thenReturn(campanhas);

            List<Campanha> campanhasConsultados = service.listAll(startPosition, maxResult);
            Assert.assertNotNull("Campanha não pode ser null.", campanhasConsultados);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
}
