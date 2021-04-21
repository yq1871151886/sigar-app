package com.tiamaes.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiamaes.cloud.beans.FzBigPOJO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/1/11 11:13
 */
@Mapper
@Repository
public interface ListMapper extends BaseMapper<FzBigPOJO> {

    @Select("select day,ifnull(sum(warn_count),0) warn_count from ad_count_show_zp_content where day = #{formats} and ana_type != '互联网诈骗' group by day")
    FzBigPOJO getDxTrends(String formats);


    @Select("select DATE_FORMAT(create_time,'%Y-%m-%d') create_time,ifnull(sum(count),0) count from ad_count_show_harmful_url where DATE_FORMAT(create_time,'%Y-%m-%d') = #{formats} group by DATE_FORMAT(create_time,'%Y-%m-%d')")
    FzBigPOJO getInternetTrends(String formats);

}
