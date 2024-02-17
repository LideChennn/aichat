package cn.edu.gdpu.aichat.controller.user;


import cn.edu.gdpu.aichat.dto.AddHistoryDto;
import cn.edu.gdpu.aichat.dto.UpdateHistoryDto;
import cn.edu.gdpu.aichat.entity.History;
import cn.edu.gdpu.aichat.result.Result;
import cn.edu.gdpu.aichat.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@Api(tags = "历史记录相关接口")
@Slf4j
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 获取所有的历史记录
     * 之后用户可以点击某个历史记录，携带历史记录的id过来后端，
     * 后端可以识别用户点击了哪一个历史记录，
     * 从而对消息列表List<ChatMessage>初始化
     */
    @GetMapping
    @ApiOperation("根据用户id获取所有的该用户历史记录")
    public Result<List<History>> getAllHistoryByUserId() {
        log.info("根据用户id获取所有的该用户历史记录");
        List<History> histories = historyService.getHistoryAndAiPromptByUserId();
        return Result.success(histories);
    }

    /**
     * 删除历史信息by historyId
     * @param historyId 历史Id
     * @return Result
     */
    @DeleteMapping("/{historyId}")
    @ApiOperation("根据历史记录id删除该历史记录")
    public Result deleteHistoryByHistoryId(
            @ApiParam(value = "聊天记录ID", required = true)
            @PathVariable Integer historyId) {
        log.info("根据历史记录id删除该历史记录");
        int i = historyService.deleteHistoryByHistoryId(historyId);
        return Result.success();
    }

    /**
     * 删除所有历史信息by userId
     * @return Result
     */
    @DeleteMapping("/all")
    @ApiOperation("通过用户id删除其所有历史信息")
    public Result deleteAllHistoriesByUserId(){
        log.info("通过用户id删除其所有历史信息");
        int i = historyService.deleteAllHistoriesByUserId();
        return Result.success();
    }

    /**
     * 修改historyName
     * @param updateHistoryDto historyName id
     * @return Result
     */
    @PutMapping
    @ApiOperation("根据历史记录ID修改历史记录的名称")
    public Result updateHistoryName(@RequestBody UpdateHistoryDto updateHistoryDto){
        log.info("修改历史记录的名称, {}", updateHistoryDto);
        int i = historyService.updateHistoryName(updateHistoryDto);
        return Result.success();
    }

    /**
     * 插入一条记录
     */
    @PostMapping
    @ApiOperation("插入一条历史记录,并且返回该历史记录的id")
    public Result<Integer> saveHistory(@RequestBody AddHistoryDto addHistoryDto) {
        log.info("插入一条历史记录,{}", addHistoryDto);
        int historyId = historyService.insertHistoryByObject(addHistoryDto);
        log.info("新增的历史记录id为,{}", historyId);
        return Result.success(historyId);
    }
}