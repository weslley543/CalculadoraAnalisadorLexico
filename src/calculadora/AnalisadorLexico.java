/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;

/**
 *
 * @author wesll
 */
public class AnalisadorLexico {

    public ArrayList<Lexema> lexemas;
    StringBuffer currentToken = new StringBuffer();

    public AnalisadorLexico() {
        this.lexemas = new ArrayList();

    }
    
    public void cleanLexemas(){
        this.lexemas.clear();
    }

    public void analizarExpressao(String expressao) {
        Lexema aux = null;

        for (int i = 0; i < expressao.length(); i++) {

            String cur = expressao.substring(i, i + 1);
            if (cur.matches("[0-9.]")) {
                currentToken.append(cur);
                continue;
            } else if (currentToken.length() > 0) {
                String token = currentToken.toString();
                if (token.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+")) {
                    aux = new Lexema("NUM", token);
                    lexemas.add(aux);

                }else{
                    throw new Error("O caractere inserido não é nenhum dos válidos verifique a expressão");
                }
            }
            currentToken = new StringBuffer();
            char chr = expressao.charAt(i);

            switch (chr) {
                case '+':
                    aux = new Lexema("OPSOMA", "+");

                    break;
                case '-':
                    aux = new Lexema("OPSUB", "-");

                    break;
                case '*':
                    aux = new Lexema("OPMUL", "*");

                    break;
                case '/':
                    aux = new Lexema("OPDIV", "/");

                    break;
                case ')':
                    aux = new Lexema("FP", ")");

                    break;
                case '(':
                    aux = new Lexema("AP", "(");
                    break;
                case ' ':
                    continue;
                default :
                    throw new Error("O caractere inserido não é nenhum dos válidos verifique a expressão");

            }

            lexemas.add(aux);
        }

        if (currentToken.length() > 0) {
            String token = currentToken.toString();
            if (token.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+")) {
                aux = new Lexema("NUM", token);
                lexemas.add(aux);
            }
        }
        
        currentToken.delete(0, currentToken.length());
//        for (int i = 0; i < lexemas.size(); i++) {
//            System.out.println(lexemas.get(i).descIdentificador + " " + lexemas.get(i).indentificador);
//        }
    }
}
