package Lab01;

public class Table {

    final private int N = 100;
    private Row[] table;
    private int amount;

    public Table() {
        table = new Row[N];
        amount = 0;
    }

    public Row selectDirect(int index) {
        return table[index];
    }

    public Row insertDirect(Row element, int index) {
        if (index > amount) {
            return null;
        }
        while (index < amount) {
            table[index++] = new Row();
        }
        table[index++] = element;
        amount = index;
        return table[index - 1];
    }

    public Row deleteDirect(int index) {
        if (index < 0 || index > amount) {
            System.out.println("Problem in deleting");
        } else {
            table[index].markDeleted();
        }
        return table[index];
    }

    public Row updateDirect(Row element, int index) {
        if (index >= amount) {
            return null;
        } else {
            table[index] = element;
        }
        return element;
    }
    //------------------------------------------------------------------------------------------------------------------

    public boolean isEqual(Row element, Key key) {
        return element.getKey().getStringKey().equals(key.getStringKey()) &&
                element.getKey().getNumKey() == key.getNumKey();
    }

    public int compareStrings(String str1, String str2) {
        char[] str1Chars = str1.toLowerCase().toCharArray();
        char[] str2Chars = str2.toLowerCase().toCharArray();
        int i = 0;
        while (str1Chars[i] == str2Chars[i] && str1Chars[i] != 0) {
            i++;
        }
        return str1Chars[i] - str2Chars[i];
    }

    public int compareKeys(Row element, Key key) {
        int i = compareStrings(element.getKey().getStringKey(), key.getStringKey());
        if (i != 0) return i;
        else return element.getKey().getNumKey() - key.getNumKey();
    }

    public int compareKeys2(Key key1, Key key2) {
        int i = compareStrings(key1.getStringKey(), key2.getStringKey());
        if (i != 0) return i;
        else return key1.getNumKey() - key2.getNumKey();
    }

    //------------------------------------------------------------------------------------------------------------------

    public Row selectLinear(Key key) {
        for (int i = 0; i < amount; i++) {
            if (isEqual(table[i], key)) {
                return table[i];
            }
        }

        return null;

//        while (--amount >= 0 && !isEqual(table[amount], key));
//        if (amount < 0) return null;
//        return table[amount];
    }


    public Row insertLinear(Row element) {
        int n = 0;
        while (n < amount && table[n].getKey().getStringKey().toCharArray()[0] != 0) n++;
        if (n == amount) amount++;
        table[n] = element;
        return table[n];
    }

    public Row deleteLinear(Row element) {
        Row deleteElement = selectLinear(element.getKey());
        if (deleteElement != null) deleteElement.markDeleted();
        return deleteElement;
    }

    public Row updateLinear(Key key, Row element) {
        Row updateElement = selectLinear(key);
        if (updateElement != null) updateElement = element;
        return updateElement;
    }

    public Row[] packLinear() {
        int n = 0;
        int ln = 0;
        while (n < amount) {
            if (table[n].getDeleted()) {
                n++;
                continue;
            }
            table[ln] = table[n];
            n++;
            ln++;
        }
        for (int i = ln; i < n; i++) {
            table[i] = null;
        }
        amount = ln;
        return table;
    }

    //------------------------------------------------------------------------------------------------------------------
    public Row[] sort() {
        int n = 0, n1;
        Row element;
        for (; n < amount; n++) {
            for (n1 = n + 1; n1 < amount; n1++) {
                if (compareKeys(table[n], table[n1].getKey()) > 0) {
                    element = table[n];
                    table[n] = table[n1];
                    table[n1] = element;
                }
            }
        }
        return table;
    }

    public Row selectBinary(Key key) {
        sort();

        int l = 0;
        int r = amount - 1;

        while (r >= l) {
            int m = (l + r) / 2;
            int comparison = compareKeys(table[m], key);
            if (comparison == 0) {
                return table[m];
            } else if (comparison > 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return null;
    }

    public Row insertBinary(Row element) {
        Key tableKey = element.getKey();
        sort();

        int l = 0;
        int r = amount - 1;

        int m = 0;
        int comparison = 0;
        while (r >= l) {
            m = (l + r) / 2;
            comparison = compareKeys(table[m], tableKey);
            if (comparison == 0) {
                table[m] = element;
                return table[m];
            } else if (comparison > 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        final int placeToInsert = m + (comparison >= 0 ? 0 : 1);
        for (int i = amount; i > placeToInsert; i--) {
            table[i] = table[i - 1];
        }
        table[placeToInsert] = element;
        amount++;
        return table[placeToInsert];
    }

    public Row deleteBinary(Row element) {
        Row deleteElement = selectBinary(element.getKey());
        if (deleteElement != null) deleteElement.markDeleted();
        return deleteElement;
    }

    public Row updateBinary(Key key, Row element) {
        Row updateElement = selectBinary(key);
        if (updateElement != null) updateElement = element;
        return updateElement;
    }

    //------------------------------------------------------------------------------------------------------------------
    public int compareStrings2(String string1, String string2) {
        final String str1 = string1.toLowerCase();
        final String str2 = string2.toLowerCase();
        int index1 = 0;
        int index2 = 0;
        int amount = 0;

        while (index1 < str1.length()) {
            char c1 = str1.charAt(index1);
            for (int i2 = index2; i2 < str2.length(); i2++) {
                char c2 = str2.charAt(i2);
                if (c1 == c2) {
                    amount++;
                    index2 = i2 + 1;
                    break;
                }
            }
            index1++;
        }
//        System.out.println("here: " + amount);
        return amount;
    }

    public Row selectMostSimilar(Key key) {
        Row bestFitRow = null;
        int currentMax = -1;
        for (Row row : table) {
            if (row == null) {
                continue;
            }
            final int sim1 = compareStrings2(key.getStringKey(), row.getKey().getStringKey());
            final int sim2 = compareStrings2(row.getKey().getStringKey(), key.getStringKey());
            final int similarity = sim1 > sim2 ? sim1 : sim2;
            if (similarity > currentMax) {
//                System.out.println("Found new best: " + similarity+ " " + row.getField());
                currentMax = similarity;
                bestFitRow = row;
            }
        }
        return bestFitRow;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: \n");
        for (Row row : table) {
            if (row != null) {
                sb.append(row.toString());
            }
        }
        return sb.toString();
    }

    public int getAmount() {
        return amount;
    }
}