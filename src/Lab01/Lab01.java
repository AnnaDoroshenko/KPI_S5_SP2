/**
 * System programming
 * Lab 1. MODULAR PROGRAMMING WITHIN THE BASIC SYSTEM
 * OF PROGRAM DESIGN AND ITS USE FOR TABLET PROCESSING PROGRAMS
 *
 * @variant: 16
 * @author: Anna Doroshenko
 * @group: IO-52
 * @date: 25.09.2017 --
 */

package Lab01;

public class Lab01 {

    public static void main(String[] args) {
        Table testTable = new Table();
        testTable.insertDirect(new Row(new Key("ajhat", (byte)4), 2.5f), 0);
        testTable.insertDirect(new Row(new Key("ahjsh", (byte)1), 12.6f), 1);
        testTable.insertDirect(new Row(new Key("abbcg", (byte)21), 7.9f), 2);
        testTable.insertDirect(new Row(new Key("jkfaa", (byte)7), 3.21f), 3);
        testTable.insertDirect(new Row(new Key("abjht", (byte)4), 2.5f), 4);
//        System.out.println(testTable);
//        System.out.println("-----------------------------------------------------------------------");

//        System.out.println(testTable.selectDirect(3));
//        System.out.println(testTable.selectDirect(5));
//        System.out.println(testTable.deleteDirect(1));
//        System.out.println(testTable.updateDirect(new Row(new Key("horool", (byte)95), 61.2f), 5));
//        System.out.println("-----------------------------------------------------------------------");
//
//        System.out.println(testTable.isEqual(new Row(new Key("jkfaa", (byte)7), 5.6f), new Key("jkfaa", (byte)7)));
//        System.out.println("-----------------------------------------------------------------------");
//
//        System.out.println(testTable.insertLinear(new Row(new Key("igorek", (byte)71), 100.0f)));
//        System.out.println(testTable.selectLinear(new Key("abbcg", (byte)8)));
//        System.out.println(testTable.deleteLinear(new Row(new Key("jkfaa", (byte)7), 3.21f)));
//        System.out.println(testTable.updateLinear(new Key("jkfaa", (byte)7), new Row(new Key("horsh", (byte)23), 32.5f)));
//        testTable.packLinear();
//        System.out.println(testTable);
//        System.out.println("-----------------------------------------------------------------------");
//
//        System.out.println(testTable.selectBinary(new Key("horool", (byte)95)));
//        testTable.insertBinary(new Row(new Key("ghsdsaj", (byte)45), 63.75f));
//        System.out.println(testTable.deleteBinary(new Row(new Key("jkfaa", (byte)7), 3.21f)));
//        System.out.println(testTable.updateBinary(new Key("ahjsh", (byte)1), new Row(new Key("kdfjd", (byte)56), 53.68f)));
//        testTable.packLinear();
//        System.out.println(testTable);
//        System.out.println("-----------------------------------------------------------------------");
        System.out.println(testTable);
        System.out.println("-----------------------------------------------------------------------");
//
        System.out.println(testTable.selectMostSimilar(new Key("BAAJHA", (byte)14)));
//        System.out.println(testTable.selectMostSimilar(new Key("baAjHa", (byte)14)));
//        System.out.println("-----------------------------------------------------------------------");
//
//        System.out.println(testTable.getAmount());
    }
}
