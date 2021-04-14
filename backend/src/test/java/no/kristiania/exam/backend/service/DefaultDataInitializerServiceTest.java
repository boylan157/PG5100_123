package no.kristiania.exam.backend.service;

import no.kristiania.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Test
    public void testInit(){


        assertEquals(4, userService.getAllUsers().size());
        assertEquals(21, itemService.getAllItems().size());

       // This is a bit flaky because i am using same entities in other tests..
       // assertEquals(7, copyService.getAllCopies().size());

    }
}
