package no.kristiania.exam.backend.service;




import no.kristiania.exam.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private CopyService copyService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean createUser(String username, String name, String surname, String email, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        if ((em.find(User.class, username) != null) || (em.find(User.class, email) != null)){
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setHashedPassword(hashedPassword);
        user.setRoles(Collections.singleton("user"));
        //All new users start with 0 in in-game cash
        user.setInGameCash(0L);
        //But all users start with 3 lootboxes!
        user.setAvailableLootboxes(3L);
        user.setEnabled(true);

        em.persist(user);

        return true;
    }

    public User findUserByUsername(String username){
        User user = em.find(User.class, username);
        if(user == null){
            throw new IllegalStateException("No user with that username");
        }
        return user;
    }

    public List<User> getAllUsers(){
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u", User.class
        );

        return query.getResultList();

    }

    public void clearCache(){em.clear();}

    public Long getNumberOfLootboxes(String username){
        User user = em.find(User.class, username);
        if(user != null){
            return user.getAvailableLootboxes();
        }
        return null;
    }

    public Long getInGameCash(String username){
        User user = em.find(User.class, username);
        if(user != null){
            return user.getInGameCash();
        }
        return null;
    }

    // Add lootbox to existing user
    public void addLootbox(String username){
        User user = em.find(User.class, username);
        if(user == null){
            throw new IllegalStateException("No user with that username");
        }

        user.setAvailableLootboxes(user.getAvailableLootboxes() + 1L);
    }

    //Get in-game cash
    public void addInGameCash(long cashAmount, String username){
        User user = em.find(User.class, username);
        if(user == null){
            throw new IllegalStateException("No user with that username");
        }

        user.setInGameCash(user.getInGameCash() + cashAmount);
    }


    // Buy new lootbox function
    public void buyLootbox(String username){
        User user = em.find(User.class, username);

        if(user == null){
            throw new IllegalStateException("No user with that username");
        }
        // Price for lootbox is 100
        if(user.getInGameCash() < 100){
            throw new IllegalStateException("Cant afford lootbox");
        }

        user.setInGameCash(user.getInGameCash() - 100);
        user.setAvailableLootboxes(user.getAvailableLootboxes() + 1);
    }
}


















