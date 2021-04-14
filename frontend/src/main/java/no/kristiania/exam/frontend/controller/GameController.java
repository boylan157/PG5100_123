package no.kristiania.exam.frontend.controller;


import no.kristiania.exam.backend.entity.Copy;
import no.kristiania.exam.backend.entity.Item;
import no.kristiania.exam.backend.service.CopyService;
import no.kristiania.exam.backend.service.ItemService;
import no.kristiania.exam.backend.service.UserService;
import org.openqa.selenium.Alert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class GameController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private ItemService itemService;


    @Autowired
    private UserInfoController infoController;

    public Long getLootBoxAmount() {
        return userService.getNumberOfLootboxes(infoController.getUserName());
    }

    public Long getInGameCashAmount(){
        return userService.getInGameCash(infoController.getUserName());
    }

    public List<Item> getAllPokemon() {
        return itemService.getAllItems().stream().collect(Collectors.toList());
    }

    public List<Copy> getCollection() {
        return copyService.getAllCopiesOwnedByUser(infoController.getUserName()).stream().collect(Collectors.toList());
    }

    public void buyLootbox() {
        if(infoController.getUser().getInGameCash() >= 100) {
            userService.buyLootbox(infoController.getUserName());
        }
    }

    public void getMoreMoney(){
            infoController.getUser().setInGameCash(infoController.getUser().getInGameCash() + 100L);
    }

    public void openLootBox(){
        if(infoController.getUser().getAvailableLootboxes() >= 1)
        copyService.redeemLootBox(infoController.getUserName());
    }

    public void sellPokemon(Long copyId){
        copyService.sellCopy(copyId);
    }


}
