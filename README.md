# twitter-example

트위터 API를 이용하여 실시간 트위터 데이터를 가져오는 예제입니다.

## 실행방법
### 트위터에서 발급받은 인증정보 정보 입력
* [트위터 인증 발급 사이트](https://apps.twitter.com/)
* config/TwitterConfiguration.java
~~~
    String consumerKey = "===========================";
    String consumerSecret = "===========================";
    String acessToken = "===========================";
    String acessTokenSecret = "===========================";
~~~

### TwitterManage.java 실행
* 사용자 질의 설정
~~~ Java
    Query query = new Query();
    query.setLang("ko");    //지역정보(예: 한국)
    query.setQuery("미세먼지"); //질의어 : 미세먼지
    query.setSince("2018-03-28");   //조회날짜 : 2018년 3월 28일 이후 트위터(YYYY-MM-DD 형태)
~~~

### 실행결과
~~~
전체개수===>150
[twitter] :RT @OfficialMonstaX: [#원호] 몬베베 !! 미세먼지심해요 마스크 꼭 끼고다니기! https://t.co/O9pVSMEs9d

[twitter] :RT @crzbro: 저 요즘 미세먼지 마스크로 '에어가드 스포츠 마스크'를 사용중인데, 이거 괜찮아요. 흡/배기밸브가 달려있어서 호흡이 편하고, 식약처의 KF94 인증도 받은 제품. 가격도 배송비포함 15,000원대로 저렴한데다가 3번 정도 빨아…

[twitter] :RT @bulhandang924: 미세먼지와의 대결에서 https://t.co/FU5f20sDkq

[twitter] :RT @forn_official: 수딩컵팩💚 수딩컵팩🎶

수딩컵팩 안써본 사람 없게 하기 프로젝트!
본 트윗을 RT 해주신 분들 중 10분께 수딩컵팩+α를 보내드립니다.

미세먼지로 자극받은 피부에는 티트리 수딩컵팩🍀(지우 차애템 안써본 사람 없게…

[twitter] :RT @383174U: 올해 봄은 미세먼지와 함께 하고 바로 찜통으로 넘어갈 예정입니다. https://t.co/vrvwaGb5xC

[twitter] :@BTS_twt 지민아 오늘도 쫀하루 ^~^ 오늘은 낮에 미세먼지 잠깐 풀려서 나두 산책하구 왔오 ㅎㅂㅎ 지민이도 너무 실내에만 있지 말고 햇빛 많이 쐬기😶😶😶 지민이 너무너무 보고싶다ㅠㅠ 흑흑 ㅠㅠ 일본 앨범 기대하구 있을게욥 😍😍

[twitter] :RT @127OX9: 롯데타워 79층에서 본.. 현재 하늘...^^
미세먼지 너무 심한데 ,, 아까 재난문자옴 ㅠㅠ https://t.co/T03A8SFYZ0
~~~

* 엑셀로 내보내기 경로 :  `resources/tweet.csv`

### 참고 사이트
* http://twitter4j.org/
