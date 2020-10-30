/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author wesll
 */
public class Lexema extends Lex {

    public Lexema(String indentificador, String simboloIdentificador) {
        super(indentificador, simboloIdentificador);
    }
    
    
    
    public String getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(String indentificador) {
        this.indentificador = indentificador;
    }

    public String getSimboloIdentificador() {
        return descIdentificador;
    }

    public void setSimboloIdentificador(String simboloIdentificador) {
        this.descIdentificador = simboloIdentificador;
    }
    
    
}
