# secureQR-web-client Test Application


### 1. Description 
- 암호화된 QR 코드를 생성하는 기능을 하는 클라이언트 웹 어플리케이션 역할을 담당함.

### 2. 개발 환경
 &nbsp;개발 언어 : ![JAVA11](http://img.shields.io/badge/-Java11-006cb7?style=flat&logo=Java) & ![JAVA](http://img.shields.io/badge/-Javascript-006cb7?style=flat&logo=Javascript)<br/>
  &nbsp;개발 환경 :  ![Springboot](http://img.shields.io/badge/-Springboot2.5.4-000000?style=flat&logo=springboot) & ![Springboot](http://img.shields.io/badge/-Gradle7.1.1-006cb7?style=flat&logo=gradle)

### 3. 실행 방법




### 4. 전체적인 설계흐름
- 먼저 해당 레파지토리는 Sotree17/secure-module 을 라이브러리화 한 jar파일을 사용하여, 
- Sotree17/secure-server 을 REST API 서버 역할을 두며 해당 서버에 요청함으로써 암호화된 QR 이미지를 사용자에게 보여주는 흐름을 갖음. 




    #### 먼저 REST API 서버의 /generator 에 대한 POST 요청에 대한 응답으로 암호화된 SecureQR 이미지를 얻을 수 있음.
![슬라이드1](https://user-images.githubusercontent.com/54317409/132015823-f53589db-a641-4cbc-9f13-ae636d502e62.PNG)


   #### 그리고 본 레파지토리의 client 어플리케이션에선 다음의 설계 흐름을 통해 사용자에게 SecureQR 이미지를 보여줌 
![공소 레포트 준비](https://user-images.githubusercontent.com/54317409/132016161-6ae74c63-287e-4a60-b0e5-c03af4ebd88a.png)



### 5. QR 코드 생성 페이지
![home](https://user-images.githubusercontent.com/48395704/132034582-11c36a78-79ae-40e9-8f3d-e806115c6237.gif)


