package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.TestApplication;
import no.kristiania.exam.backend.entity.Copy;
import no.kristiania.exam.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CopyServiceTest {

    @Autowired
    private CopyService copyService;

    @Autowired UserService userService;


    @Test
    public void testGetAllCopiesForUser(){

        // Misty is given 3 pokemon in defaultdatainitializer
        List<Copy> copiesMisty = copyService.getAllCopiesOwnedByUser("Misty");
        assertEquals(3, copiesMisty.size());

        // Ash is given 2 pokemon in defaultdatainitializer
        List<Copy> copiesAsh = copyService.getAllCopiesOwnedByUser("Ash");
        assertEquals(2, copiesAsh.size());
    }

    @Test
    public void testRedeemLootbox(){

        User user = userService.findUserByUsername("Brock");
        String name = user.getUsername();
        long x = userService.getNumberOfLootboxes(name);

        // All users start with 3 boxes
        assertEquals(x, 3);

        List<Copy> listOfCopies = copyService.redeemLootBox(name);

        long y = userService.getNumberOfLootboxes(name);

        //Since we opened one box, user should have 2 left
        assertEquals(y, 2);
        assertEquals(3, listOfCopies.size());
    }

    @Test
    public void testGetIdsFromCopies(){
        List<Long> list = copyService.getIdsFromCopies("Misty");

        assertEquals(3, (long) list.get(0));
        assertEquals(4, (long) list.get(1));
        assertEquals(5, (long) list.get(2));

    }

    @Test
    public void testSellCopy(){
        //just to show me id of pokemon: id = 7
        //copyService.getIdsFromCopies("Tajiri");
        String username = "Tajiri";
        int nrOfCopies = copyService.getAllCopiesOwnedByUser(username).size();
        // Tajiri should only have 1 pokemon from data init
        assertEquals(1, nrOfCopies);
        // All users start with 0 in game game cash
        Long inGameCashAmount = userService.getInGameCash(username);
        assertEquals(0, (long)inGameCashAmount);

        copyService.sellCopy(7);
        //After selling Tajiri has 0 pokemon left
        nrOfCopies = copyService.getAllCopiesOwnedByUser(username).size();
        assertEquals(0, nrOfCopies);

        // Tajiri cash amount should increase when selling
        inGameCashAmount = userService.getInGameCash(username);
        // mew, wich is the pokemon we sold is worth 100 of our in game currency.
        assertEquals(100, (long)inGameCashAmount);

    }



}
