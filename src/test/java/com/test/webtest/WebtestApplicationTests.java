package com.test.webtest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.webtest.domain.QrDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class WebtestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testParsingToJson(){
		QrDTO qrDTO = new QrDTO("https://junho.com/", "https://test.com", 0, 250, 250);
		String requestJson = new Gson().toJson(qrDTO, JsonObject.class);

	}

}
