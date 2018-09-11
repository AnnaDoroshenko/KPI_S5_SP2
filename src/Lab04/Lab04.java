/**
 * System programming
 * Lab 4. CREATION AND CONFIGURATION OF UPLINK
 * SYNTAX ANALYZERS
 *
 * @variant: 16
 * @author: Anna Doroshenko
 * @group: IO-52
 * @date: 19.11.2017
 */

package Lab04;

import Lab03.Token;

import java.util.List;

public class Lab04 {

    public static void main(String[] args) {

        String input = "a:=n-1; b:=0; b:=n[an]";
        Token expression = new Token();
        List<Token> listOfTokens;

        listOfTokens = expression.getListOfTokens(input);

//        for (Token token : listOfTokens) {
//            System.out.println(token);
//        }

        System.out.println(input);

        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();

        if (syntaxAnalyzer.analyseCorrectnessOfBrackets(listOfTokens)
                && syntaxAnalyzer.analyseCorrectnessOfNextToken(listOfTokens)) {
            System.out.println("\nInput is correct");
        } else {
            System.out.println("\nInvalid input");
        }
    }
}