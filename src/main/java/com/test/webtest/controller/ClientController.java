package com.test.webtest.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.webtest.domain.QrDTO;
import com.test.webtest.domain.QrImage;
import com.test.webtest.service.QrService;
import com.test.webtest.service.Restrofit.RestInterface;
import crypto.SecureQrCryptoArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/*")
public class ClientController {
    //Service instance here
    @Autowired
    private QrService qrService;
    //private RestInterface restInterface;
    private SecureQrCryptoArray arr = new SecureQrCryptoArray();
    //private static final String CONTENT_TYPE = "application/json"; // header

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
     * 클라이언트 - 서버 분리시
     * 'org.apache.httpcomponents:httpclient:4.5.13' 이용
     */
    @PostMapping("/requestQR")
    public String request(QrDTO qrDTO, Model model) throws ClientProtocolException, IOException, URISyntaxException {

        CloseableHttpClient client = HttpClients.createDefault();

        // HTTP info
        String BaseUrl = "http://ec2-3-37-43-9.ap-northeast-2.compute.amazonaws.com:8080/";
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

        // Gson 이용해서 JSON -> POJO 하려 했는데 잘 안되었음
        // 문자열 파싱함
        String[] temp = responseBody.split(":");
        String str_ = temp[1].substring(1, temp[1].length() - 2);
        log.info(str_);

        // 정규표현식쓰려했는데 이것도 잘 안되었음.. 아마 이미지 byte코드라서 그런듯
        /*
        String regex = "{\"binary\":(.*)";
        Pattern ptrn = Pattern.compile(regex);
        Matcher m = ptrn.matcher(responseBody);
        log.info(m.group(0));
        */

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

    // TODO Restrofit 사용 하려했는데 자꾸 Body가 NULL 이였음
    /**
     * // 백업
     * @PostMapping("/requestQR")
     *     public String request(QrDTO qrDTO) throws Exception {
     *         // HTTP Bod
     *         String BaseUrl = "http://ec2-3-37-43-9.ap-northeast-2.compute.amazonaws.com:8080/";
     *         String requestJson = new Gson().toJson(qrDTO);
     *         String resourcePath = "generator";
     *         log.info(requestJson);
     *
     *         // Retrofit 인스턴스 생성
     *         Retrofit retrofit = new Retrofit.Builder()
     *                 .baseUrl(BaseUrl)
     *                 .addConverterFactory(GsonConverterFactory.create())
     *                 .build();
     *         // 사용자 지정 인터페이스를 Retrofit 라이브러리에 의해 인스턴스로 자동 구현,
     *         RestInterface api = retrofit.create(RestInterface.class);
     *
     *         // 인터페이스 함수를 호출하여 Call 객체 생성
     *         Call call = api.generateQR(resourcePath, requestJson);
     *
     *         call.enqueue(new Callback() {
     *             @Override
     *             public void onResponse(Call call, Response response) {
     *                 log.info("연결 성공");
     *                 String test = new Gson().toJson(response.body());
     *                 log.info(test);
     *
     *                 //byte[] result = qrImage.getBinary();
     *                 //qrService.createQRImage(result, "C:\\TestQR\\qrImg\\Server-Test.png");
     *                 log.info("QR 이미지 생성 성공");
     *             }
     *
     *             @Override
     *             public void onFailure(Call call, Throwable t) {
     *                 log.info("연결 실패");
     *             }
     *         });
     *         return "redirect:";
     *     }
     */


    // TODO - 기존 Restrofit 코드 ( RestInterface
    /*
    @PostMapping("/test")
    public String test(QrDTO qrDTO) throws Exception{
        // HTTP Body
        String requestJson = new Gson().toJson(qrDTO);
        String resourcePath = "generator";
        log.info(requestJson);

        // 요청
        Call<ResponseBody> call = restInterface.generateQR(CONTENT_TYPE, resourcePath,requestJson);
        log.info(call.toString());

        Response<ResponseBody> response= call.execute();

        byte[] result = response.body().bytes();
        log.info(result.toString());
        // 저장 - home.html 에서 이미지 보여줌
        qrService.createQRImage(result, "C:\\TestQR\\qrImg\\Server-Test.png");
        log.info("QR 이미지 생성 완료");
        return "redirect:";
    }*/
}
