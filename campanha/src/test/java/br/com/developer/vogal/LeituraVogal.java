package br.com.developer.vogal;

public class LeituraVogal implements Stream {
    
    private char[] vogais= new char[]{'a', 'e', 'i', 'o', 'u'};
    private int[] contadorVogais = new int[5];
    
    private java.util.stream.Stream<Character> entrada;
    
    
    @Override
    public char getNext() {

        return 0;
    }

    @Override
    public boolean hasNext() {
        
        return false;
    }

    public char firstChar(java.util.stream.Stream<Character> input) {
        entrada = input;
        
        if (hasNext()) {
            
            
        }
                        
        return 'a';
    }

}
