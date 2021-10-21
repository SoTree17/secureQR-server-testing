# secureQR-web-client Test Application


### 1. Description 
- 앞서 개발한 `secureQR-module` 라이브러리를 적용한 웹 클라이언트 적용 예시
- 암호화된 QR 코드를 생성하고 발급하는 역할을 수행하는 예시

<br/>

### 2. 개발 환경
 &nbsp;개발 언어 : ![JAVA11](http://img.shields.io/badge/-Java11-006cb7?style=flat&logo=Java) & ![JAVA](http://img.shields.io/badge/-Javascript-006cb7?style=flat&logo=Javascript)<br/>
  &nbsp;개발 환경 :  ![Springboot](http://img.shields.io/badge/-Springboot2.5.4-000000?style=flat&logo=springboot) & ![Springboot](http://img.shields.io/badge/-Gradle7.1.1-006cb7?style=flat&logo=gradle)


<br/>

### 3. 실행 방법

- 로컬에서 실행하는 경우

  1. 동봉한 jar 파일을 다운로드 받음.

  2. cmd 나 터미널을 실행

  3. 해당 jar파일 디렉토리로 이동

  4. 다음과 명령어를 입력 

     ``` sh
     java -jar secureQR-0.0.1-SNAPSHOT.jar
     ```

  5. 웹 브라우저에 localhost:8080/ 을 입력

  6. 아래의 화면처럼 정보들을 입력
     
     ![home](https://user-images.githubusercontent.com/48395704/132034582-11c36a78-79ae-40e9-8f3d-e806115c6237.gif)







### 4. 전체적인 설계흐름
- 먼저 해당 레파지토리는 Sotree17/secure-module 을 라이브러리화 한 jar파일을 사용하여, 
- Sotree17/secure-server 을 REST API 서버 역할을 두며 해당 서버에 요청함으로써 암호화된 QR 이미지를 사용자에게 보여주는 흐름을 갖음. 




    #### 먼저 REST API 서버의 /generator 에 대한 POST 요청에 대한 응답으로 암호화된 SecureQR 이미지를 얻을 수 있음.
![슬라이드1](https://user-images.githubusercontent.com/54317409/132015823-f53589db-a641-4cbc-9f13-ae636d502e62.PNG)


   #### 그리고 본 레파지토리의 client 어플리케이션에선 다음의 설계 흐름을 통해 사용자에게 SecureQR 이미지를 보여줌 
![공소 레포트 준비](https://user-images.githubusercontent.com/54317409/132016161-6ae74c63-287e-4a60-b0e5-c03af4ebd88a.png)





