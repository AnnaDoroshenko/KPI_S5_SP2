/**
 * System programming
 * Lab 3. CREATION AND ADJUSTMENT OF LEXICAL ANALYSIS
 * ON THE BASIS OF AUTOMATIC GRAMMAR
 *
 * @variant: 16
 * @author: Anna Doroshenko
 * @group: IO-52
 * @date: 23.10.2017
 */

package Lab03;

import java.util.List;

public class Lab03 {

    public static void main(String[] args) {

        Token expression = new Token();
        List<Token> listOfTokens;

        listOfTokens = expression.getListOfTokens("repeat n=5+anka5");
//        listOfTokens = expression.getListOfTokens("repeat n:=n-1 until (n=0 or b!=a[n]);");

//        expression.add(new Token(TokenName.KEYWORD, "repeat"));
//        expression.add(new Token(TokenName.IDENTIFIER, "n"));
//        expression.add(new Token(TokenName.ASSIGN, ":="));
//        expression.add(new Token(TokenName.IDENTIFIER, "n"));
//        expression.add(new Token(TokenName.SUB, "-"));
//        expression.add(new Token(TokenName.LITERAL, "1"));
//        expression.add(new Token(TokenName.KEYWORD, "until"));
//        expression.add(new Token(TokenName.LEFT_PARENTHESIS, "("));
//        expression.add(new Token(TokenName.IDENTIFIER, "n"));
//        expression.add(new Token(TokenName.EQUAL, "="));
//        expression.add(new Token(TokenName.LITERAL, "0"));
//        expression.add(new Token(TokenName.KEYWORD, "or"));
//        expression.add(new Token(TokenName.IDENTIFIER, "b"));
//        expression.add(new Token(TokenName.NOT_EQUAL, "!="));
//        expression.add(new Token(TokenName.IDENTIFIER, "a"));
//        expression.add(new Token(TokenName.LEFT_BRACKET, "["));
//        expression.add(new Token(TokenName.IDENTIFIER, "n"));
//        expression.add(new Token(TokenName.RIGHT_BRACKET, "]"));
//        expression.add(new Token(TokenName.RIGHT_PARENTHESIS, ")"));
//        expression.add(new Token(TokenName.SEMICOLON, ";"));

        for(Token t : listOfTokens){
            System.out.println(t);
        }
    }

}
