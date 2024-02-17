package cn.edu.gdpu.aichat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

import static com.theokanning.openai.service.OpenAiService.*;

/**
 * 把openaiService变成bean
 */
@Configuration
public class OpenAiConfig {
    //api key
    @Value("${openai.key}")
    private String API_KEY;
    @Value("${openai.hostname}")
    private String proxyHostName;
    @Value("${openai.port}")
    private int proxyPort;
    @Value("${openai.timeout}")
    private int timeout;

    @Bean
    public OpenAiService openAiService(){
        //配置api keys 并获取service
        ObjectMapper mapper = defaultObjectMapper();
        //代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, proxyPort));
        //连接
        OkHttpClient client = defaultClient(API_KEY, Duration.ofSeconds(timeout))
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        //获取openAiService
        return new OpenAiService(api);
    }
}
