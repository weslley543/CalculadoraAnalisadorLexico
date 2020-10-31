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
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnalisadorLexico al = new AnalisadorLexico();
        UIPrincipal principal = new UIPrincipal();
        principal.setVisible(true);   
//        al.analisarExpressaoIndicandoErros("C:\\Users\\wesll\\Documents\\NetBeansProjects\\CalculadoraAnalisadorLexico\\src\\calculadora\\simbolosaceitos.txt");
    }
    
}
