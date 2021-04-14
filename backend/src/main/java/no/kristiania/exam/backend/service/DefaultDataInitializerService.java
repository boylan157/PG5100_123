package no.kristiania.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;



@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;


    @PostConstruct
    public void init() {


        //String user = "admin";


        // Making some users
       attempt(() -> {
            return userService.createUser("Ash", "Ash", "Ketchum", "ash@gmail.com" ,"123");
        });
        attempt(() -> {
            return userService.createUser("Misty", "Misty", "Misty", "misty@gmail.com","123");
        });
        attempt(() -> {
            return userService.createUser("Brock", "Brock", "Lesnar","brock@gmail.com", "123");
        });
        attempt(() -> {
            return userService.createUser("Tajiri", "Satoshi", "Tajiri","tajiri@gmail.com", "123");
        });


        // Making some Pokemon/Items
        attempt(() -> {
            return itemService.createItem(1L, "Bulbasaur", "Grass", "One of the starter pokemon in first generation", 15L);
        });
        attempt(() -> {
            return itemService.createItem(2L, "Ivysaur", "Grass", "Evolved version of Bulbasaur", 20L);
        });
        attempt(() -> {
            return itemService.createItem(3L, "Venusaur", "Grass", "Bulbasaur's final form! this pokemon dont mess around", 30L);
        });
        attempt(() -> {
            return itemService.createItem(4L, "Charmander", "Fire", "One of the starter pokemon in first generation", 15L);
        });
        attempt(() -> {
            return itemService.createItem(5L, "Charmeleon", "Fire", "Evolved version of Charmander", 20L);
        });
        attempt(() -> {
            return itemService.createItem(6L, "Charizard", "Fire", "Charmander's final form! This pokemon can burn everything!", 30L);
        });
        attempt(() -> {
            return itemService.createItem(7L, "Squirtle", "Water", "One of the starter pokemon in first generation", 15L);
        });
        attempt(() -> {
            return itemService.createItem(8L, "Wartortle", "Water", "Evolved version of Squirtle", 20L);
        });
        attempt(() -> {
            return itemService.createItem(9L, "Blastoise", "Water", "Squirtle's final form! This pokemon is badass", 15L);
        });
        attempt(() -> {
            return itemService.createItem(23L, "Ekans", "Poison", "Ekans name backwards is snake!", 15L);
        });
        attempt(() -> {
            return itemService.createItem(24L, "Arbok", "Poison", "Arbok's name backwards is kobra!", 25L);
        });
        attempt(() -> {
            return itemService.createItem(25L, "Pikachu", "Electric", "Most iconic Pokemon", 15L);
        });
        attempt(() -> {
            return itemService.createItem(43L, "Oddish", "Grass", "'I'm a blueberry!'", 15L);
        });
        attempt(() -> {
            return itemService.createItem(63L, "Abra", "Psychic", "'Watch out! I will teleport!'", 15L);
        });
        attempt(() -> {
            return itemService.createItem(64L, "Kadabra", "Psychic", "'Look at my spoon and DIE!'", 20L);
        });
        attempt(() -> {
            return itemService.createItem(65L, "Alakazam", "Psychic", "'I have two spoon now!'", 30L);
        });
        attempt(() -> {
            return itemService.createItem(143L, "Snorlax", "Normal", "Will sleep his opponents to death", 25L);
        });
        attempt(() -> {
            return itemService.createItem(129L, "Magikarp", "Water", "Might not be dangerous now, but will turn into a beast once it evolves", 15L);
        });
        attempt(() -> {
            return itemService.createItem(132L, "Ditto", "Normal", "Will be whatever you want it to be", 20L);
        });
        attempt(() -> {
            return itemService.createItem(150L, "Mewtwo", "Psychic", "Cloned version of the mythical pokemon Mew", 50L);
        });
        attempt(() -> {
            return itemService.createItem(151L, "Mew", "Psychic", "Strongest Pokemon in the game", 100L);
        });


        // Adding some pokemon to users collections
       copyService.createCopy(3L, "Ash");
       copyService.createCopy(143L, "Ash");

       copyService.createCopy(7L, "Misty");
       copyService.createCopy(8L, "Misty");
       copyService.createCopy(9L, "Misty");

       copyService.createCopy(63L, "Brock");

       copyService.createCopy(151L, "Tajiri");




    }

    // This function is taken from
    // https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java

    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }
}
