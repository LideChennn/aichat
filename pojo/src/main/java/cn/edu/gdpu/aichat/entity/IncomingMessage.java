package cn.edu.gdpu.aichat.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomingMessage implements Serializable {
    private String content;
    private Integer historyId;
    private Integer userId;
    private Integer promptId;
    private List<String> texts;//k个原文
}
