package Lab05;

import java.util.ArrayList;
import java.util.List;

public class Token {

    private TokenName tokenName;
    private String tokenValue;
    List<Token> result = new ArrayList<Token>();

    public Token() {}

    public Token(TokenName tokenName, String tokenValue) {
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public TokenName getTokenName() {
        return tokenName;
    }

    public TokenName defineToken(String string) {
        switch (string) {
            case "repeat":
                tokenName = TokenName.KEYWORD_REPEAT;
                break;

            case "until":
                tokenName = TokenName.KEYWORD_UNTIL;
                break;

            case "or":
                tokenName = TokenName.KEYWORD_OR;
                break;

            case "if":
                tokenName = TokenName.KEYWORD_IF;
                break;

            case "then":
                tokenName = TokenName.KEYWORD_THEN;
                break;

            case "begin":
                tokenName = TokenName.KEYWORD_BEGIN;
                break;

            case "end":
                tokenName = TokenName.KEYWORD_END;
                break;

            case "for":
                tokenName = TokenName.KEYWORD_FOR;
                break;

            case "to":
                tokenName = TokenName.KEYWORD_TO;
                break;

            case "do":
                tokenName = TokenName.KEYWORD_DO;
                break;

            default:
                if (Character.isLetter(string.charAt(0))) {
                    tokenName = TokenName.IDENTIFIER;
                } else {
                    tokenName = TokenName.LITERAL;

                    for (int i = 1; i < string.length(); i++) {
                        if (Character.isLetter(string.charAt(i))) {
                            System.out.println("Error: invalid input of literal in " + string);
                            tokenName = TokenName.UNDEFINED;
                            break;
                        }
                    }
                }
        }

        return tokenName;
    }

    public String getAtom(String str, int i) {
        int j = i;
        for (; j < str.length(); ) {
            if (Character.isLetter(str.charAt(j))) {
                j++;
            } else if (Character.isDigit(str.charAt(j))) {
                j++;
            } else {

                return str.substring(i, j);
            }
        }

        return str.substring(i, j);
    }


    public List<Token> getListOfTokens(String input) {
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); ) {
            switch (input.charAt(i)) {
                case '-':
                    result.add(new Token(TokenName.SUBTRACTION, "-"));
                    i++;
                    break;
                case '+':
                    result.add(new Token(TokenName.ADDITION, "+"));
                    i++;
                    break;
                case '*':
                    result.add(new Token(TokenName.ASTERISK, "*"));
                    i++;
                    break;
                case '/':
                    result.add(new Token(TokenName.OBELISK, "/"));
                    i++;
                    break;

                case '(':
                    result.add(new Token(TokenName.LEFT_PARENTHESIS, "("));
                    i++;
                    break;

                case ')':
                    result.add(new Token(TokenName.RIGHT_PARENTHESIS, ")"));
                    i++;
                    break;

                case '[':
                    result.add(new Token(TokenName.LEFT_BRACKET, "["));
                    i++;
                    break;

                case ']':
                    result.add(new Token(TokenName.RIGHT_BRACKET, "]"));
                    i++;
                    break;

                case ';':
                    result.add(new Token(TokenName.SEMICOLON, ";"));
                    i++;
                    break;

                case '=':
                    result.add(new Token(TokenName.EQUAL, "="));
                    i++;
                    break;

                case ':':
                    if(input.charAt(i + 1) == '='){
                        result.add(new Token(TokenName.ASSIGN, ":="));
                        i += 2;
                    } else {
                        System.out.println("Error: unknown operator at '" + input.charAt(i) + "' at " + i);
                        System.exit(-1);
                    }
                    break;

//                case '!':
//                    if(input.charAt(i + 1) == '='){
//                        result.add(new Token(TokenName.NOT_EQUAL, "!="));
//                        i += 2;
//                    } else {
//                        System.err.println("Error: unknown operator at " + i );
//                        System.exit(-1);
//                    }
//                    break;

                case '>':
                    result.add(new Token(TokenName.SYMBOL_MORE, ">"));
                    i++;
                    break;

                case '<':
                    if(input.charAt(i + 1) == '>') {
                        result.add(new Token(TokenName.NOT_EQUAL, "<>"));
                        i += 2;
                    } else{
                        result.add(new Token(TokenName.SYMBOL_LESS, "<"));
                        i++;
                    }
                    break;

                default:
                    if (input.charAt(i) == ' ') {
                        i++;
                    } else if (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i))) {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        result.add(new Token(defineToken(atom), atom));
                    } else{
                        System.out.println("Error: unknown operator '" + input.charAt(i) + "' at " + i);
                        result.add(new Token(TokenName.UNDEFINED, "" + input.charAt(i)));
                        i++;
                    }
                    break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return tokenValue + " :: " + tokenName.name();
    }
}

