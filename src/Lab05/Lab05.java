/**
 * System programming
 * Lab 5. CREATION AND CONFIGURATION OF
 * DESCENDING SYNTAX ANALYZERS ON THE BASE
 * OF USING OF BACKUS` METALANGUAGE
 *
 * @variant: 16
 * @author: Anna Doroshenko
 * @group: IO-52
 * @date: 30.11.2017
 */

package Lab05;

import java.util.List;

public class Lab05 {

    public static void main(String[] args) {

        final String input = "If b<d then begin For t:=1 to p do begin a:=b end; end;";
        Token expression = new Token();
        List<Token> listOfTokens;

        listOfTokens = expression.getListOfTokens(input);
//        listOfTokens = expression.getListOfTokens("if (i = 0)then i:=i+1;");

//        for (Token token : listOfTokens) {
//            System.out.println(token);
//        }

        SyntaxAnalyzerDetailed syntaxAnalyzer = new SyntaxAnalyzerDetailed();
        final boolean isCorrectBrackets = syntaxAnalyzer.analyseCorrectnessOfBrackets(listOfTokens);
        final boolean isCorrectNextToken = syntaxAnalyzer.analyseCorrectnessOfNextToken(listOfTokens);

//        System.out.println(isCorrectBrackets);
//        System.out.println(isCorrectNextToken);

        System.out.println(input);

        if (isCorrectBrackets && isCorrectNextToken) {
            System.out.println("\nInput is correct");
        }
    }
}
