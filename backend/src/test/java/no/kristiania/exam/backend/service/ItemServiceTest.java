package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.TestApplication;
import no.kristiania.exam.backend.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void getItemFromId(){
        Item item = itemService.getItem(1L);
        assertEquals("Bulbasaur", item.getName());
    }
}
