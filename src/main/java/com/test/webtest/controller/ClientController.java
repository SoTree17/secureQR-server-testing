package com.test.webtest.controller;

import com.google.gson.Gson;
import com.test.webtest.domain.QrDTO;
import com.test.webtest.service.QrService;
import crypto.SecureQrCryptoArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/*")
public class ClientController {
    //Service instance here
    @Autowired
    private QrService qrService;
    private SecureQrCryptoArray arr = new SecureQrCryptoArray();

    //Spring MVC here
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * 클라이언트 - 서버 동일 웹 어플리케이션에서 작동시 코드
     */
    @RequestMapping("/request")
    public String connectToServer(QrDTO qrDTO) throws Exception {
        log.info(qrDTO.toString());
        // authUrl, data, c_index, width, height 입력을 서버로 전달
        byte[] result = qrService.createSecureQRCode(arr, qrDTO);
        log.info(new String(result, StandardCharsets.UTF_8));
        // 응답 받은 byte[] 배열 이미지로 렌더링
        //qrService.createQRImage(result, "src//main//resources//imgs//Test5.png");
        qrService.createQRImage(result, "C:\\TestQR\\qrImg\\Test3.png");

        // 화면에 보여주기
        log.info("이미지 렌더링 완료");

        return "redirect:";
    }

    /**
     * (현재 적용) 클라이언트 - 서버 분리시
     * HTTP 요청 : 'org.apache.httpcomponents:httpclient:4.5.13' 이용
     */
    @PostMapping("/requestQR")
    public String request(QrDTO qrDTO, Model model) throws ClientProtocolException, IOException, URISyntaxException {

        CloseableHttpClient client = HttpClients.createDefault();

        // HTTP info
        String BaseUrl = qrDTO.getAuthUrl();
        String requestJson = new Gson().toJson(qrDTO);
        String resourcePath = "api/v1/secureQR/generator";
        String requestUrl = BaseUrl + resourcePath;

        //Set HTTP Header, Body
        HttpPost httpPost = new HttpPost(requestUrl);
        StringEntity entity = new StringEntity(requestJson);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        //응답 담기
        String responseBody = "";
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        responseBody = client.execute(httpPost, responseHandler);
        log.info(responseBody);

        // Gson 이용해서 JSON -> POJO 하려 했는데 잘 안되어 문자열 파싱
        String[] temp = responseBody.split(":");
        String str_ = temp[1].substring(1, temp[1].length() - 2);
        log.info(str_);


        byte[] binary = Base64.getDecoder().decode(str_);
        qrService.createQRImage(binary, "C:\\TestQR\\qrImg\\Server-Test.png");
        log.info("QR 이미지 생성 완료");

        client.close();

        model.addAttribute("url", "/qrImg/Server-Test.png");
        return "redirect:";
    }

    /* 동적으로 이미지 경로 처리*/
    /*@GetMapping("/{dir}/{fileName}")
    public ResponseEntity<Map<String, String>> getImgPath (@PathVariable("dir") String dir ,@PathVariable("fileName") String fileName){
        try{
            Map<String, String> map = new HashMap<>();
            map.put("url", "/"+dir+"/"+fileName+);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/
}
