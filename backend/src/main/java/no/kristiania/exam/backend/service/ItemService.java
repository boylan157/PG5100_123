package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

@Service
@Transactional
public class ItemService {


    @Autowired
    private EntityManager em;


    public Long createItem(
            Long pokedexId,
            String name,
            String type,
            String description,
            Long value
    ) {
        Item item = new Item();

        item.setPokeDexId(pokedexId);
        item.setName(name);
        item.setType(type);
        item.setDescription(description);
        item.setValue(value);

        em.persist(item);

        return pokedexId;
    }

    public List<Item> getAllItems(){
        TypedQuery<Item> query = em.createQuery(
                "SELECT i from Item i ORDER BY i.pokeDexId ASC", Item.class
        );
        List<Item> allItems = query.getResultList();

        return  allItems;
    }

    public Item getItem(Long pokedexId){
        Item item = em.find(Item.class, pokedexId);

        if(item == null){
            throw new IllegalStateException("Pokemon not found in database");
        }

        return item;
    }


}
