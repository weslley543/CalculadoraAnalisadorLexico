package calculadora;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wesll
 */
public class LexemaComErros extends Lex {
      public int linhaLida;
      public int colunaLida;
    
    public LexemaComErros(String indentificador, String simboloIdentificador, int linhaLida, int colunaLida) {
        super(indentificador, simboloIdentificador);
        this.linhaLida = linhaLida;
        this.colunaLida = colunaLida;
    }

    public int getLinhaLida() {
        return linhaLida;
    }

    public void setLinhaLida(int linhaLida) {
        this.linhaLida = linhaLida;
    }

    public String getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(String indentificador) {
        this.indentificador = indentificador;
    }

    public String getDescIdentificador() {
        return descIdentificador;
    }

    public void setDescIdentificador(String descIdentificador) {
        this.descIdentificador = descIdentificador;
    }

    public int getColunaLida() {
        return colunaLida;
    }

    public void setColunaLida(int colunaLida) {
        this.colunaLida = colunaLida;
    }
    
}
