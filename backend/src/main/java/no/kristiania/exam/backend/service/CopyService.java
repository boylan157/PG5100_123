package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.entity.Copy;
import no.kristiania.exam.backend.entity.Item;
import no.kristiania.exam.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
@Transactional
public class CopyService {

    @Autowired
    private EntityManager em;


    public Copy createCopy(Long pokedexId, String username){
        Item item = em.find(Item.class, pokedexId);
        User user = em.find(User.class, username);

        if(item == null){
            throw new IllegalStateException("Pokemon not found");
        }
        if(user == null){
            throw new IllegalStateException("No user with that username");
        }

        Copy copy = new Copy();
        copy.setOwnedBy(user);
        copy.setPokemon(item);

        em.persist(copy);

        return copy;
    }

    public List<Copy> getAllCopies(){
        TypedQuery<Copy> query = em.createQuery(
                "SELECT c FROM Copy c", Copy.class
        );

        return query.getResultList();
    }

    public List<Copy> getAllCopiesOwnedByUser(String username){
        TypedQuery<Copy> query = em.createQuery(
                "SELECT c FROM Copy c WHERE c.ownedBy.username=?1", Copy.class
        );
        query.setParameter(1, username);

        return query.getResultList();

    }

    public List<Long> getIdsFromCopies(String username){
        TypedQuery<Long> query = em.createQuery(
                "SELECT c.id from Copy c WHERE c.ownedBy.username=?1", Long.class
        );
        query.setParameter(1, username);


        System.out.println("Pokemon id: "+query.getResultList());
        return query.getResultList();

    }

    public void sellCopy(long copyId){
        Copy copy = em.find(Copy.class, copyId);

        if(copy == null){
            throw new IllegalStateException("Pokemon not found in collection");
        }

        User user = copy.getOwnedBy();
        Item item = copy.getPokemon();
        user.setInGameCash(user.getInGameCash() + item.getValue());

        em.remove(copy);
    }


    // Open lootbox function
    public List<Copy> redeemLootBox(String username){
        User user = em.find(User.class, username);
        if(user == null){
            throw new IllegalStateException("No user with that username");
        }
        if(user.getAvailableLootboxes() < 1){
            throw new IllegalStateException("You dont have any lootboxes!");
        }


        // removing a lootbox from user inventory
        user.setAvailableLootboxes(user.getAvailableLootboxes() - 1);

        String name = user.getUsername();

        List<Copy> allCopies = new ArrayList<>();

        // User gets 3 copies from lootboxes
        Copy copy;
        Copy copy2;
        Copy copy3;
        copy = createCopy(getRandomPokeDexNumber(), name);
        copy2 = createCopy(getRandomPokeDexNumber(), name);
        copy3 = createCopy(getRandomPokeDexNumber(), name);

        allCopies.add(copy);
        allCopies.add(copy2);
        allCopies.add(copy3);

        return allCopies;
    }
    // I pick 9 because i only have the first 9 pokemon sequentially.. I see now maybe i should've made pokemon cronologically with pokedexNr
    // I would of course fix this if i had more time.
    public long getRandomPokeDexNumber(){
        Random random = new Random();
        int n = random.nextInt(9);
        n += 1;

        return n;
    }


}






