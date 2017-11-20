package Lab05;

import Lab03.Token;
import Lab04.SyntaxAnalyzer;

import java.util.List;

public class Lab05 {

    public static void main(String[] args) {

        Token expression = new Token();
        List<Token> listOfTokens;

        listOfTokens = expression.getListOfTokens("n:=n-1; b:=0; b:=n!=a[n]);");

        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();
        syntaxAnalyzer.analyseCorrectnessOfBrackets(listOfTokens);
        syntaxAnalyzer.analyseCorrectnessOfNextToken(listOfTokens);
    }
}
