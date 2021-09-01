package com.test.webtest.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.webtest.domain.ImageVO;
import com.test.webtest.domain.QrDTO;
import com.test.webtest.service.QrService;
import com.test.webtest.service.rest.RestInterface;
import crypto.SecureQrCryptoArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.generating.Generator;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
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
    private RestInterface restInterface;
    private SecureQrCryptoArray arr = new SecureQrCryptoArray();
    private static final String CONTENT_TYPE = "application/json"; // header

    //Spring MVC here
    @GetMapping("/")
    public String home(){
        return "home";
    }

    /**
     * 클라이언트 - 서버 동일 웹 어플리케이션에서 작동시 코드
     * @param qrDTO
     * @return
     * @throws Exception
     */
    @RequestMapping ("/request")
    public String connectToServer(QrDTO qrDTO) throws Exception{
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
     * 클라이언트 - 서버 분리시
     * Retrofit 2 이용
     */
    @PostMapping("/requestQR")
    public void request(QrDTO qrDTO) throws Exception{
        // HTTP Body
        String requestJson = new Gson().toJson(qrDTO);
        String resourcePath = "generator";
        log.info(requestJson);

        // 요청
        Call<ResponseBody> call = restInterface.generateQR(CONTENT_TYPE, resourcePath,requestJson);
        Response<ResponseBody> response= call.execute();
        byte[] result = response.body().bytes();
        // 저장 - home.html 에서 이미지 보여줌
        qrService.createQRImage(result, "C:\\TestQR\\qrImg\\Server-Test.png");

    }
}
