import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AllMemberList {
    public void vipMemberList(ArrayList<String> vipMember, ArrayList<String> member, File f, MemberInfo memberInfo) {
        //Kullanıcıdan isim, soyisim ve e-posta bilgilerinin girilmesi istenir.
        Scanner s = new Scanner(System.in);
        System.out.print("İsim girişi ==> ");
        memberInfo.name = s.next();
        System.out.print("Soyisim girişi ==> ");
        memberInfo.surname = s.next();
        System.out.print("E-posta girişi ==> ");
        memberInfo.email = s.next();

        //Girilen bilgiler "newMember" adlı String değişkeninde birleştirilir.
        String newMember = memberInfo.name + "    " + memberInfo.surname + "    " + memberInfo.email;

        vipMember.add(newMember);//"newMember" değişkeni "vipMember" listesine atanır.

        try {
            //"ELİT ÜYELER" başlığının altına gereken bilgiler yazılırken dosyanın tümünde değişiklik yapılır.
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));//Dosya yazdırma işlemi için gereken tanımlama

            bw.write("ELİT ÜYELER\n");//Başlık dosyaya yazdırılır

            //Başlığın devamına "vipMember" listesindeki bilgiler atanır.
            for (String elitUye : vipMember) {
                bw.write(elitUye);
                bw.newLine(); //Yeni satıra geçilir.
            }
            bw.write("GENEL ÜYELER\n");//2. başlık dosyaya yazdırılır

            //2. başlığın devamına "member" listesindeki bilgiler atanır.
            for (String elitUye : member) {
                bw.write(elitUye);
                bw.newLine(); //Yeni satıra geçilir.
            }

            bw.close(); //Dosya, yazdırma işlemine kapatılır.
        } catch (IOException e) {//Olası bir hata durumunda hata mesajı buradan gönderilir
            e.printStackTrace(); //ve kod durdurulmadan çalışmaya devam eder.
        }
    }

    public void memberList(ArrayList<String> member, File f, MemberInfo memberInfo) {
        //Kullanıcıdan isim, soyisim ve e-posta bilgilerinin girilmesi istenir.
        Scanner s = new Scanner(System.in);
        System.out.print("İsim girişi ==> ");
        memberInfo.name = s.next();
        System.out.print("Soyisim girişi ==> ");
        memberInfo.surname = s.next();
        System.out.print("E-posta girişi ==> ");
        memberInfo.email = s.next();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));//Dosya yazdırma işlemi için gereken tanımlama

            //Girilen bilgiler "newMember" adlı String değişkeninde birleştirilir.
            String newMember = memberInfo.name + "    " + memberInfo.surname + "    " + memberInfo.email;

            member.add(newMember);//"newMember" değişkeni "member" listesine atanır.

            bw.write(newMember);//Dosyanın sonuna "newMember" değişkeni atanır.
            bw.newLine();       //Yeni satıra geçilir.
            bw.close();         //Dosya, yazdırma işlemine kapatılır.
        } catch (IOException e) {//Olası bir hata durumunda hata mesajı buradan gönderilir
            e.printStackTrace(); //ve kod durdurulmadan çalışmaya devam eder.
        }
    }
}
