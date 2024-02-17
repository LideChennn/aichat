package cn.edu.gdpu.aichat.mapper;


import cn.edu.gdpu.aichat.entity.History;
import cn.edu.gdpu.aichat.entity.HistoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {

    /**
     * 分布查询，根据用户id获取历史记录及历史记录的prompt
     * @param userId 用户id
     * @return List<History>
     */
    List<History> getHistoryAndAiPromptByUserId(Integer userId);

    int countByExample(HistoryExample example);

    int deleteByExample(HistoryExample example);

    int insert(History record);

    @Options(useGeneratedKeys = true, keyProperty = "historyId", keyColumn = "history_id")
    int insertSelective(History record);

    List<History> selectByExample(HistoryExample example);

    int updateByExampleSelective(@Param("record") History record, @Param("example") HistoryExample example);

    int updateByExample(@Param("record") History record, @Param("example") HistoryExample example);
}