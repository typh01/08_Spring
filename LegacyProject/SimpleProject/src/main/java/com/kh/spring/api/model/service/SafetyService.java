package com.kh.spring.api.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SafetyService {

	public String requestHospitalApi() throws IOException {
		
		// 우리가 만들고 있는 자바 프로그램에서
		// API서버로 요청 보내고 응답받아서 콘솔에 출력하기
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.safetydata.go.kr/V2/api/DSSP-IF-10840");
		sb.append("?serviceKey=서비스키 입력하셈");
		
		// HttpURLConnerction 객체를 활용해서 API서버로 요청을 보내서 응답을 받아와야함
		// 1. java.net.URL 객체 생성 => 생성자 호출 시 인자값으로 요청할 URL을 전달
		URL url = new URL(sb.toString());
		// 2. URL객체를 이용해서 HttpURLConnection 객체 생성
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		
		connection.connect();
		
		// API 서버와 스트림 연결(BufferedReader)
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String responseData = br.readLine();
		
		log.info(responseData);
		
		br.close();
		is.close();
		connection.disconnect();
		return responseData;
	}

	public String requestMessage(int pageNo) {
		
		final String SERVICE_KEY = "서비스 키 입력하셈";
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.safetydata.go.kr/V2/api/DSSP-IF-00247");
		sb.append("?serviceKey=" + SERVICE_KEY);
		sb.append("&pageNo=" + pageNo);
		sb.append("&numOfRows=3");
		
		try {
			URI uri = new URI(sb.toString());			
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(uri, String.class);
			return response;
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getItems(String query) {
        String clientId = "AXQYWNUrEmFB7nHkejon"; //애플리케이션 클라이언트 아이디
        String clientSecret = "eYdOcH2FpL"; //애플리케이션 클라이언트 시크릿

        String text = null;
        try {
            text = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + text;    // JSON 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


//        System.out.println(responseBody);
        return responseBody;
    }


    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
	
	
}
