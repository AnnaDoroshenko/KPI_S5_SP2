package Lab03;

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
                tokenName = TokenName.KEYWORD;
                break;

            case "until":
                tokenName = TokenName.KEYWORD;
                break;

            case "or":
                tokenName = TokenName.KEYWORD;
                break;

            default:
                if (Character.isLetter(string.charAt(0))) {
                    tokenName = TokenName.IDENTIFIER;
                } else {
                    tokenName = TokenName.LITERAL;

                    for (int i = 1; i < string.length(); i++) {
                        if (Character.isLetter(string.charAt(i))) {
                            System.err.println("Error: invalid input of literal in " + string);
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
        for (int i = 0; i < input.length(); ) {
            switch (input.charAt(i)) {
                case '-':
                    result.add(new Token(TokenName.SUBTRACTION, "-"));
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
                        System.err.println("Error: unknown operator at " + i );
                        System.exit(-1);
                    }
                    break;

                case '!':
                    if(input.charAt(i + 1) == '='){
                        result.add(new Token(TokenName.NOT_EQUAL, "!="));
                        i += 2;
                    } else {
                        System.err.println("Error: unknown operator at " + i );
                        System.exit(-1);
                    }
                    break;

                case '>':
                    result.add(new Token(TokenName.SYMBOL_MORE, ">"));
                    i++;
                    break;

                case '<':
                    result.add(new Token(TokenName.SYMBOL_LESS, "<"));
                    i++;
                    break;

                default:
                    if (input.charAt(i) == ' ') {
                        i++;
                    } else if (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i))) {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        result.add(new Token(defineToken(atom), atom));
                    } else{
                        System.err.println("Error: unknown operator at " + i );
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

