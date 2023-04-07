import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Properties;
//"EmailManager" adlı class "gmail" tipindeki e-postalara mail göndermek için yapılmıştır.
public class EmailManager {
    public void emailSend(ArrayList<String> emails) {
        //Mail gönderilirken oluşan bekleme süresinde gözüken mesaj
        System.out.println("----------------------------------------------");
        System.out.println("Toplu mail gönderiliyor...");

        //Parametre olarak gelen listedeki "isim  soyisim  e-posta" şeklinde bulunan satırlardan
        //"e-posta" kısımları ayıklanır ve başka bir liste değişkenine kaydedilir.
        ArrayList<String> sendEmail = new ArrayList<>();
        for (String email : emails) {
            String[] parts = email.split("    ");
            for (String part : parts) {
                if (part.contains("@")) {
                    sendEmail.add(part);
                }
            }
        }

        //Mail gönderecek kişinin e-posta bilgisi ve "gmail" için uygulama şifresi
        String senderEmail = "ornek@gmail.com";
        String senderPassword = "***";

        //Mail göndermek için kullanılan Gmail SMTP sunucusunun adres ve bağlantı noktası belirtilir.
        String host = "smtp.gmail.com";
        int port = 587;

        //Gönderiecek mailin konu başlığı ve gövdesi
        String subject = "Toplu Mail Gönderme Testi";
        String body = "Bu bir toplu mail gönderme testidir.";

        //"Properties" class'ı için tanımlanan "props" nesnesi, SMTP ayarları için gereken özellikleri tutar.
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        //"Session.getInstance" metodu mail göndermek için kullanılan oturumu başlatır.
        Session s = Session.getInstance(props, new javax.mail.Authenticator() {
            //Kullanıcının e-posta ve uygulama şifresi bilgilerini doğrulama işlemi yapılır.
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            //"Message" adlı class, gönderilecek mail için gereken bilgi ve özellikleri ayarlar
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(senderEmail));//Kullanıcı e-postası

            //Mailin gönderileceği e-posta(lar) ayarlanır.
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", sendEmail)));

            //Mailin konu başlığı ve gövdesi ayarlanır.
            msg.setSubject(subject);
            msg.setText(body);

            //E-posta gönderimini sağlayan metot
            Transport.send(msg);

            //E-posta gönderildiğinde çıkan mesaj
            System.out.println("Toplu mail başarıyla gönderildi.");
        } catch (Exception e) {//Genel hata alıcı tanımı
            System.out.println("E-posta bilgileri listeye kaydedilmeden e-posta gönderilemez.");
        }
    }
}
