package Lab03;

import java.util.ArrayList;
import java.util.List;

public class Token {

    private TokenName tokenName;
    private String tokenValue;

    public Token(){

    }

    public Token(TokenName tokenName, String tokenValue) {
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
    }

    public TokenName defineToken(String string){
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

            case "n":
                tokenName = TokenName.IDENTIFIER;
                break;

            case "0":
                tokenName = TokenName.LITERAL;
                break;

            case "1":
                tokenName = TokenName.LITERAL;
                break;

            case ":=":
                tokenName = TokenName.ASSIGN;
                break;

            case "!=":
                tokenName = TokenName.NOT_EQUAL;
                break;

            default:
                tokenName = TokenName.IDENTIFIER;
                break;
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
        List<Token> result = new ArrayList<Token>();
        for (int i = 0; i < input.length(); ) {
            switch (input.charAt(i)) {
                case '-':
                    result.add(new Token(TokenName.SUB, "-"));
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

                default:
                    if (Character.isWhitespace(input.charAt(i))) {
                        i++;
                    } else {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        result.add(new Token(defineToken(atom), atom));
                    }
                    break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return tokenName.name() + " :: " + tokenValue;
    }
}

