# SkBlogSearch

## 블로그 검색
### 기본 정보

**GET /v1/search/blog HTTP/1.1**

**Test Host http://localhost:8080**

* 다음 블로그 서비스에서 질의어로 게시물을 검색합니다. 원하는 검색어와 함께 결과 형식 파라미터를 선택적으로 추가할 수 있습니다. 응답 바디는 staus, reason, result로 구성된 JSON 객체입니다.

* 다음 블로그 서비스 API에 문제가 있을 경우 네이버 블로그 서비스에서 질의어로 게시물을 검색합니다.

### Request
#### Parameter

Name | Type |	Description | Required 
--- | --- | --- | ---
query	| String	| 검색을 원하는 질의어| O 
sort	| String	|결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy	| X
page	| Integer	| 결과 페이지 번호, 1~50 사이의 값, 기본 값 1 |	X
size	| Integer	| 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10	| X


### Response
#### status

* String Type, "OK", "NOK" 값 중 하나의 값을 보여줌

#### reason

* String Type, status의 값이 "NOK" 일 경우 원인을 보여줌.

#### result
Name | Type | Description 
--- | --- | --- 
source | String | 검색 결과의 출처를 나타내 준다. "kakao", "naver" 값 중 하나의 값을 보여줌. "kakao"를 우선순위로 검색함
body | KaKaoBodyType \| NaverBodyType | "kakao", "naver" 에서 검색한 결과 수행 값을 보여줌

#### KakaoBodyType
Name | Type | Description
--- | --- | ---
meta | MetaType | 검색 결과에 대한 meta 정보
document | DocumentType Array | 검색 결과에 대한 문서 

#### MetaType
Name | Type | Description
--- | --- | ---
total_count	| Integer	| 검색된 문서 수
pageable_count |	Integer	| total_count 중 노출 가능 문서 수
is_end	| Boolean	| 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음

#### DocumentType
Name	| Type	| Description
--- | --- | ---
title	| String	| 블로그 글 제목
contents |	String	| 블로그 글 요약
url	| String	| 블로그 글 URL
blogname	| String	| 블로그의 이름
thumbnail	| String	| 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음
datetime	| String(DateTime)	| 블로그 글 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]


#### NaverBodyType
Name | Type | Description
--- | --- | ---
lastBuildDate | String(DateTime) | 검색 결과를 생성한 시간
total | Integer | 총 검색 결과 개수
start | Integer | 검색 시작 위치
display | Integer | 한 번에 표시할 검색 결과 개수
items | ItemType Array | 개별 검색 결과. JSON 형식의 결괏값에서는 ItemType 속성의 JSON 배열로 개별 검색 결과를 반환합니다.

#### ItemType
Name | Type | Description
--- | --- | ---
title | String | 블로그 포스트의 제목. 제목에서 검색어와 일치하는 부분은 \<b\> 태그로 감싸져 있습니다.
link | String | 블로그 포스트의 URL
description | String | 블로그 포스트의 내용을 요약한 패시지 정보. 패시지 정보에서 검색어와 일치하는 부분은 \<b\> 태그로 감싸져 있습니다
bloggername | String | 블로그 포스트가 있는 블로그의 이름
bloggerlink | String | 블로그 포스트가 있는 블로그의 주소
postdate | String(DateTime) | 블로그 포스트가 작성된 날짜



Sample
Request

```bash
curl -v -X GET "http://localhost:8080/v1/search/blog?query=test"
```

Response

