package br.com.developer.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.developer.dao.TimeCoracaoDao;
import br.com.developer.model.TimeCoracao;

/**
 * 
 *
 */
public class TimeCoracaoServiceTest extends ServiceTestBase{

    private TimeCoracaoService service;

    private TimeCoracaoDao dao;

    @Before
    public void setUp() {
        service = new TimeCoracaoService();
        dao = Mockito.mock(TimeCoracaoDao.class);
    }

    @Test
    public void testCreate() {
        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setNome("Corinthians");

        try {
            mockDependencyInjection("dao", dao, service);

            service.create(timeCoracao);
            Mockito.verify(dao).create(timeCoracao);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteById() {
        TimeCoracao timeCoracao = new TimeCoracao();
        long id = 1L;
        timeCoracao.setId(id);
        timeCoracao.setNome("Corinthians");

        try {
            mockDependencyInjection("dao", dao, service);
            TimeCoracao timeCoracaoRecuperado = new TimeCoracao();
            timeCoracaoRecuperado.setId(id);
            timeCoracaoRecuperado.setNome("Corinthians");
            
            Mockito.when(dao.findById(id)).thenReturn(timeCoracaoRecuperado);

            service.deleteById(timeCoracao.getId());
            Mockito.verify(dao).delete(timeCoracao);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        long id = 1L;
        TimeCoracao timeCoracao = null;

        try {
            mockDependencyInjection("dao", dao, service);

            TimeCoracao timeCoracaoRecuperado = new TimeCoracao();
            timeCoracaoRecuperado.setId(id);
            
            Mockito.when(dao.findById(id)).thenReturn(timeCoracaoRecuperado);

            timeCoracao = service.findById(id);
            Assert.assertNotNull("TimeCoracao não pode ser null.", timeCoracao);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        TimeCoracao timeCoracao = new TimeCoracao();

        try {
            mockDependencyInjection("dao", dao, service);

            Mockito.when(dao.update(timeCoracao)).thenReturn(new TimeCoracao());

            service.update(timeCoracao);
            Mockito.verify(dao).update(timeCoracao);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testListAll() {
        long id = 1L;

        try {
            mockDependencyInjection("dao", dao, service);

            List<TimeCoracao> timesCoracao = new ArrayList<TimeCoracao>();
            TimeCoracao timeCoracaoRecuperado = new TimeCoracao();
            timeCoracaoRecuperado.setId(id);

            timesCoracao.add(timeCoracaoRecuperado);

            Integer startPosition=0;
            Integer maxResult=10;
            Mockito.when(dao.listAll(startPosition, maxResult)).thenReturn(timesCoracao);

            List<TimeCoracao> timeCoracaosConsultados = service.listAll(startPosition, maxResult);
            Assert.assertNotNull("TimeCoracao não pode ser null.", timeCoracaosConsultados);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
