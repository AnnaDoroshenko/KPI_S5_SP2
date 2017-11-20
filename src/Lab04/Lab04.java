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

        Token expression = new Token();
        List<Token> listOfTokens;

//        listOfTokens = expression.getListOfTokens("n:=n-1; b:=0; a[n]");
        listOfTokens = expression.getListOfTokens("a[n]");

        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();

        if (syntaxAnalyzer.analyseCorrectnessOfBrackets(listOfTokens)
                && syntaxAnalyzer.analyseCorrectnessOfNextToken(listOfTokens)) {
            System.out.println("The statement is correct");
        } else {
            System.err.println("There is(are) mistake(-s) in the statement");
        }
    }
}