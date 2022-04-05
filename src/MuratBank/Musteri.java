package MuratBank;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Musteri {

    public static Scanner scan = new Scanner(System.in);

    public static void girisEkrani() {
        System.out.println("BANKAMİZA HOS GELDINIZ.");
        System.out.println("Lutfen kart no ve sifre giriniz");
        sifreKontrol();
    }

    public static void sifreKontrol() {
        int kalanhak = 3;
        while (kalanhak > 0) {
            System.out.print("Kart no : ");
            String girilenKartNo = scan.nextLine();
            System.out.print("Sifre   : ");
            int girilenSifre = scan.nextInt();

            if (girilenKartNo.contains(" ")) {
                girilenKartNo = girilenKartNo.replace(" ", "");
            }
            if (girilenKartNo.equals(variables.getKartno()) && (girilenSifre==variables.getSifre())) {
                variables.sifreOnay = true;
                System.out.println("Giris Basarili");
                anaMenu();
            } else {
                kalanhak--;
                System.out.println("Giris Hatali. Tekrar deneyiniz");
                System.out.println("Kalan hak : " + kalanhak);
            }
            if (kalanhak == 0) {
                System.out.println("Sifrenizi 3 defa yanlis girfiniz. \n" +
                        "Hesabiniz bloke edildi. Lutfen Musteri Hizmetleri ile gorusun. İyi gunler");
            }
        }
    }

    public static void anaMenu() {
        while (variables.sifreOnay) {
            System.out.println("Bakiye Sorgulama : 1");
            System.out.println("Para Yatirma     : 2");
            System.out.println("Para Cekme       : 3");
            System.out.println("Para Gonderme    : 4");
            System.out.println("Sifre Degistirme : 5");
            System.out.println("Cikis            : 6");
            int secim = Musteri.scan.nextInt();

            switch (secim) {
                case 1:
                    bakiyeSorgulama();
                    break;
                case 2:
                    paraYatirma();
                    break;
                case 3:
                    paraCekme();
                    break;
                case 4:
                    paraGonderme();
                    break;
                case 5:
                    sifreDegistirme();
                    break;
                case 6:
                    cikis();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Hatali secim. Tekrar deneyiniz.");
                    break;
            }
        }
    }

    public static void bakiyeSorgulama() {
        System.out.println("Mevcut Hesap bakiyeniz : " + variables.getBakiye());
    }

    public static void paraYatirma() {
        System.out.println("Ne kadar para yatirmak istiyorsunuz ? ");
        double girilecekmiktar = scan.nextDouble();
        variables.setBakiyeParaGirisi(girilecekmiktar);
        bakiyeSorgulama();
    }

    public static void paraCekme() {
        System.out.println("Ne kadar para cekmek istiyorsunuz ? ");
        double cekilecekmiktar = scan.nextDouble();
        if (cekilecekmiktar > variables.getBakiye()) {
            System.out.println("Bakiyenizde yeterli miktar bulunmamaktadir.");
            bakiyeSorgulama();
        } else {
            variables.setBakiyeParaCikisi(cekilecekmiktar);
            bakiyeSorgulama();
        }
    }

    public static void paraGonderme() { //EFT

        ibanNOKontrol();
        if (variables.ibanNoKontrolOnay) {
            System.out.print("Gondermek istediginiz miktar (TL) : ");
            double gonderilecekMiktar = scan.nextDouble();
            if (gonderilecekMiktar > variables.getBakiye()) {
                System.out.println("Bakiyenizde yeterli miktar bulunmamaktadir.");
                bakiyeSorgulama();
                paraGonderme();
            } else {
                System.out.print("Transfer isleminiz gerceklesti. ");
                transferSonrakiBakiyeSorgulama(gonderilecekMiktar);
            }
        }
    }

    public static void transferSonrakiBakiyeSorgulama(double gonderilecekMiktar) {
        variables.setBakiyeParaCikisi(gonderilecekMiktar);
        bakiyeSorgulama();
    }

    public static void ibanNOKontrol() { //TR123456789456123123456789
        int kalanhak = 0;
        while (true) {
            System.out.print("IBAN no giriniz : ");
            String ibanNO = scan.next();
            if (ibanNO.startsWith("TR") && ibanNO.length() == 26) {
                variables.ibanNoKontrolOnay = true;
                break;
            } else {
                kalanhak++;
                System.out.println("Girdiginiz IBAN No hatali.");
                System.out.println("TR ile baslamali ve 26 karakter olmali. Tekrar deneyiniz.");
                if (kalanhak == 3) {
                    System.out.println("IBAN No 3 kez hatali girdiniz. İsleminiz iptal edildi.");
                    System.exit(0);
                }
            }
        }
    }

    public static void sifreDegistirme() {

        sifreDegisimEskiSifreKontrol();
        if (variables.sifreOnay) {
            System.out.println("Yeni sifreyi giriniz:");
            int yenisifre = scan.nextInt();
            System.out.println("Yeni sifreyi tekrar giriniz:");
            int yenisifreTekrar = scan.nextInt();
            if (yenisifre == yenisifreTekrar) {
                System.out.println("Sifreniz basarili bir sekilde degistirildi.");
                variables.setSifre(yenisifre);
                anaMenu();
            } else {
                System.out.println("İki sifre birbiri ile uyusmuyor.");
                sifreDegistirme();
            }
        } else {
            System.out.println("Sifrenizi yanlis girdiniz.");
            sifreDegistirme();
        }
    }

    public static void sifreDegisimEskiSifreKontrol() {
        int kalanhak = 3;
        do {
            System.out.println("Mevcut sifrenizi giriniz : ");
            int girilenSifre = scan.nextInt();
            if (girilenSifre == variables.getSifre()) {
                variables.sifreOnay = true;
                break;
            } else {
                System.out.println("Yanlis Sifre. Kalan hak : " + --kalanhak);
            }
        } while (kalanhak > 0);
        if (kalanhak == 0) {
            System.out.println("Sifreniz bloke edildi. Musteri Hizmetleri ile irtibata geciniz. ");
            System.exit(0);
        }
    }

    public static void cikis() {
        System.out.println("Mutlu Gunler Dileriz.");
    }
}

