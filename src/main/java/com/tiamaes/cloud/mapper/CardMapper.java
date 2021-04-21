package com.tiamaes.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiamaes.cloud.beans.CardModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/15 14:00
 */
@Mapper
@Repository
public interface CardMapper extends BaseMapper<CardModel> {
}
