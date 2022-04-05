package MuratBank;

public class variables {
    private static String kartno = "1234";
    private static int sifre = 1234;
    static boolean sifreOnay;
    static boolean ibanNoKontrolOnay;
    private static double bakiye = 10000;

    public static double getBakiye() {
        return bakiye;
    }

    public static void setBakiyeParaGirisi(double girilecekmiktar) {
        bakiye += girilecekmiktar;
    }

    public static void setBakiyeParaCikisi(double eksilecekmiktar) {
        bakiye -= eksilecekmiktar;
    }

    public static String getKartno() {
        return kartno;
    }

    public static void setKartno(String kartno) {
        variables.kartno = kartno;
    }

    public static int getSifre() {
        return sifre;
    }

    public static void setSifre(int sifre) {
        variables.sifre = sifre;
    }
}
