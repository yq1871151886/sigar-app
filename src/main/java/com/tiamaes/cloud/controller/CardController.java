package com.tiamaes.cloud.controller;

import com.tiamaes.cloud.beans.CardModel;
import com.tiamaes.cloud.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.KeySelector;
import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/15 14:05
 */
@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("selectCardAll")
    public List<CardModel> selectCardAll(){
        return cardService.selectCardAll();
    }


}
