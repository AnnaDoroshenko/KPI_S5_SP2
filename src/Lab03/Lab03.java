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

        listOfTokens = expression.getListOfTokens("repeat n:=n-1 until (n=0 or b!=a[n]);");

        System.out.println(select(listOfTokens, 2));

        insert(listOfTokens, new Token(TokenName.COLON, ":"), 2);

        for (Token t : listOfTokens) {
            System.out.println(t);
        }

        update(listOfTokens, new Token(TokenName.COMMA, ","), 2);

        for (Token t : listOfTokens) {
            System.out.println(t);
        }
    }

    public static Token select(List<Token> tokens, int index) {
        return tokens.get(index);
    }

    public static void insert(List<Token> tokens, Token token, int index) {
        final int sizeOfList = tokens.size();
        if (index > sizeOfList) {
            return;
        }
        tokens.add(index, token);
    }

    public static void update(List<Token> tokens, Token token, int index) {
        final int sizeOfList = tokens.size();
        if (index >= sizeOfList) {
            return;
        } else {
            tokens.set(index, token);
        }
    }
}
