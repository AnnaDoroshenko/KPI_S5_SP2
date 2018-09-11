package Lab05;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzerDetailed {

    public SyntaxAnalyzerDetailed() {
    }

    public boolean analyseCorrectnessOfBrackets(List<Token> listOfTokens) {
        if (listOfTokens == null) {
            System.out.println("Empty expression found");
            return true;
        }
        List<Character> stack = new ArrayList<>();
        int i = 0;
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
                        System.out.println("'(' expected at " + i);
                        return false;
                    }
                    final char top = stack.remove(stackSize - 1);
                    if (top != '(') {
                        System.out.println("'(' expected at " + i);
                        return false;
                    }
                }
                break;

                case ("]"): {
                    final int stackSize = stack.size();
                    if (stackSize == 0) {
                        System.out.println("'[' expected at " + i);
                        return false;
                    }
                    final char top = stack.remove(stackSize - 1);
                    if (top != '[') {
                        System.out.println("'[' expected at " + i);
                        return false;
                    }
                }
                break;

                default:
            }
            i++;
        }
        if (stack.size() > 0) {
            System.out.println("Unmatched brackets");
            return false;
        }
        return true;
    }

    public boolean analyseCorrectnessOfNextToken(List<Token> listOfTokens) {
        if (listOfTokens == null) {
            System.out.println("Empty expression found");
            return true;
        }
        List<String> stack = new ArrayList<>();
        List<String> beginEndStack = new ArrayList<>();
        List<String> ifThenStack = new ArrayList<>();
        List<String> loopStack = new ArrayList<>();
        final TokenName firstTokenName = listOfTokens.get(0).getTokenName();
        switch (firstTokenName) {
            case KEYWORD_IF:
            case KEYWORD_REPEAT:
            case KEYWORD_FOR:
            case KEYWORD_DO:
            case KEYWORD_BEGIN:
            case IDENTIFIER:
                break;
            default:
                System.out.println("Unexpected first token");
                return false;
        }

        if (listOfTokens.get(listOfTokens.size() - 1).getTokenName() != TokenName.SEMICOLON) {
            System.out.println("Expected ';' in the end of expression");
        }

        int bracketCounter = 0;
        int assignsCounter = 0;
        if (listOfTokens.size() == 1) {
            return false;
        }
        for (int i = 0; i < listOfTokens.size() - 1; i++) {
            final String tokenValue = listOfTokens.get(i).getTokenValue();
            switch (tokenValue) {
                case ("repeat"):
                    stack.add("repeat");
                    break;

                case ("until"): {
                    final int stackSize = stack.size();
                    if (stackSize == 0) {
                        System.out.println("'repeat' expected at " + i);
                        return false;
                    }
                    final String top = stack.remove(stackSize - 1);
                    if (!top.equals("repeat")) {
                        System.out.println("'repeat' expected at " + i);
                        return false;
                    }
                    break;
                }

                case ("begin"):
                    beginEndStack.add("begin");
                    break;

                case ("end"): {
                    final int stackSize = beginEndStack.size();
                    if (stackSize == 0) {
                        System.out.println("'begin' expected at " + i);
                        return false;
                    }
                    final String top = beginEndStack.remove(stackSize - 1);
                    if (!top.equals("begin")) {
                        System.out.println("'begin' expected at " + i);
                        return false;
                    }
                    break;
                }

                case ("if"):
//                    boolean isThen = false;
                    int indexOfIf = i;
                    int indexOfThen = -1;
                    for (int ii = indexOfIf; ii < listOfTokens.size() - 1; ii++) {
                        final String currentValue = listOfTokens.get(ii).getTokenValue();
                        if (currentValue.equals("then")) {
//                            isThen = true;
                            indexOfThen = ii;
                            break;
                        }
                    }

                    if (indexOfThen == -1) {
                        System.out.println("Error: no matching 'then'");
                        return false;
                    }

                    boolean validExpression = false;
//                    if (true) {
//                    if (listOfTokens.get(indexOfIf + 1).getTokenValue().equals("(") && isThen &&
//                            listOfTokens.get(indexOfThen - 1).getTokenValue().equals(")")) {
                    int begin = i + 1;
                    int end = indexOfThen - 1;

                    for (int check = begin; check < end; check++) {
                        final String currentToken = listOfTokens.get(check).getTokenValue();
                        if (currentToken.equals("<") || currentToken.equals(">") || currentToken.equals("<=")
                                || currentToken.equals(">=") || currentToken.equals("=")) {
                            validExpression = true;
                            System.out.println("Mistake in if-then statement");
//                                return false;
                            break;
                        }

//                            break;
                    }
                    if (!validExpression) {
                        System.out.println("Mistake in if-then statement");
                        return false;
                    }
                    ifThenStack.add("if");
//                    }
                    break;

                case ("then"): {
                    int stackSize = ifThenStack.size();
                    if (stackSize == 0) {
                        System.out.println("'if' expected at " + i);
                        return false;
                    }
                    final String top = ifThenStack.remove(stackSize - 1);
                    if (!top.equals("if")) {
                        System.out.println("'if' expected at " + i);
                        return false;
                    }
                    break;
                }

                case ("for"):
                    loopStack.add("for");
                    break;

                case ("to"): {
                    final int stackSize = loopStack.size();
                    if (stackSize == 0) {
                        System.out.println("'for' expected at " + i);
                        return false;
                    }
                    final String top = loopStack.get(stackSize - 1);
                    if (!top.equals("for")) {
                        System.out.println("'for' expected at " + i);
                        return false;
                    }
                    loopStack.add("to");
                    break;
                }

                case ("do"): {
                    final int stackSize = loopStack.size();
                    if (stackSize == 0) {
                        System.out.println("Unexpected token at " + i);
                        return false;
                    }
                    final String top = loopStack.remove(stackSize - 1);
                    if (!top.equals("to")) {
                        System.out.println("Unexpected token at " + i);
                        return false;
                    }
                    loopStack.clear();
                    break;
                }

                default:
            }

            final TokenName tokenName = listOfTokens.get(i).getTokenName();
            final TokenName nextTokenName = listOfTokens.get(i + 1).getTokenName();
            final String token = listOfTokens.get(i).getTokenValue();
            System.out.println("Doing: " + tokenName + ", " + nextTokenName);
            switch (tokenName) {
                case KEYWORD_IF:
                    if (!((nextTokenName == TokenName.LEFT_PARENTHESIS) ||
                            (nextTokenName == TokenName.IDENTIFIER) ||
                            (nextTokenName == TokenName.LITERAL))) {
                        System.out.println("Unexpected token after keyword if");
                        return false;
                    }
                    break;
                case KEYWORD_THEN:
                    if (!((nextTokenName == TokenName.KEYWORD_BEGIN) ||
                            (nextTokenName == TokenName.KEYWORD_REPEAT) ||
                            (nextTokenName == TokenName.IDENTIFIER) ||
                            (nextTokenName == TokenName.KEYWORD_IF))) {
                        System.out.println("Unexpected token after keyword then");
                        return false;
                    }
                    break;
                case KEYWORD_REPEAT:
                    if (!((nextTokenName == TokenName.KEYWORD_BEGIN) ||
                            (nextTokenName == TokenName.IDENTIFIER) ||
                            (nextTokenName == TokenName.KEYWORD_FOR) ||
                            (nextTokenName == TokenName.LITERAL))) {
                        System.out.println("Unexpected token after keyword repeat");
                        return false;
                    }
                    break;
                case KEYWORD_UNTIL:
                    if (!((nextTokenName == TokenName.LEFT_PARENTHESIS) ||
                            (nextTokenName == TokenName.IDENTIFIER))) {
                        System.out.println("Unexpected token after keyword until");
                        return false;
                    }
                    break;
                case KEYWORD_OR:
                    if (!((nextTokenName == TokenName.LEFT_PARENTHESIS) ||
                            (nextTokenName == TokenName.LITERAL) ||
                            (nextTokenName == TokenName.IDENTIFIER))) {
                        System.out.println("Unexpected token after keyword or");
                        return false;
                    }
                    break;
                case KEYWORD_FOR:
                    if (!(nextTokenName == TokenName.IDENTIFIER)) {
                        System.out.println("Unexpected token after keyword for");
                        return false;
                    }
                    break;
                case KEYWORD_TO:
                    if (!(nextTokenName == TokenName.IDENTIFIER)) {
                        System.out.println("Unexpected token after keyword to");
                        return false;
                    }
                    break;
                case KEYWORD_DO:
                    if (!((nextTokenName == TokenName.KEYWORD_BEGIN) ||
                            (nextTokenName == TokenName.IDENTIFIER))) {
                        System.out.println("Unexpected token after keyword do");
                        return false;
                    }
                    break;
                case KEYWORD_BEGIN:
                    if (!((nextTokenName == TokenName.KEYWORD_IF) ||
                            (nextTokenName == TokenName.KEYWORD_REPEAT) ||
                            (nextTokenName == TokenName.KEYWORD_FOR) ||
                            (nextTokenName == TokenName.KEYWORD_BEGIN) ||
                            (nextTokenName == TokenName.IDENTIFIER) ||
                            (nextTokenName == TokenName.KEYWORD_DO))) {
                        System.out.println("Unexpected token after keyword begin");
                        return false;
                    }
                    break;
                case KEYWORD_END:
                    if (!((nextTokenName == TokenName.SEMICOLON) ||
                            (nextTokenName == TokenName.KEYWORD_UNTIL))) {
                        System.out.println("Unexpected token after keyword end");
                        return false;
                    }
                    break;

                case IDENTIFIER:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case KEYWORD_END:
                        case KEYWORD_DO:
                        case KEYWORD_THEN:
                        case ASSIGN:
                        case EQUAL:
                        case NOT_EQUAL:
                        case RIGHT_PARENTHESIS:
                        case RIGHT_BRACKET:
                        case LEFT_BRACKET:
                        case SEMICOLON:
                        case ADDITION:
                        case OBELISK:
                        case ASTERISK:
                        case SUBTRACTION:
                        case SYMBOL_LESS:
                        case SYMBOL_MORE:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case LITERAL:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case KEYWORD_THEN:
                        case KEYWORD_UNTIL:
                        case KEYWORD_OR:
                        case KEYWORD_TO:
                        case KEYWORD_DO:
                        case KEYWORD_BEGIN:
                        case KEYWORD_END:
                        case RIGHT_PARENTHESIS:
                        case RIGHT_BRACKET:
                        case SEMICOLON:
                        case ADDITION:
                        case OBELISK:
                        case SUBTRACTION:
                        case ASTERISK:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case LEFT_BRACKET:
                    bracketCounter++;
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case RIGHT_BRACKET:
                    bracketCounter--;
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case ASTERISK:
                        case SUBTRACTION:
                        case NOT_EQUAL:
                        case EQUAL:
                        case ASSIGN:
                        case SEMICOLON:
                        case RIGHT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case LEFT_PARENTHESIS:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case RIGHT_PARENTHESIS:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case KEYWORD_THEN:
                        case KEYWORD_OR:
                        case KEYWORD_TO:
                        case KEYWORD_DO:
                        case ASTERISK:
                        case SUBTRACTION:
                        case NOT_EQUAL:
                        case EQUAL:
                        case ASSIGN:
                        case SEMICOLON:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case SEMICOLON:
                    assignsCounter--;
                    if (assignsCounter < 0) {
                        System.out.println("Missed semicolon");
                        return false;
                    }
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case KEYWORD_IF:
                        case KEYWORD_REPEAT:
                        case KEYWORD_END:
                        case IDENTIFIER:
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case ASSIGN:
                    assignsCounter++;
                    if (bracketCounter != 0) {
                        System.out.println("Expected assignment");
                        return false;
                    }
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case SUBTRACTION:
                case ADDITION:
                case OBELISK:
                case ASTERISK:
                case SYMBOL_LESS:
                case SYMBOL_MORE:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case EQUAL:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                case NOT_EQUAL:
                    switch (listOfTokens.get(i + 1).getTokenName()) {
                        case IDENTIFIER:
                        case LITERAL:
                        case LEFT_PARENTHESIS:
                            break;
                        default:
                            System.out.println("Unexpected token after " + tokenName);
                            return false;
                    }
                    break;

                default:
                    return false;
            }

        }
        if (stack.size() > 0) {
            System.out.println("Unmatched repeat keyword. 'until' expected");
            return false;
        } else if (beginEndStack.size() > 0) {
            System.out.println("Unmatched begin keyword. 'end' expected");
            return false;
        } else if (ifThenStack.size() > 0) {
            System.out.println("Unmatched if keyword. 'then' expected");
            return false;
        } else if (loopStack.size() > 0) {
            System.out.println("Incorrect for block");
            return false;
        }
        return true;
    }
}
