package com.test.webtest.controller;

import com.test.webtest.domain.QrDTO;
import com.test.webtest.domain.QrImage;
import com.test.webtest.service.QrService;
import crypto.SecureQrCryptoArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qr.authentication.AuthQR;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/secureQR")
@RequiredArgsConstructor
public class ServerController {

    @Autowired
    private QrService qrService;
    private SecureQrCryptoArray arr;

    @PostMapping(value="/createQR")
    public byte[] createQR(@RequestBody QrDTO qrDTO) throws IOException {
        return qrService.createSecureQRCode(arr, qrDTO);
    }

    @PostMapping("/authQR")
    public String authQrAndResponse(@RequestBody Map<String, String> param) throws Exception {
        AuthQR authQR = new AuthQR(arr);
        int c_index = Integer.parseInt(param.get("c_index"));
        int d_index = Integer.parseInt(param.get("d_index"));
        String data = param.get("data");

        return authQR.getOriginData(data, c_index,d_index);
    }

    @PostMapping("/generator")
    public ResponseEntity<QrImage> generateQR(@RequestBody QrDTO qrDTO) throws Exception {
        log.info("클라이언트로부터 secureQR 이미지 생성 요청 받음");
        QrImage result = new QrImage();
        result.setBinary(qrService.createSecureQRCode(arr, qrDTO));
        //HttpHeaders header = new HttpHeaders();
        //header.add("Content-type", "image/png");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
