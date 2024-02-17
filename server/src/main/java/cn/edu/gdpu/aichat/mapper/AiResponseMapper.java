package cn.edu.gdpu.aichat.mapper;


import cn.edu.gdpu.aichat.entity.AiResponse;
import cn.edu.gdpu.aichat.entity.AiResponseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AiResponseMapper {
    /**
     * 根据recordId获取对应的ai回复
     * @param chatRecordId id
     * @return AiResponse
     */
    AiResponse getAiResponseByRecordId(Integer chatRecordId);

    int countByExample(AiResponseExample example);

    int deleteByExample(AiResponseExample example);

    int insert(AiResponse record);

    int insertSelective(AiResponse record);

    List<AiResponse> selectByExampleWithBLOBs(AiResponseExample example);

    List<AiResponse> selectByExample(AiResponseExample example);

    int updateByExampleSelective(@Param("record") AiResponse record, @Param("example") AiResponseExample example);

    int updateByExampleWithBLOBs(@Param("record") AiResponse record, @Param("example") AiResponseExample example);

    int updateByExample(@Param("record") AiResponse record, @Param("example") AiResponseExample example);
}