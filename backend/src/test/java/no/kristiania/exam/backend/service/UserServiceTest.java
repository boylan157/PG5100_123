package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.TestApplication;
import no.kristiania.exam.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest{

    @Autowired
    private UserService userService;


    @Test
    public void testFindUserByUsername(){

        userService.createUser("test", "test", "test", "test@mail.com", "123");
        User user = userService.findUserByUsername("test");
        assertEquals("test@mail.com", user.getEmail() );
        assertNotEquals("test2@mail.com", user.getEmail());
    }

    @Test
    public void addLootboxToUser(){


        User user = userService.findUserByUsername("Ash");

        String name = user.getUsername();

        long lootboxes = userService.getNumberOfLootboxes(name);

        userService.addLootbox("Ash");

        userService.clearCache();

        long newLootboxes = userService.getNumberOfLootboxes("Ash");

        assertEquals(lootboxes + 1, newLootboxes);
    }




    @Test
    public void testAddInGameCash(){


        String name = "test3";
        userService.createUser(name, "test", "test", "test3@mail.com", "123");

        long cash = userService.getInGameCash(name);
        assertEquals(0, cash);

        userService.addInGameCash(200, name);
        cash = userService.getInGameCash(name);
        assertEquals(200, cash);
    }

    @Test
    public void testBuyLootbox(){


        User user = userService.findUserByUsername("Ash");
        String name = user.getUsername();

        userService.addInGameCash(200, name);

        userService.buyLootbox(name);
        long cashleft = userService.getInGameCash(name);
        long lootboxesLeft = userService.getNumberOfLootboxes(name);

        assertEquals(100, cashleft);
        // All users start with 3 lootboxes
        assertEquals(4, lootboxesLeft);
    }





}
