package com.test.webtest.controller;

import com.test.webtest.domain.ImageVO;
import com.test.webtest.domain.QrDTO;
import com.test.webtest.service.QrService;
import crypto.SecureQrCryptoArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.generating.Generator;

import java.io.File;
import java.nio.charset.StandardCharsets;

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
    public String home(){
        return "home";
    }

    @RequestMapping ("/request")
    public String connectToServer(QrDTO qrDTO) throws Exception{
        log.info(qrDTO.toString());
        // authUrl, data, index, width, height 입력을 서버로 전달
        byte[] result = qrService.createSecureQRCode(arr, qrDTO);
        log.info(new String(result, StandardCharsets.UTF_8));
        // 응답 받은 byte[] 배열 이미지로 렌더링
        //qrService.createQRImage(result, "src//main//resources//imgs//Test5.png");
        qrService.createQRImage(result, "C:\\TestQR\\qrImg\\Test3.png");

        // 화면에 보여주기
        log.info("이미지 렌더링 완료");

        return "redirect:";
    }


}
