package br.com.developer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.jms.JMSContext;
import javax.jms.Topic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.model.Campanha;
import br.com.developer.model.TimeCoracao;
import br.com.developer.util.DateUtil;

/**
 *
 *
 */
public class CampanhaServiceTest extends ServiceTestBase {

    public CampanhaService service;

    public CampanhaDao dao;

    private TimeCoracaoService timeCoracaoService;

    public Event<Campanha> eventoCampanha;

    private JMSContext context;

    private Topic topic;

    @Before
    public void setUp() throws Exception {
        service = new CampanhaService();
        dao = Mockito.mock(CampanhaDao.class);
        mockDependencyInjection("dao", dao, service);

        eventoCampanha = Mockito.mock(Event.class);
        mockDependencyInjection("eventoCampanha", eventoCampanha, service);

        timeCoracaoService = Mockito.mock(TimeCoracaoService.class);
        mockDependencyInjection("timeCoracaoService", timeCoracaoService, service);

//        context = Mockito.mock(JMSContext.class);
//        mockDependencyInjection("context", context, service);
//
//        topic = Mockito.mock(Topic.class);
//        mockDependencyInjection("topic", topic, service);
    }

    @Test
    public void testCreate() {
        final Campanha campanha = new Campanha();
        campanha.setNome("Teste 01");
        campanha.setInicioVigencia(new Date());
        campanha.setFimVigencia(new Date());
        final TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        campanha.setTimeCoracao(timeCoracao);

        try {

            service.create(campanha);
            Mockito.verify(dao).create(campanha);

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteById() {
        final long id = 1L;
        final Campanha campanha = new Campanha();
        campanha.setId(id);
        campanha.setNome("Teste 01");
        campanha.setInicioVigencia(new Date());
        campanha.setFimVigencia(new Date());
        final TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId(1L);
        timeCoracao.setNome("Corinthians");
        campanha.setTimeCoracao(timeCoracao);

        try {
            mockDependencyInjection("dao", dao, service);
            final Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);
            campanhaRecuperado.setNome("Teste 01");
            campanhaRecuperado.setInicioVigencia(new Date());
            campanhaRecuperado.setFimVigencia(new Date());
            campanhaRecuperado.setTimeCoracao(timeCoracao);

            Mockito.when(dao.findById(id)).thenReturn(campanhaRecuperado);

            service.deleteById(campanha.getId());
            Mockito.verify(dao).delete(campanha);

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        final long id = 1L;
        Campanha campanha = null;

        try {
            mockDependencyInjection("dao", dao, service);

            final Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);

            Mockito.when(dao.findById(id)).thenReturn(campanhaRecuperado);

            campanha = service.findById(id);
            Assert.assertNotNull("Campanha não pode ser null.", campanha);

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        final Campanha campanha = new Campanha();

        try {
            mockDependencyInjection("dao", dao, service);

            Mockito.when(dao.update(campanha)).thenReturn(new Campanha());

            service.update(campanha);
            Mockito.verify(dao).update(campanha);

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testListAll() {
        final long id = 1L;

        try {
            mockDependencyInjection("dao", dao, service);

            final List<Campanha> campanhas = new ArrayList<Campanha>();
            final Campanha campanhaRecuperado = new Campanha();
            campanhaRecuperado.setId(id);

            campanhas.add(campanhaRecuperado);

            final Integer startPosition = 0;
            final Integer maxResult = 10;
            Mockito.when(dao.listAll(startPosition, maxResult)).thenReturn(campanhas);

            final Long timeCoracaoId = 1L;
            final List<Campanha> campanhasConsultados = service.consultarCampanhasAtivas(new Date(), timeCoracaoId, startPosition,
                            maxResult);
            Assert.assertNotNull("Campanha não pode ser null.", campanhasConsultados);

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCadastroCampanha() {
        final Integer startPosition = null;
        final Integer maxResult = null;

        try {

            final List<Campanha> campanhas = new ArrayList<Campanha>();
            final Campanha campanha2 = new Campanha(2L, "Campanha 2", DateUtil.toDate("01/10/2017"), DateUtil.toDate("02/10/2017"));
            campanhas.add(campanha2);
            final Campanha campanha1 = new Campanha(1L, "Campanha 1", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            campanhas.add(campanha1);

            Mockito.when(dao.consultarCampanhasAtivas(DateUtil.toDate("03/10/2017"), startPosition, maxResult)).thenReturn(campanhas);

            final Long timeCoracaoId = 1L;
            final Campanha campanha3 = new Campanha(null, "Campanha 3", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            final TimeCoracao time = new TimeCoracao(timeCoracaoId, null);
            campanha3.setTimeCoracao(time);

            final Campanha campanha4 = campanha3;
            campanha4.setId(3L);
            Mockito.when(dao.create(campanha3)).thenReturn(campanha4);

            Mockito.when(timeCoracaoService.findById(timeCoracaoId)).thenReturn(new TimeCoracao(1L, "Corinthians"));

            final Campanha create = service.cadastrarCampanha(campanha3);
            Mockito.verify(eventoCampanha, Mockito.times(1)).fire(campanha4);
            Assert.assertNotNull("Campanha não pode ser null.", create.getId());

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCadastroCampanha1() {
        final Integer startPosition = null;
        final Integer maxResult = null;

        try {
            mockDependencyInjection("dao", dao, service);
            mockDependencyInjection("eventoCampanha", eventoCampanha, service);
            final List<Campanha> campanhas = new ArrayList<Campanha>();
            final Campanha campanha2 = new Campanha(2L, "Campanha 2", DateUtil.toDate("01/10/2017"), DateUtil.toDate("02/10/2017"));
            campanhas.add(campanha2);
            final Campanha campanha1 = new Campanha(1L, "Campanha 1", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            campanhas.add(campanha1);
            final Campanha campanha3 = new Campanha(3L, "Campanha 4", DateUtil.toDate("01/10/2017"), DateUtil.toDate("04/10/2017"));
            campanhas.add(campanha3);
            final Campanha campanha4 = new Campanha(4L, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("05/10/2017"));
            campanhas.add(campanha4);
            Mockito.when(dao.consultarCampanhasAtivas(DateUtil.toDate("03/10/2017"), startPosition, maxResult)).thenReturn(campanhas);

            final Long timeCoracaoId = 1L;
            final Campanha campanha5 = new Campanha(null, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            final TimeCoracao time = new TimeCoracao(timeCoracaoId, null);
            campanha5.setTimeCoracao(time);

            final Campanha campanha6 = campanha5;
            campanha6.setId(5L);
            Mockito.when(dao.create(campanha5)).thenReturn(campanha6);

            Mockito.when(timeCoracaoService.findById(timeCoracaoId)).thenReturn(new TimeCoracao(1L, "Corinthians"));

            final Campanha create = service.cadastrarCampanha(campanha5);
            Mockito.verify(eventoCampanha, Mockito.times(1)).fire(campanha6);
            Assert.assertNotNull("Campanha não pode ser null.", create.getId());

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testVerificarDataVencida() {
        try {
            final List<Campanha> campanhas = new ArrayList<Campanha>();

            final Campanha campanha2 = new Campanha(2L, "Campanha 2", DateUtil.toDate("01/10/2017"), DateUtil.toDate("02/10/2017"));
            campanhas.add(campanha2);
            final Campanha campanha1 = new Campanha(1L, "Campanha 1", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            campanhas.add(campanha1);
            final Campanha campanha3 = new Campanha(null, "Campanha 3", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));

            service.verificarConsistenciaVigencias(campanha3, campanhas);

            campanhas.stream().forEach(System.out::println);

            Assert.assertEquals(DateUtil.toDate("04/10/2017"), campanhas.get(0).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("05/10/2017"), campanhas.get(1).getFimVigencia());

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testVerificarDataVencida1() {
        try {
            final List<Campanha> campanhas = new ArrayList<Campanha>();

            final Campanha campanha2 = new Campanha(2L, "Campanha 2", DateUtil.toDate("01/10/2017"), DateUtil.toDate("02/10/2017"));
            campanhas.add(campanha2);
            final Campanha campanha1 = new Campanha(1L, "Campanha 1", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            campanhas.add(campanha1);
            final Campanha campanha3 = new Campanha(3L, "Campanha 4", DateUtil.toDate("01/10/2017"), DateUtil.toDate("04/10/2017"));
            campanhas.add(campanha3);
            final Campanha campanha4 = new Campanha(4L, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("05/10/2017"));
            campanhas.add(campanha4);

            final Campanha campanha5 = new Campanha(null, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));

            service.verificarConsistenciaVigencias(campanha5, campanhas);

            campanhas.stream().forEach(System.out::println);

            Assert.assertEquals(DateUtil.toDate("06/10/2017"), campanhas.get(0).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("07/10/2017"), campanhas.get(1).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("08/10/2017"), campanhas.get(2).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("09/10/2017"), campanhas.get(3).getFimVigencia());
            // Assert.assertEquals(DateUtil.toDate("03/10/2017"), campanhas.get(4).getFimVigencia()
            // );

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testVerificarDataVencida2() {
        try {
            final List<Campanha> campanhas = new ArrayList<Campanha>();

            final Campanha campanha2 = new Campanha(2L, "Campanha 2", DateUtil.toDate("01/10/2017"), DateUtil.toDate("02/10/2017"));
            campanhas.add(campanha2);
            final Campanha campanha1 = new Campanha(1L, "Campanha 1", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));
            campanhas.add(campanha1);
            final Campanha campanha3 = new Campanha(3L, "Campanha 4", DateUtil.toDate("01/10/2017"), DateUtil.toDate("06/10/2017"));
            campanhas.add(campanha3);
            final Campanha campanha4 = new Campanha(4L, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("07/10/2017"));
            campanhas.add(campanha4);

            final Campanha campanha5 = new Campanha(null, "Campanha 5", DateUtil.toDate("01/10/2017"), DateUtil.toDate("03/10/2017"));

            service.verificarConsistenciaVigencias(campanha5, campanhas);

            campanhas.stream().forEach(System.out::println);

            Assert.assertEquals(DateUtil.toDate("04/10/2017"), campanhas.get(0).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("05/10/2017"), campanhas.get(1).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("06/10/2017"), campanhas.get(2).getFimVigencia());
            Assert.assertEquals(DateUtil.toDate("07/10/2017"), campanhas.get(3).getFimVigencia());
            // Assert.assertEquals(DateUtil.toDate("03/10/2017"), campanhas.get(4).getFimVigencia()
            // );

        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
