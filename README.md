# Youtube 클론 코딩 프로젝트

전세계적으로 인기있는 웹서비스인 유튜브를 내가 직접 만들어보면 재밌을 것 같아서 시작하게 되었습니다.

# Github

https://github.com/kjb95/youtube

# 기술 스택

- HTML/CSS
- JavaScript
- Java
- SpringBoot
- MySQL
- JPA
- Docker

# 프로젝트 실행 방법

### 초기설정

1. 깃 레포지토리에서 클론받기
2. frontend 폴더에 .env 만들고 환경변수 REACT_APP_YOUTUBE_API=[유튜브 API Key] 추가
3. 도커로 MySQL 환경 구축
4. 스프링부트 실행
5. ./frontend 에서 npm run init 실행 (유튜브 API를 호출 하여 얻은 초기 데이터를 디비에 저장)

### 초기설정 이후

1. ./backend 에서 ./gradlew build 실행
2. 도커로 구축한 MySQL 환경 제거
3. 루트 폴더에서 docker-compose up --build

# 프로젝트 진행 단계

프로젝트는 아래의 순서로 점층적으로 발전 시켰습니다.

### 1. 바닐라 자바스크립트로 유튜브 페이지 만들기

HTML/CSS/JS를 학습하기 위해 오직 바닐라 자바스크립트만을 사용하여 웹 페이지를 만들었습니다.

- 고정 단위인 px 대신 유동 단위인 % 사용을 하고, media 쿼리를 사용하는 등 반응형 웹페이지로 만들었습니다.

https://user-images.githubusercontent.com/107471786/173803353-45ac07b7-22b1-4eec-b869-61b6f147e0e0.mov

- 레이어 팝업 형태로 공지사항을 구현했습니다. json으로 저장된 데이터를 ajax fetch API를 이용하여 불러왔고, 쿠키를 이용하여 오늘하루 보지 않기 기능도
  구현했습니다.

https://user-images.githubusercontent.com/107471786/173803372-55f24d65-fba5-44a1-ae81-c39d6112a72f.mov

- 플레이 리스트 랜덤재생/추가/제거 기능을 만들었습니다. 팝업 형태로 플레이리스트 추가 폼을 만들었고, 필요한 데이터는 localStorage에 저장했습니다. (영상은 추가
  기능만)

https://user-images.githubusercontent.com/107471786/173803477-3b4367f4-5908-4493-a19b-f941133a8d08.mov

### 2. 바닐라 자바스크립트를 리액트로 변환하기

리액트를 학습하기 위해 바닐라 자바스크립트 코드를 리액트 코드로 리액트 코드로 변환해 보았습니다.

- 그냥 CSS 대신 styled-components를 사용했습니다.
- 바닐라 자바스크립트로 직접 만들었던 팝업창을 react-modal 라이브러리를 이용하여 모달로 구현했습니다.
- json 파일에서 데이터를 읽고 보여주는것이 아니라, 유튜브 API를 호출하여 실시간으로 데이터를 가져왔습니다.

### 3. 스프링부트 사용하기

스프링부트를 학습하기 위해 프로젝트에 스프링부트로 서버를 구축했습니다.

- 데이터베이스는 데이터의 일관성이 보장되는 RDB인 MySQL로 선택했습니다.
- 5)프로젝트 개선 단계에서 다양한 스프링 기술들을 프로젝트에 적용 하는 중 입니다.

### 4. 개발환경 구축

도커를 활용하여 개발환경을 구축 했습니다.

- Dockerfile로 데이터베이스, 스프링부트, 리액트 환경을 각각의 이미지로 만들고, 이미지들을 빌드해서 생긴 3개의 컨테이너를 도커 컴포즈로 관리했습니다.
- AWS EC2에 배포도 해보았습니다.

### 5. 프로젝트 개선 (현재 진행형)

이 프로젝트를 통해 전반적인 웹 개발 과정을 경험해 보았습니다.

프론트엔드와 백엔드 둘 다 조금씩 경험해본 결과, 각각의 다른 매력을 느껴서 프론트를 잘 아는 백엔드 개발자가 되고 싶어졌습니다.

프로젝트 개선사항들은 다음 아래와 같습니다.

- JUnit5, AssertJ 를 이용하여 간단한 테스트 코드 작성
- @slf4j를 이용하여 로그 전략에 따른 로그 남기기
- 네비게이션바 생성(홈 화면 이동, react-i18next 라이브러리를 이용하여 다국어 처리)

https://user-images.githubusercontent.com/107471786/182569742-fa301ae5-57c7-4edf-9e31-e51a3ee57649.mov

- @Validated를 이용한 유효성 검증
- RESTful API 규칙 적용
- Java 에서는 JavaDoc, JavaScript 에서는 JSDoc 주석 사용
- 네이밍 컨벤션 규칙 준수
- 회원가입/로그인 기능 추가 (로그인 유지 구현 x, 구글 OAuth 구현 x)
- 필터, 인터셉터를 이용하여 공통 로직 처리(컨트롤러에 도달하는 모든 요청마다 로그 남기기)