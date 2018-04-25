package jobs;

import models.AdminInfo;
import models.Notification;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.jobs.Every;
import play.jobs.Job;
import play.libs.Mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by abzalsahitov@gmail.com  on 4/23/18.
 */
@Every("10s")
public class NotificationChecker extends Job {

    private static void sendEmail(Notification notification) throws EmailException, ExecutionException, InterruptedException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(AdminInfo.USERNAME, AdminInfo.PASSWORD));
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        email.setFrom(AdminInfo.EMAIL);
        email.setSubject("Уведомление о наступлении события \"" + notification.getTitle() + "\"");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
        email.setMsg(String.format("Ваше уведомление \'%s\' наступило.\nПроверьте календарь. \nДата: %s", notification.getTitle(), sdf.format(notification.getTime().getTime())));
        email.addTo(AdminInfo.EMAIL);
        email.send();
        Future<Boolean> send = Mail.send(email);
        send.get();
    }

    @Override
    public void doJob() throws Exception {
        Calendar currentTume = Calendar.getInstance();
        Notification.find("time<:currentTime and expired=false")
                .setParameter("currentTime", currentTume)
                .<Notification>fetch().stream().forEach(notification -> {
            System.out.println("sending");
            notification.setExpired(true);
            notification.save();
            try {
                sendEmail(notification);
            } catch (EmailException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
