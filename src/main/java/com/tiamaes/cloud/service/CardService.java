package com.tiamaes.cloud.service;

import com.tiamaes.cloud.beans.CardModel;

import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/15 14:01
 */
public interface CardService {

    List<CardModel> selectCardAll();

}
