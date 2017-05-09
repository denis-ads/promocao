package br.com.developer.services;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.developer.util.DateUtil;

/**
 * 
 *
 */
public class ConsistenciaCampahaTest {
    
    @Test
    public void teste0(){
        Date[] datas = {DateUtil.toDate("02/10/2017"),DateUtil.toDate("03/10/2017")};
        verificaDatas2(DateUtil.toDate("03/10/2017"),datas);
        for (Date date : datas) {
            System.out.println(date);
        }
        //  4,5
        Assert.assertEquals(DateUtil.toDate("04/10/2017"), datas[0] );
        Assert.assertEquals(DateUtil.toDate("05/10/2017"), datas[1] );
    }
    
    @Test
    public void teste1(){
        Date[] datas = {DateUtil.toDate("02/10/2017"),DateUtil.toDate("03/10/2017"), DateUtil.toDate("04/10/2017"),DateUtil.toDate("05/10/2017")};
        verificaDatas2(DateUtil.toDate("03/10/2017"),datas);
        for (Date date : datas) {
            System.out.println(date);
        }
        //  6,7,8,9
        Assert.assertEquals(DateUtil.toDate("06/10/2017"), datas[0] );
        Assert.assertEquals(DateUtil.toDate("07/10/2017"), datas[1] );
        Assert.assertEquals(DateUtil.toDate("08/10/2017"), datas[2] );
        Assert.assertEquals(DateUtil.toDate("09/10/2017"), datas[3] );
    }
    
    @Test
    public void teste2(){
        Date[] datas = {DateUtil.toDate("02/10/2017"),DateUtil.toDate("03/10/2017"), DateUtil.toDate("06/10/2017"),DateUtil.toDate("07/10/2017")};
        verificaDatas2(DateUtil.toDate("03/10/2017"),datas);
        for (Date date : datas) {
            System.out.println(date);
        }
        //  4,5,6,7
        Assert.assertEquals(DateUtil.toDate("04/10/2017"), datas[0] );
        Assert.assertEquals(DateUtil.toDate("05/10/2017"), datas[1] );
        Assert.assertEquals(DateUtil.toDate("06/10/2017"), datas[2] );
        Assert.assertEquals(DateUtil.toDate("07/10/2017"), datas[3] );
    }
    
    private void verificaDatas2(Date dataOriginal, Date[] datas) {
        for (int i = 0; i < datas.length; i++) {
            int j = 0;
            
            if(datas[i].compareTo(dataOriginal)<=0){
                datas[i] = DateUtil.adicionarDias(datas[i],1);
            }
            
            while(j < datas.length){
                
                if(i==j){
                    j=j+1;

                }else if(i>j){    
                    
                    if (datas[i].compareTo(datas[j]) <= 0 ) {
                        datas[i] = DateUtil.adicionarDias(datas[i],1);
                    }else{
                        j=j+1;
                    }
                }else{
                 
                    if (datas[i].compareTo(datas[j]) < 0 ) {
                        if (datas[j].compareTo(dataOriginal) >0 ) {
                            break;
                        }                        
                        datas[i] = DateUtil.adicionarDias(datas[i],1);
                    }else if(datas[i].compareTo(datas[j]) == 0) {
                        datas[i] = DateUtil.adicionarDias(datas[i],1);
                   
                    }else{
                        j=j+1;
                    }
                }
                
            }
        }
    }
}