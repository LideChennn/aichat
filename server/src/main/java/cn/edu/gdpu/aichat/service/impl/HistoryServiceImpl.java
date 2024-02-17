package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.context.BaseContext;
import cn.edu.gdpu.aichat.dto.AddHistoryDto;
import cn.edu.gdpu.aichat.dto.UpdateHistoryDto;
import cn.edu.gdpu.aichat.mapper.HistoryMapper;
import cn.edu.gdpu.aichat.entity.History;
import cn.edu.gdpu.aichat.entity.HistoryExample;
import cn.edu.gdpu.aichat.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<History> getHistoryAndAiPromptByUserId() {
        Integer userId = BaseContext.getCurrentId();
        return historyMapper.getHistoryAndAiPromptByUserId(userId);
    }

    @Override
    public History getHistoryByHistoryId(Integer historyId) {
        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        criteria.andHistoryIdEqualTo(historyId);
        return historyMapper.selectByExample(historyExample).get(0);
    }

    @Override
    public List<History> getAllByUserId(Integer userId) {
        HistoryExample historyExample = new HistoryExample();
        //设计条件为等于userId
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return historyMapper.selectByExample(historyExample);
    }

    @Override
    public int insertHistoryByObject(AddHistoryDto addHistoryDto) {
        History history = History.builder()
                .historyName(addHistoryDto.getHistoryName())
                .promptId(addHistoryDto.getPromptId())
                .userId(BaseContext.getCurrentId())
                .userAiPrompt(addHistoryDto.getUserAiPrompt())
                .build();
        historyMapper.insertSelective(history);
        return history.getHistoryId();
    }

    @Override
    public int deleteHistoryByHistoryId(Integer historyId) {
        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        criteria.andHistoryIdEqualTo(historyId);
        return historyMapper.deleteByExample(historyExample);
    }

    @Override
    public int deleteAllHistoriesByUserId() {
        Integer userId = BaseContext.getCurrentId();

        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return historyMapper.deleteByExample(historyExample);
    }

    @Override
    public int updateHistoryName(UpdateHistoryDto updateHistoryDto) {
        History history = History.builder()
                .historyName(updateHistoryDto.getHistoryName())
                .historyId(updateHistoryDto.getHistoryId())
                .build();

        HistoryExample historyExample = new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        criteria.andHistoryIdEqualTo(history.getHistoryId());
        return historyMapper.updateByExampleSelective(history, historyExample);
    }

}
