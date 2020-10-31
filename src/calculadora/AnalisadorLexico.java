/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wesll
 */
public class AnalisadorLexico {

    public ArrayList<Lexema> lexemas;
    public ArrayList<LexemaComErros> lexErros;
    private StringBuffer currentToken = new StringBuffer();
    private Scanner sc;

    public AnalisadorLexico() {
        this.lexemas = new ArrayList();
        this.lexErros = new ArrayList();

    }

    public void cleanLexemas() {
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
                default:
                    throw new Error("O caractere " + "'" + chr + "'" + " inserido não é nenhum dos válidos verifique a expressão");

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

    public void analisarExpressaoIndicandoErros(String diretorio) {
        try {
            int linha = 1;
            
            this.sc = new Scanner(new FileReader(diretorio));
            
            while (sc.hasNextLine()) {
                String expressao = sc.nextLine();
                LexemaComErros aux = null;
                int i;
                ArrayList<Integer> colunas = new ArrayList();
                for (i = 0; i < expressao.length(); i++) {
                
                    String cur = expressao.substring(i, i + 1);
                    if (cur.matches("[0-9.]")) {
                        colunas.add(i);
                        currentToken.append(cur);
                        continue;
                    } else if (currentToken.length() > 0) {
                        String token = currentToken.toString();
                        if (token.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+")) {
                           
                            aux = new LexemaComErros("NUM", token, linha, colunas.get(0));
                            lexErros.add(aux);

                        }
                    }
                    currentToken = new StringBuffer();
                    char chr = expressao.charAt(i);
                    
                    switch (chr) {
                        case '+':
                            
                            aux = new LexemaComErros("OPSOMA", "+", linha, i);

                            break;
                        case '-':
                            aux = new LexemaComErros("OPSUB", "-", linha, i);

                            break;
                        case '*':
                            aux = new LexemaComErros("OPMUL", "*", linha, i);

                            break;
                        case '/':
                            aux = new LexemaComErros("OPDIV", "/", linha, i);

                            break;
                        case ')':
                            aux = new LexemaComErros("FP", ")", linha, i);

                            break;
                        case '(':
                            aux = new LexemaComErros("FP", "(", linha, i);
                            break;
                        case ' ':
                            continue;
                        default:
                            aux = new LexemaComErros("ERRO", Character.toString(chr), linha, i);
                            

                    }

                    lexErros.add(aux);
                    int size = colunas.size();
                    for(int j = 0; j < size; j++){
                        colunas.remove(0);
                    }
                }

                if (currentToken.length() > 0) {
                    String token = currentToken.toString();
                    if (token.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+")) {
                        aux = new LexemaComErros("NUM", token,linha, colunas.get(0));
                         lexErros.add(aux);
                    }
                }
                currentToken.delete(0, currentToken.length());
                linha++;

            }
//            for (int i = 0; i < lexErros.size(); i++) {
//                System.out.println(lexErros.get(i).descIdentificador + " " + lexErros.get(i).indentificador + " " +lexErros.get(i).linhaLida + " " +lexErros.get(i).colunaLida);
//            }

        } catch (FileNotFoundException e) {
            throw new Error(e + "Erro ao abrir o arquivo");
        }
    }
}
