## 관련 프로젝트
#### 유튜브 웹페이지 만들기1 (Vanila JavaScript)
블로그 : https://velog.io/@kjb95/%EC%9C%A0%ED%8A%9C%EB%B8%8C-%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0

깃허브 : https://github.com/kjb95/youtube
#### 유튜브 웹페이지 만들기2 (React)
블로그 : https://velog.io/@kjb95/%EC%9C%A0%ED%8A%9C%EB%B8%8C-%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B02

깃허브 : https://github.com/kjb95/youtube_react

## 기술스택
- HTML
- CSS
- JavaScript
- React
- Spring Boot
- MySQL
- JPA
- Docker

## 프로젝트 개선사항
### 1. Spring Boot + JPA 사용
프론트단(localstorage)에서 데이터를 저장하는 방식 대신 스프링부트와 JPA를 이용하여 데이터베이스에 데이터를 저장
### 2. 유튜브 API를 호출해서 얻은 데이터 파싱
데이터베이스에는 원본 데이터를 저장하고, 데이터를 파싱한 후에 프론트 단으로 전달
#### 개선 전
![](https://velog.velcdn.com/images/kjb95/post/859a63b6-339d-4107-86c8-1cd4a8cd8a77/image.png)
#### 개선 후
![](https://velog.velcdn.com/images/kjb95/post/4197b031-d9cd-4945-9dd1-e2b3058e2d02/image.png)
### 3. 도커로 개발 환경 구축
도커 컴포즈, 도커 파일을 이용하여 리액트, 스프링부트, DB(MySQL) 개발 환경 구축

https://user-images.githubusercontent.com/107471786/180921548-ae770889-701f-4dcb-b76b-da30a1a2441a.mov

(컴포즈 업 명령어를 이미 백그라운드로 실행시켜 둔 상태)

## 코드
https://github.com/kjb95/youtube_react_spring