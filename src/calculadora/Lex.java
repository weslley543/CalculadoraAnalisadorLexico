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
public  abstract class Lex {
      public String indentificador;
      public String descIdentificador;
      public Lex(String indentificador, String descIdentificador){
          this.indentificador=indentificador;
          this.descIdentificador = descIdentificador;
      }
      
}
