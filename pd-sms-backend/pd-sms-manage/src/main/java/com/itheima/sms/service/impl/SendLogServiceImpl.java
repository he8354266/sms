package com.itheima.sms.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2110:58
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:58
 * @updateDate 2022/2/21 10:58     
 * @version 1.0
 **/

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sms.entity.SendLogEntity;
import com.itheima.sms.mapper.SendLogMapper;
import com.itheima.sms.service.SendLogService;
import com.itheima.sms.vo.MarketingStatisticsCountVO;
import com.itheima.sms.vo.SendLogPageVO;
import com.itheima.sms.vo.SendLogVO;
import com.itheima.sms.vo.StatisticsCountVO;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 日志表
 *
 * @author 传智播客
 *
 */
@Service
public class SendLogServiceImpl extends ServiceImpl<SendLogMapper, SendLogEntity> implements SendLogService {
    @Override
    public Page<SendLogVO> pageLog(Page<SendLogVO> page, Map<String, Object> params) {
        IPage<SendLogVO> sendLogVOPage = this.baseMapper.selectLogPage(page, params);
        page.setRecords(sendLogVOPage.getRecords());
        return page;
    }

    @Override
    public List<StatisticsCountVO> trend(Map params) {
        return this.baseMapper.trend(params);
    }

    @Override
    public Page<StatisticsCountVO> countPage(Page<StatisticsCountVO> page, Map<String, Object> params) {
        IPage<StatisticsCountVO> countPage = this.baseMapper.countPage(page, params);
        countPage.getRecords().stream().map(item -> {
            item.setFail(item.getCount() - item.getSuccess());
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
            //可以设置精确几位小数
            df.setMaximumFractionDigits(2);
            //模式 例如四舍五入
            df.setRoundingMode(RoundingMode.HALF_UP);
            double accuracy_num = (double) item.getSuccess() / (double) item.getCount() * 100;
            item.setSuccessRate(df.format(accuracy_num));
            return item;
        }).collect(Collectors.toList());
        page.setRecords(countPage.getRecords());
        return page;
    }

    @Override
    public List<Map> countForConfig(Map params) {
        return null;
    }

    @Override
    public List<Map> rateForConfig(Map params) {
        return null;
    }

    @Override
    public MarketingStatisticsCountVO getMarketingCountByBusinessId(String id) {
        return null;
    }

    @Override
    public Page<SendLogPageVO> sendLogPage(Page<SendLogPageVO> page, SendLogPageVO sendLogPageVO) {
        return null;
    }
}
