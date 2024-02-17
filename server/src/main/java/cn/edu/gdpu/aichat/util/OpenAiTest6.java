package cn.edu.gdpu.aichat.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.theokanning.openai.service.OpenAiService.*;

public class OpenAiTest6 {

    private static OpenAiService openAiService;
    //消息队列
    private static List<ChatMessage> list = new ArrayList<>();
    //api key
    private static String API_KEY = "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";
    //系统角色
    private static String systemRole = "You are a helpful assistant.";

    private static int limit = 1000;

    static {
        //配置api keys 并获取service
        ObjectMapper mapper = defaultObjectMapper();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",10809));
        OkHttpClient client = defaultClient(API_KEY, Duration.ofSeconds(60))
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        //获取openAiService
        openAiService = new OpenAiService(api);
//        //添加chatgpt要扮演的角色
        addList("system", systemRole);
    }

    //向服务器发送信息
    public static String getResponse(String msg){
        //封装用户信息
        addList("user", msg);

        //封装http request的请求体
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(list)
                .build();
        // chatCompletion 对象就是chatGPT响应的数据了
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(completionRequest);

        //解封数据
        List<ChatCompletionChoice> choices = chatCompletion.getChoices();
        ChatMessage message = choices.get(0).getMessage();
        String content = message.getContent();

        //记录chatgpt回复的聊天信息
        addList("assistant", content);

        return content;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String s = in.nextLine();
            String response = getResponse(s);
            System.out.println(response);
        }
    }

    private static void addList(String role, String content){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole(role);
        chatMessage.setContent(content);
        list.add(chatMessage);
    }
}