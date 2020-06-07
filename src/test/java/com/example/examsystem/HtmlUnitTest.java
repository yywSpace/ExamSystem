package com.example.examsystem;


import java.util.List;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HtmlUnitTest {
    @Test
    public void test() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage loginpage, mainpage=null;
        try {
            loginpage = webClient.getPage("http://47.96.152.133:8080/login");
            // 根据页面标题判别正确页面
            assertTrue(loginpage.asText().contains("上机考试系统"));


            List<HtmlForm> loginforms = loginpage.getForms();
            System.out.println(loginforms);
            for(HtmlForm f:loginforms) {

                    HtmlTextInput name = f.getInputByName("account");
                    HtmlPasswordInput pass = f.getInputByName("password");
                    HtmlButton submit = f.getButtonByName("登录");
                    name.setValueAttribute("teacher");
                    pass.setValueAttribute("111");
                    mainpage = submit.click();
                    break;

            }
            // 根据页面内容验证正确登录
            assertTrue(mainpage.asText().contains("teacher"));
//            // 通过XPath表达式找到“写新微博”的链接
//            HtmlAnchor newAnchor = (HtmlAnchor) mainpage
//                    .getByXPath("//div[2]/p[2]/a").get(0);
////			System.out.println(newAnchor.asXml());
//            mainpage = (HtmlPage) newAnchor.openLinkInNewWindow();
////			System.out.println(mainpage.asXml());
//            // 验证页面中包含“发送给：”文字，说明打开链接正确
//            assertTrue(mainpage.asText().contains("发送给："));
//            String weiboString = "HtmlUnit "+Math.random();
//            // 同前，查找表单，填写内容并提交
//            loginforms = mainpage.getForms();
//            for(HtmlForm f:loginforms) {
//                if(f.getActionAttribute().equals("write.servlet")) {
//                    HtmlTextArea message = f.getTextAreaByName("message");
//                    HtmlSubmitInput submit = f.getInputByValue("发布");
//                    message.setText(weiboString);
//                    mainpage = submit.click();
//                    break;
//                }
//            }
//            assertTrue(mainpage.asText().contains("发布过的微博"));
//            // 验证已发布页面中有随机生成的发布内容
//            assertTrue(mainpage.asText().contains(weiboString));
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
        }
    }
}

