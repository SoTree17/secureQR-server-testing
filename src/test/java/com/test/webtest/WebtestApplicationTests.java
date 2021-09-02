package com.test.webtest;

import com.google.gson.Gson;
import com.test.webtest.domain.QrDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebtestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testParsingToJson() {
        QrDTO qrDTO = new QrDTO("http://junho.com/", "http://test.com", 0, 250, 250);
        String requestJson = new Gson().toJson(qrDTO);
        String testJson = "{\"authUrl\":\"http://junho.com/\","
                + "\"data\":\"http://test.com\","
                + "\"c_index\":0,"
                + "\"width\":250,"
                + "\"height\":250}";
        Assertions.assertEquals(testJson, requestJson); // actual 값 같은지 비교
    }

}
