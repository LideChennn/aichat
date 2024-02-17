package cn.edu.gdpu.aichat.config;

import com.theokanning.openai.completion.chat.ChatMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class GlobalConfig {
    @Bean
    public ConcurrentMap<Integer,List<ChatMessage>> chatMessagesMap() {
        return new ConcurrentHashMap<>();
    }
}
