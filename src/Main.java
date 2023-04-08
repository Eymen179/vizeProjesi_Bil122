import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); //Aşağıda kullanılacak işlem seçimleri için "Scanner" tanımlanır.

        //Mailleri kaydetmek için Arraylist tipinde liste değişkenleri tanımlanır.
        ArrayList<String> vipMember = new ArrayList<>();
        ArrayList<String> member = new ArrayList<>();
        ArrayList<String> everyMember = new ArrayList<>();

        //Dosya yolu tanımlanır.
        File f = new File("C:\\Users\\eymen\\Documents\\Java Çalışmaları\\vizeProject\\src\\Kullanıcılar.txt");

        txtSubjects(f);//Boş olan dosyaya 2 adet başlık tanımlamaya yarayan metot

        //Liste atamaları ve mail gönderimine yarayan classlar için nesne tanımları
        AllMemberList allMemberList = new AllMemberList();
        EmailManager emailManager = new EmailManager();

        char process1 = 0, process2;//işlem seçimleri için char tipi değişkenler

        System.out.println("Hoşgeldiniz!!");
        //"Çıkış" işlemi seçilene kadar kod çalışmaya devam eder.
        while (process1 != '4') {
            System.out.println("----------------------------------------------");
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz:");
            System.out.println("1- Elit Üye Ekleme \t2- Genel Üye Ekleme \n3- Mail Gönderme \t4- Çıkış ");
            System.out.print("==> ");
            process1 = s.next().charAt(0);

            //Kullanıcıdan girdi alınarak yapmak istenilen işlem seçilir.
            switch (process1) {
                //Kullanıcının girdiği ad, soyad ve e-postayı "vipMember" ya da "member" listesine ve
                //dosyada "ELİT ÜYELER" ya da "GENEL ÜYELER" başlığının altına atama işlemi yapan metotlar
                case '1' -> allMemberList.vipMemberList(vipMember, member, f, new VipMember());
                case '2' -> allMemberList.memberList(member, f, new Member());

                //Mail gönderme metodu
                case '3' -> {
                    //"member" ve "vipMember" listelerindeki bilgiler "everyMember" adında tek bir liste değişkenine atanır.
                    everyMember.addAll(member);
                    everyMember.addAll(vipMember);

                    System.out.println("----------------------------------------------");
                    System.out.println("Kimlere mail göndermek istersiniz?");
                    System.out.println("1- Elit Üyeler \n2- Genel Üyeler \n3- Tüm Üyeler ");
                    System.out.print("==> ");
                    process2 = s.next().charAt(0);
                    switch (process2) {
                        //Mail göndermeye yarayan "emailManagaer" class'ına seçilen liste değişkeni gönderilir.
                        case '1' -> emailManager.emailSend(vipMember);
                        case '2' -> emailManager.emailSend(member);
                        case '3' -> emailManager.emailSend(everyMember);
                    }
                }
                //Seçiildiğinde çalışan kod durdurulur ve sistemden çıkış yapılır.
                case '4' -> {
                    System.out.println("----------------------------------------------");
                    System.out.println("Çıkmak istediğinizden Emin misiniz?(Evet-1, Hayır-2)");
                    System.out.println("Eğer çıkarsanız listelerdeki ve dosyadaki veriler kod tekrar çalıştığında silinecek.");
                    System.out.print("==> ");
                    process3 = s.next().charAt(0);
                    if(process3=='1'){
                        System.out.println("----------------------------------------------");
                        System.out.println("Sistemden çıkış yapılmıştır.");
                        System.out.println("----------------------------------------------");
                    }else if(process3=='2'){
                        continue;
                    }else{
                        System.out.println("Hatalı giriş");
                    }
                }
                //"case" bölümlerinde belirtilen değerler dışında değer yazılırsa çalışacak mesaj
                default -> System.out.println("Hatalı giriş!! Tekrar Deneyin!");
            }
        }
    }

    public static void txtSubjects(File f) {
        try {
            //İçi boş olan Kullanıcılar.txt dosyasına başlıklar yazılır.
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));//Dosya yazdırma işlemi için gereken tanımlama
            bw.write("ELİT ÜYELER\n");  //İlk başlık yazdırılır.
            bw.newLine();                  //Yeni satıra geçilir.
            bw.write("GENEL ÜYELER\n");//İlk başlık yazdırılır.
            bw.newLine();                  //Yeni satıra geçilir.
            bw.close();                    //Dosya, yazma işlemine kapatılır.
        } catch (FileNotFoundException e) {//Olası bir hata durumunda hata mesajı buradan gönderilir
            e.printStackTrace();           //ve kod durdurulmadan çalışmaya devam eder.
        } catch (Exception e) { //Üst satırda belirtilen spesifik hata alıcı dışında genel hata alıcı tanımı
            e.printStackTrace();
        }
    }
}
