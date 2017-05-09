package br.com.developer.vogal;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class VogalTest {

    private LeituraVogal leituraVogal = new LeituraVogal(); 
    
    @Test
    public void lerPrimeiraVogal(){
        String entrada = "aAbBABacafe";
        
        Stream<Character> input= entrada.chars().mapToObj(i -> (char)i);
        
        char retorno = leituraVogal.firstChar(input);    
        
        Assert.assertEquals('e', retorno);
    }
}
