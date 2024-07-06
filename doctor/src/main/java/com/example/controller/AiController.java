package com.example.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.example.domain.vo.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AiController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/26 - 15:33
 * @Version: v1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/ai")
public class AiController {
    private final String role="现在有一个医生是帮别人体检的，可能会问你相关的医学问题:";

    @RequestMapping("/chat")
    @ResponseBody
    public Response aiChat(String question, HttpSession session) throws NoApiKeyException, InputRequiredException {

//        List<String> messages=(List<String>) session.getAttribute("history");
//        if(messages==null){
//            messages=new LinkedList<>();
//            session.setAttribute("history",messages);
//        }

//        messages.add("用户说:"+question);
//        StringBuilder sb=new StringBuilder();
//        for(int i=0;i<messages.size();i++){
//            sb.append(messages.get(i));
//            sb.append("\n");
//        }

        Generation gen = new Generation();
        QwenParam params = QwenParam.builder().model("qwen-turbo")
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .prompt(role+question)
                .seed(222)
                .topP(0.8)
                .resultFormat("message")
                .enableSearch(false)
                .maxTokens(100)
                .temperature((float)0.85)
                .repetitionPenalty((float)1.0)
                .build();
        GenerationResult result = gen.call(params);

        System.out.println(result);

//        Map<String, Object> respond = new HashMap<>();
//        respond.put("answer", result.getOutput().getChoices().get(0).getMessage().getContent());

        return new Response(200,"success",result.getOutput().getChoices().get(0).getMessage().getContent());
    }
}