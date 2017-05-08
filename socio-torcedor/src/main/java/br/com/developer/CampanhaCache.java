package br.com.developer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.developer.model.Campanha;

/**
 * TODO Substituir POR Cache Distribuido.
 * Infinispan, DataGrid.
 *
 */
public class CampanhaCache implements Serializable{

    private static final long serialVersionUID = -516315685552801313L;
    
    public static final Map<Long, List<Campanha>> CAMPANHAS_CACHE = new ConcurrentHashMap<>();
    
    public static void adicionarCache(Long timeCoracaoId, List<Campanha> campanhas){
        CAMPANHAS_CACHE.put(timeCoracaoId, campanhas);
    }
    
    public static void limparCache(Long timeCoracaoId){
        CAMPANHAS_CACHE.remove(timeCoracaoId);
    }
    
    public static void limparCache(){
        CAMPANHAS_CACHE.clear();;
    }
    
    public static List<Campanha> getCampanhas(Long timerCoracaoId){
        return CAMPANHAS_CACHE.get(timerCoracaoId);
    }
}
