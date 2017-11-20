package Lab05;

import Lab03.Token;
import Lab03.TokenName;

import java.util.LinkedList;
import java.util.List;

public class SyntaxAnalyserDetailed {

    public SyntaxAnalyserDetailed() {
    }

    private LinkedList<Token> stackOfBrackets = new LinkedList<>();
    private boolean correctBrackets = false;
    private LinkedList<Token> stackOfRepeatUntilStatement = new LinkedList<>();

    public void analyseCorrectnessOfBrackets(List<Token> listOfTokens) {
        System.out.println("here we are");
        if (listOfTokens != null) {
            for (int i = 0; i < listOfTokens.size() - 1; ) {
                switch (listOfTokens.get(i).getTokenValue()) {
                    case ("("):
                        stackOfBrackets.add(new Token(TokenName.LEFT_PARENTHESIS, "("));
                        correctBrackets = false;
                        i++;
                        break;

                    case ("["):
                        stackOfBrackets.add(new Token(TokenName.LEFT_BRACKET, "["));
                        correctBrackets = false;
                        i++;
                        break;

                    case (")"):
                        if (stackOfBrackets != null) {
                            if (!stackOfBrackets.getLast().getTokenValue().equals("(")) {
                                correctBrackets = false;
                                System.err.println("Error: invalid input of right parenthesis in " + i);
                            }
                            correctBrackets = true;
                            i++;
                            break;
                        }

                    case ("]"):
                        if (stackOfBrackets != null) {
                            if (!stackOfBrackets.getLast().getTokenValue().equals("[")) {
                                correctBrackets = false;
                                System.err.println("Error: invalid input of right bracket in " + i);
                            }
                            correctBrackets = true;
                            i++;
                            break;
                        }
                }
            }
            if (!correctBrackets) {
                System.err.println("Error: invalid input of brackets");
            }
        } else {
            System.err.println("Error: empty list of tokens");
        }
    }

    public void analyseCorrectnessOfNextToken(List<Token> listOfTokens) {
        System.out.println("here we are");
        if (listOfTokens != null) {
            boolean isOnlyRepeat = false;
            for (int i = 0; i < listOfTokens.size() - 1; ) {
                switch (listOfTokens.get(i).getTokenName()) {
                    case KEYWORD:
                        if (listOfTokens.get(i).getTokenValue().equals("repeat")) {
                            stackOfRepeatUntilStatement.add(new Token(TokenName.KEYWORD, "repeat"));
                            isOnlyRepeat = true;
                        }
                        if (listOfTokens.get(i).getTokenValue().equals("until")) {
                            if (stackOfRepeatUntilStatement == null ||
                                    !stackOfRepeatUntilStatement.getLast().getTokenValue().equals("repeat")) {
                                System.err.println("Error: NO REPEAT KEYWORD");
                            }
                            isOnlyRepeat = false;
                        }
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case RIGHT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after keyword " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case IDENTIFIER:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case ASSIGN:
                                break;
                            case EQUAL:
                                break;
                            case NOT_EQUAL:
                                break;
                            case RIGHT_PARENTHESIS:
                                break;
                            case SEMICOLON:
                                break;
                            case SUBTRACTION:
                                break;
                            default:
                                System.err.println("Error: incorrect token after identifier " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case LITERAL:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case KEYWORD:
                                break;
                            case RIGHT_PARENTHESIS:
                                break;
                            case SEMICOLON:
                                break;
                            case SUBTRACTION:
                                break;
                            case ASTERISK:
                                break;
                            default:
                                System.err.println("Error: incorrect token after literal " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case LEFT_BRACKET:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            default:
                                System.err.println("Error: incorrect token after left bracket " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case RIGHT_BRACKET:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case ASTERISK:
                                break;
                            case SUBTRACTION:
                                break;
                            case NOT_EQUAL:
                                break;
                            case EQUAL:
                                break;
                            case ASSIGN:
                                break;
                            case SEMICOLON:
                                break;
                            default:
                                System.err.println("Error: incorrect token after right bracket " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case LEFT_PARENTHESIS:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            default:
                                System.err.println("Error: incorrect token after left parenthesis " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case RIGHT_PARENTHESIS:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case ASTERISK:
                                break;
                            case SUBTRACTION:
                                break;
                            case NOT_EQUAL:
                                break;
                            case EQUAL:
                                break;
                            case ASSIGN:
                                break;
                            case SEMICOLON:
                                break;
                            default:
                                System.err.println("Error: incorrect token after right parenthesis " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case SEMICOLON:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            default:
                                System.err.println("Error: incorrect token after semicolon " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case ASTERISK:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case LEFT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after asterisk " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case ASSIGN:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case LEFT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after assignment " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case SUBTRACTION:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case LEFT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after asterisk " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case EQUAL:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case LEFT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after asterisk " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    case NOT_EQUAL:
                        switch (listOfTokens.get(i + 1).getTokenName()) {
                            case IDENTIFIER:
                                break;
                            case LITERAL:
                                break;
                            case LEFT_PARENTHESIS:
                                break;
                            default:
                                System.err.println("Error: incorrect token after asterisk " + listOfTokens.get(i).getTokenValue());
                        }
                        i++;
                        break;
                    default:
                        System.err.println("Error: incorrect input");
                }
            }
            if (!isOnlyRepeat) {
                System.err.println("Error: NO UNTIL KEYWORD");
            }
        } else {
            System.err.println("Error: empty list of tokens");
        }
    }
}
