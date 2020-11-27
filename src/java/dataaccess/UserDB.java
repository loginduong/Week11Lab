package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.User;
import services.AccountService;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public User getByUUID(String uuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Logger.getLogger(AccountService.class.getName()).log(Level.INFO, uuid);
        try {
            Query query = em.createNamedQuery("User.findByResetPasswordUuid", User.class);
            query.setParameter("resetPasswordUuid", uuid);
            List<User> users = query.getResultList();
            
                        Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Query Result: "+users.get(0).getResetPasswordUuid());

            return users.get(0);
        } catch (Exception e) {
            Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "getbyuuid failed", e);
            return null;
        } finally {
            em.close();
        }
        
    }
}
