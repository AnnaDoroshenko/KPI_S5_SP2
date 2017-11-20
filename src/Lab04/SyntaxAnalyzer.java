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
        for (int i = 0; i < listOfTokens.size() - 1; ) {
            final String tokenValue = listOfTokens.get(i).getTokenValue();
            switch (tokenValue) {
                case ("repeat"):
                    stack.add("repeat");
                    i++;
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
                    i++;
                    break;
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
                        case RIGHT_BRACKET:
                            break;
                        case SEMICOLON:
                            break;
                        case SUBTRACTION:
                            break;
                        default:
                            return false;
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
                            return false;
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
                            return false;
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
                        case RIGHT_PARENTHESIS:
                            break;
                        default:
                            return false;
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
                            return false;
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
                            return false;
                    }
                    i++;
                    break;
                case SEMICOLON:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                            break;
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            return false;
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
                            return false;
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
                            return false;
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
                            return false;
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
                            return false;
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
                            return false;
                    }
                    i++;
                    break;
                default:
                    i++;
            }

        }
        if (stack.size() > 0) {
            return false;
        }
        return true;
    }
}
