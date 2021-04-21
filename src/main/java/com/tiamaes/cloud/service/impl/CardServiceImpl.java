package com.tiamaes.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiamaes.cloud.beans.CardModel;
import com.tiamaes.cloud.mapper.CardMapper;
import com.tiamaes.cloud.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/15 14:02
 */
@Service
@Slf4j
@Async
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public List<CardModel> selectCardAll() {
        System.out.println(Thread.currentThread().getName());
        QueryWrapper<CardModel> wrapper = new QueryWrapper<CardModel>();
        return cardMapper.selectList(wrapper);
    }

    public static void main(String[] args) {
        //Double.valueOf()

    }
    public static <T> T gett(List<T> z){



        return z.get(0);
    }
}
