package com.example.controller;

import com.example.domain.pojo.Email;
import com.example.domain.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: EmailController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/26 - 16:04
 * @Version: v1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender sender;

    @PostMapping("/sendEmail")
    public Response sendEmail(@RequestBody Email email){
        System.out.println("测试开始");
        SimpleMailMessage m=new SimpleMailMessage();
        m.setFrom("1914575145@qq.com");
        m.setTo(email.getAddress());
        m.setSubject(email.getTitle());
        System.out.println(email.getTitle());
        m.setText(email.getContent());
        sender.send(m);
        System.out.println("邮件发送完毕");
        return new Response(200,"邮件发送成功",null);
    }
}
