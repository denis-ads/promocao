package br.com.developer.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.developer.dao.CampanhaDao;
import br.com.developer.event.Evento;
import br.com.developer.exception.DBException;
import br.com.developer.exception.ServiceException;
import br.com.developer.model.Campanha;
import br.com.developer.model.TimeCoracao;
import br.com.developer.util.DateUtil;

/**
 *
 *
 */
@Stateless
public class CampanhaService {

    @Inject
    private CampanhaDao dao;

    @Inject
    private TimeCoracaoService timeCoracaoService;

    @Inject
    @Evento
    private Event<Campanha> eventoCampanha;


    public Campanha cadastrarCampanha(final Campanha campanha) throws ServiceException {
        try {
            final List<Campanha> campanhas = dao.consultarCampanhasAtivas(campanha.getFimVigencia(), null, null);
            verificarConsistenciaVigencias(campanha, campanhas);
            for (final Campanha campanhaAtualizar : campanhas) {
                update(campanhaAtualizar);
            }
            final TimeCoracao timeCoracao = timeCoracaoService.findById(campanha.getTimeCoracao().getId());
            campanha.setTimeCoracao(timeCoracao);
            create(campanha);

            acionarEvento(campanha);

        } catch (final DBException e) {
            throw new ServiceException(e);
        }
        return campanha;
    }

    public void acionarEvento(final Campanha campanha) {
        eventoCampanha.fire(campanha);
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

    public List<Campanha> consultarCampanhasAtivas(Date fimVigencia, Long timeCoracaoId, Integer startPosition, Integer maxResult) throws ServiceException {
        try {
            return dao.consultarCampanhasAtivasTimeCoracaoId(fimVigencia, timeCoracaoId, startPosition, maxResult);

        } catch (final DBException e) {
            throw new ServiceException(e);
        }
    }

}