```json
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 18 Sep 2022 09:50:43 GMT
<
{"status":"OK","reason":null,"result":{"source":"kakao","body":{"meta":{"total_count":1862013,"pageable_count":800,"is_end":false},"documents":[{"title":"[SpringBoot] <b>test</b> 코드 작성하기, Lombok","contents":"@ExtendWith SpringRunner -&gt; SpringExtension @After/@Before -&gt; @AfterEach/@BeforeEach package com.talk.about.web; import org.junit.jupiter.api.<b>Test</b>; import org.junit.jupiter.api.extension.ExtendWith; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot...","url":"http://jaajaa.tistory.com/253","blogname":"그래도 해야지","thumbnail":"https://search3.kakaocdn.net/argon/130x130_85_c/BzW4WayEJiP","datetime":"2022-09-02T01:38:25.000+09:00"},{"title":"[SpringBoot] API, API <b>Test</b>","contents":"posts.PostsRepository; import com.talk.about.web.dto.PostsSavsRequestDto; import org.junit.jupiter.api.AfterEach; import org.junit.jupiter.api.<b>Test</b>; import org.junit.jupiter.api.extension.ExtendWith; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot...","url":"http://jaajaa.tistory.com/257","blogname":"그래도 해야지","thumbnail":"https://search4.kakaocdn.net/argon/130x130_85_c/J35HNo8Krp5","datetime":"2022-09-08T02:07:27.000+09:00"},{"title":"TDD(<b>Test</b>-driven Development)","contents":"TDD란? TDD(<b>Test</b>-driven Development)는 코드를 작성하기 전에 테스트를 쓰는 소프트웨어 개발 방법론입니다. 다시 말해, 개발자 자신이 바람직하다고 생각하는 코드의 결과를 미리 정의하고, 이것을 바탕으로 코드를 작성하는 법입니다. TDD를 통해 소프트웨어를 개발한다는 것은 작은 단위의 테스트 케이스를 작성하고...","url":"http://songsari.tistory.com/106","blogname":"송사리","thumbnail":"https://search4.kakaocdn.net/argon/130x130_85_c/K7Z2FJ28srN","datetime":"2022-08-03T10:59:08.000+09:00"},{"title":"[Android / <b>Test</b>] #1. 왜 테스트를 해야 하는가?","contents":"매우 중요한 부분이다. 개발하는 소프트웨어가 출시되기 전에 치명적인 오류와 잠재적인 문제, 그리고 안전한 코드 작성을 위한 도구로 많이 사용된다. TDD(<b>Test</b> Driven Development, 테스트 주도 개발)은 많은 소프트웨어 회사에서 당연시 될 정도로 테스트는 중요하다. 하지만 아직 테스트는 검증팀에서 해야하는 것...","url":"http://sonseungha.tistory.com/630","blogname":"Developer's Delight","thumbnail":"","datetime":"2022-09-06T21:26:18.000+09:00"},{"title":"회전굽힘 피로시험 (Rotary Bending Fatigue <b>Test</b>)","contents":"회전 굽힘피로시험 (Rotary Bending Fatigue <b>Test</b>) 目次 1. 개요 1-1. 피로란? 1-2. 피로시험의 목적 1-3. 용어 설명 1-4. 피 로시험의 종류 2. 회전굽힘 피로시험 2-1. 회전굽힘 피로시험 종류 2-2. 회전굽힘 피로시험 데이터 3. Ono‘s 회전굽힘 피로시험기 3-1. 회전굽힘피로시험기 구조 및 명칭 3-2. 회전 굽힘 피로...","url":"http://ppua.tistory.com/478","blogname":"Power to surprise.","thumbnail":"https://search1.kakaocdn.net/argon/130x130_85_c/LZzHOiIFKuM","datetime":"2022-09-17T18:30:53.000+09:00"},{"title":"하지직거상 검사 [Straight Leg Raising <b>Test</b>]","contents":"알고 어떤 원리로 테스트를 하는지 알 게 된다면 이 역시 셀프로 해보고 나의 상태를 정확하지는 않지만 어느 정도 파악할 수 있을 거라 생각됩니다. SLR <b>test</b> 방법 이 검사는 각각의 다리를 따로 검사하되 정상 측 다리를 먼저 검사하고 문제가 되는 다리를 검사합니다. 능동적으로 하는  하지직거상 검사는 본인 스스로...","url":"http://gseek-storage.tistory.com/24","blogname":"나의 지식 저장소","thumbnail":"https://search4.kakaocdn.net/argon/130x130_85_c/GcWMvhh3TOt","datetime":"2022-09-15T15:03:58.000+09:00"},{"title":"데이터 접근 기술 - <b>Test</b>","contents":"데이터베이스를 연동한 상태에서 잘 동작하는지 확인하기 위해 테스트 코드를 작성 해 보자. 우선 <b>test</b> 디렉토리의 application.properties 파일에도 데이터베이스 관련 정보를 넣어 줘야 한다. spring.profiles.active=<b>test</b> spring.datasource.url=jdbc:h2:tcp://localhost/~/<b>test</b> spring.datasource.username=sa...","url":"http://13months.tistory.com/463","blogname":"전자공책","thumbnail":"https://search4.kakaocdn.net/argon/130x130_85_c/23gIe6BQpLK","datetime":"2022-09-07T14:03:03.000+09:00"},{"title":"FMS(Functional Movement Screen) <b>Test</b>에 대하여","contents":"상체가 숙여진다 -&gt; 코어 안정성 부족 나무보드 밖으로 이탈 -&gt; 움직임을 완수하기 위한 동적인 안정성이 부족 ​ 4. Shoulder Mobility 4-1 Shoulder Clearing <b>test</b> 어깨의 충돌을 확인하는 검사로 반대쪽 어깨에 손을 올리고 팔꿈치를 들어 올림으로써 만약 어깨에 통증이 있다면 양성으로 기록하고 4번의 테스트는 0점...","url":"http://eroougym.tistory.com/6","blogname":"이루어짐 운동과학센터","thumbnail":"https://search1.kakaocdn.net/argon/130x130_85_c/LdDpj0bzUAZ","datetime":"2022-09-06T15:15:18.000+09:00"},{"title":"#18 <b>Test</b> Component","contents":"#18.0~1 App <b>Test</b> <b>test</b>는 jest로 진행하게 되는데 create-react-app을 실행하게되면 jset가 설치되어 있어서 별도의 설정이 필요 없다. <b>test</b>를 위해 *.spec.tsx형식으로 파일을 만들면 되는데 jest.mock을 사용하여Router를 임시방편으로 만들고 app을 rendering해준다  이때 getbytext는 rendering된 page에서 해당 값을...","url":"http://weightsforfun.tistory.com/29","blogname":"weightsforfun","thumbnail":"https://search2.kakaocdn.net/argon/130x130_85_c/J93SnoIS3JC","datetime":"2022-08-13T00:09:13.000+09:00"},{"title":"[Spring]테스트 주도 개발(<b>Test</b> Driven Development, TDD)","contents":"테이블 설계 [Spring]Spring Data JDBC - 도메인 엔티티&amp;테.. gnidinger.tistory.com 이번 글에선 테스트가 중심이 된 개발 프로세스인 테스트 주도 개발(<b>Test</b> Driven Development, TDD)에 대해 살핀다. <b>Test</b> Driven Development, TDD TDD란 이름 그대로 테스트를 중심에 둔  소프트웨어 개발 프로세스이다. 조금 더 구체...","url":"http://gnidinger.tistory.com/518","blogname":"고양이 두 잔","thumbnail":"https://search1.kakaocdn.net/argon/130x130_85_c/7AOGEHvaq12","datetime":"2022-09-13T23:21:00.000+09:00"}]}}}
```


## 키워드 검색
### 기본정보

**GET /v1/search/keyword HTTP/1.1**

**Test Host http://localhost:8080**

* 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.

* 검색어 별로 검색 횟수도 함께 제공 합니다.


### Request
#### Parameter

Name | Type |	Description | Required 
--- | --- | --- | ---
size | Integer | 최대 보여줄 키워드 개수. 10개까지 제공한다. 기본값 10 | X

### Response

#### status

* String Type, "OK", "NOK" 값 중 하나의 값을 보여줌

#### reason

* String Type, status의 값이 "NOK" 일 경우 원인을 보여줌.

#### result
Name | Type | Description 
--- | --- | --- 
body | KeywordBodyType Array | 검색 결과에 대한 Keyword Array

#### KeywordBodyType
Name | Type | Description 
--- | --- | --- 
keyword | String | 블로그 검색 시 사용한 query keyword 이다
hitcnt | Integer | keyword가 사용 된 회수 이다.


Sample
Request

```bash
curl -v -X GET "http://localhost:8080/v1/search/keyword?query=test"
```

Response

```json
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 18 Sep 2022 10:09:50 GMT
<
{"status":"OK","reason":null,"result":{"body":[{"keyword":"test","hitcnt":1}]}}
```
