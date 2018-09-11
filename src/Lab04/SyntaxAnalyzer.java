package Lab04;

import Lab03.Token;
import Lab03.TokenName;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {

    public SyntaxAnalyzer() {
    }

    public boolean analyseCorrectnessOfBrackets(List<Token> listOfTokens) {
        if (listOfTokens == null) {
            return true;
        }
        List<Character> stack = new ArrayList<>();
        for (Token token : listOfTokens) {
            final String tokenValue = token.getTokenValue();
            switch (tokenValue) {
                case ("("):
                    stack.add('(');
                    break;

                case ("["):
                    stack.add('[');
                    break;

                case (")"): {
                    final int stackSize = stack.size();
                    if (stackSize == 0) {
                        return false;
                    }
                    final char top = stack.remove(stackSize - 1);
                    if (top != '(') {
                        return false;
                    }
                }
                break;

                case ("]"): {
                    final int stackSize = stack.size();
                    if (stackSize == 0) {
                        return false;
                    }
                    final char top = stack.remove(stackSize - 1);
                    if (top != '[') {
                        return false;
                    }
                }
                break;

                default:
            }
        }
        if (stack.size() > 0) {
            return false;
        }
        return true;
    }

    public boolean analyseCorrectnessOfNextToken(List<Token> listOfTokens) {
        if (listOfTokens == null) {
            return true;
        }
        List<String> stack = new ArrayList<>();
        final TokenName firstTokenName = listOfTokens.get(0).getTokenName();
        switch (firstTokenName) {
            case KEYWORD:
            case IDENTIFIER:
                break;
                default:
                    return false;
        }

        int bracketCounter = 0;
        int assignsCounter = 0;
        for (int i = 0; i < listOfTokens.size() - 1; i++) {
//            System.out.println(i);
            final String tokenValue = listOfTokens.get(i).getTokenValue();
            switch (tokenValue) {
                case ("repeat"):
                    stack.add("repeat");
                    break;

                case ("until"):
                    final int stackSize = stack.size();
                    if (stackSize == 0) {
                        return false;
                    }
                    final String top = stack.remove(stackSize - 1);
                    if (!top.equals("repeat")) {
                        return false;
                    }
                    break;
                default:
            }

            final TokenName tokenName = listOfTokens.get(i).getTokenName();
            switch (tokenName) {
                case KEYWORD:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LITERAL:
                            break;
                        case RIGHT_PARENTHESIS:
                            break;
                        default:
                            return false;
                    }
                    break;

                case IDENTIFIER:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case ASSIGN:
                        case EQUAL:
                        case NOT_EQUAL:
                        case RIGHT_PARENTHESIS:
                        case RIGHT_BRACKET:
                        case LEFT_BRACKET:
                        case SEMICOLON:
                        case SUBTRACTION:
                            break;
                        default:
                            return false;
                    }
                    break;
                case LITERAL:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case KEYWORD:
                            break;
                        case RIGHT_PARENTHESIS:
                            break;
                        case RIGHT_BRACKET:
                        case SEMICOLON:
                            break;
                        case ADDITION:
                        case OBELISK:
                        case SUBTRACTION:
                        case ASTERISK:
                            break;
                        default:
                            return false;
                    }
                    break;
                case LEFT_BRACKET:
                    bracketCounter++;
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LITERAL:
                            break;
                        default:
                            return false;
                    }
                    break;
                case RIGHT_BRACKET:
                    bracketCounter--;
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
                        case RIGHT_PARENTHESIS:
                            break;
                        default:
                            return false;
                    }
                    break;
                case LEFT_PARENTHESIS:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LITERAL:
                            break;
                        default:
                            return false;
                    }
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
                            return false;
                    }
                    break;
                case SEMICOLON:
                    assignsCounter--;
                    if (assignsCounter < 0) {
                        return false;
                    }
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            return false;
                    }
                    break;
                case ASSIGN:
                    assignsCounter++;
                    if (bracketCounter != 0) {
                        return false;
                    }
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LITERAL:
                            break;
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            return false;
                    }
                    break;
                case SUBTRACTION:
                case ADDITION:
                case OBELISK:
                case ASTERISK:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LITERAL:
                            break;
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            return false;
                    }
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
                            return false;
                    }
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
                            return false;
                    }
                    break;
                default:
                    return false;
            }

        }
        if (stack.size() > 0) {
            return false;
        }
        return true;
    }
}
