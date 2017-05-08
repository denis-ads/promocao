package br.com.developer.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.developer.Evento;
import br.com.developer.dao.CampanhaDao;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;
import br.com.developer.util.DateUtil;

/**
 * DAO for Campanha
 */
@Stateless
public class CampanhaService {

    @Inject
    private CampanhaDao dao;


    @Inject
    @Evento
    private Event<Campanha> eventoCampanha;


    public Campanha cadastrarCampanha(final Campanha campanha) throws ServiceException {
        try {
            final List<Campanha> campanhas = dao.consultarCampanhasAtivas(campanha.getFimVigencia(), null, null);
            System.out.println("denis consulta");
            verificarConsistenciaVigencias(campanha, campanhas);
            for (final Campanha campanhaAtualizar : campanhas) {
                System.out.println("atualizar: " + campanha);
                update(campanhaAtualizar);

            }
            create(campanha);
            System.out.println("denis teste666");
            acionarEvento(campanha);

        } catch (final DBException e) {
            throw new ServiceException(e);
        }
        return campanha;
    }

    public void acionarEvento(final Campanha campanha) {
        System.out.println("vai acionar evento: " + campanha);
        System.out.println("eventoCampanha: " + eventoCampanha);

        eventoCampanha.fire(campanha);
        System.out.println("Acionou evento");
    }

    public void verificarConsistenciaVigencias(Campanha campanha, List<Campanha> campanhas) {
        for (int i = 0; i < campanhas.size(); i++) {

            int j = 0;

            final Date dataOriginal = campanha.getFimVigencia();
            final Campanha cp = campanhas.get(i);

            if (cp.getFimVigencia().compareTo(dataOriginal) <= 0) {
                cp.setFimVigencia(DateUtil.adicionarDias(cp.getFimVigencia(), 1));
            }

            while (j < campanhas.size()) {
                final Campanha cpj = campanhas.get(j);

                if (i == j) {
                    j = j + 1;

                } else if (i > j) {
                    if (cp.getFimVigencia().compareTo(cpj.getFimVigencia()) <= 0) {
                        cp.setFimVigencia(DateUtil.adicionarDias(cp.getFimVigencia(), 1));
                    } else {
                        j = j + 1;
                    }
                } else {
                    if (cp.getFimVigencia().compareTo(cpj.getFimVigencia()) < 0) {
                        if (cpj.getFimVigencia().compareTo(dataOriginal) > 0) {
                            break;
                        }
                        cp.setFimVigencia(DateUtil.adicionarDias(cp.getFimVigencia(), 1));
                    } else if (cp.getFimVigencia().compareTo(cpj.getFimVigencia()) == 0) {
                        cp.setFimVigencia(DateUtil.adicionarDias(cp.getFimVigencia(), 1));

                    } else {
                        j = j + 1;
                    }
                }
            }
        }
    }

    public Campanha create(Campanha entity) throws ServiceException {
        try {

            return dao.create(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        Campanha entity;
        try {
            entity = dao.findById(id);
            if (entity == null) {
                throw new IllegalArgumentException("Objeto não encontrado");
            }
            dao.delete(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public Campanha findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public Campanha update(Campanha entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

    public List<Campanha> consultarCampanhasAtivas(Date data, Integer startPosition, Integer maxResult) throws ServiceException {
        try {
            return dao.consultarCampanhasAtivas(data, startPosition, maxResult);

        } catch (final DBException e) {
            throw new ServiceException(e);
        }

    }
}
