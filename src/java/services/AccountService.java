package services;

import dataaccess.DBUtil;
import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import models.User;

public class AccountService {

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void resetPassword(String email, String path, String url) {
        try {
            String uuid = UUID.randomUUID().toString();
            UserDB userDB = new UserDB();
            String urlComplete = url + "?uuid=" + uuid;

            User user = userDB.get(email);
            String to = user.getEmail();
            String template = path + "/emailtemplates/resetpassword.html";
            String subject = "Notes App Reset Password";

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", urlComplete);

            GmailService.sendMail(to, subject, template, tags);

            user.setResetPasswordUuid(uuid);
            this.update(user);

        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "AccountService resetPassword had an OOPSIE", ex);
        }

    }

    public void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(user);
            et.commit();
        } catch (Exception e) {
            et.rollback();
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "AccountService update could not update", e);

        } finally {
            em.close();
        }
    }
 
    public boolean changePassword(String uuid, String password) {
     EntityManager em = DBUtil.getEmFactory().createEntityManager();
     EntityTransaction et = em.getTransaction();
        
        try {
            UserDB userDB = new UserDB();
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            
            et.begin();
            
            em.merge(user);
            
            et.commit();
            
            return true;
        } catch (Exception e) {
                        Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "Why did this happen", e);

            et.rollback();
            return false;
        } finally {
            em.close();
        }
            
        
    }
    
}
