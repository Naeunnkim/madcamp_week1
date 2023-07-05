# madcamp_week1: 탭 구조를 활용한 안드로이드 앱 개발
> 4분반 정성원, 김나은

몰입캠프 1주차에는 공통과제로 탭 구조를 활용한 안드로이드 앱 제작을 하였다.
Android Studio와 Git을 활용한 협업에 익숙해지는 것이 궁극적인 목표이다.
* 탭 1: 나의 연락처 구축. 휴대폰의 연락처 데이터를 활용하거나, JSON 형식을 이용해서 임의의 연락처 데이터를 구축. (추천: ListView나 RecyclerView 등을 이용해서 데이터 보여 주기)
* 탭 2: 나만의 이미지 갤러리 구축. 이미지는 대략 20개 정도.
* 탭 3: 자유 주제 / 900여 개의 단어 데이터를 포함하는 단어 학습 어플.

<br/>

## 팀원

* KAIST 전산학부 정성원
* 한양대학교 컴퓨터소프트웨어학부 김나은


<br/>

## 개발 환경
- OS: Android(minSdk:21, targetSdk:31)
- Language: Java
- IDE: Android Studio
- Target Device: Galaxy S10E

<br/>

## 어플리케이션 소개(Application Introduction)
***

### Tab 1 - 연락처
<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/0a7efe3b-8171-4a8a-9bc3-b7effdb086b9" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/c5bb4c7e-b385-464b-8001-011728e4e5ce" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/5295a7be-ee34-45b8-879f-246186fac4c0" height="32%" width="32%">
  <br><br><br>
</p>

#### 기능 설명
- 프로필사진, 이름, 누르면 다이얼로 이동하는 통화 버튼이 포함된 연락처 목록을 RecyclerView를 활용하여 구현하였다.
- 각각의 연락처를 클릭하면 프로필사진과 이름, 전화번호, 이메일을 표시하는 상세정보 페이지로 이동한다. 각각의 통화, 문자, 이메일 버튼은 클릭시 해당 어플리케이션으로 이동하여 통화 발신과 문자 및 이메일 전송의 기능을 수행한다.
- 연락처 목록 상단 툴바에 있는 연락처 추가 아이콘을 클릭하면 연락처 추가 페이지로 이동해 이름, 전화번호, 이메일을 입력받는다.

#### 기타 디테일
- 사용자 이름이 길어 화면 크기를 넘어가는 경우 생략 표시(...)로 보여지도록 구현하였다.
- Activity간 이동이 발생하는 경우 toolbar에 뒤로가기 버튼을 추가해두어 이전화면으로 되돌아갈 수 있도록 했다.
***

### Tab 2 - 갤러리
<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/274ccc61-ab3b-410e-a2d7-fad89a3e08f3" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/85a4fba3-01fc-4675-a6cf-df217cdd0d0b" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/8c1de43a-d616-4efb-a742-849ded395e97" height="32%" width="32%">
  <br><br><br>
</p>

#### 기능 설명
- 프로그램의 drawable폴더에 저장된 20개의 이미지를 RecyclerView의 GridLayoutManager를 활용해 한 행에 3개씩 보여주는 갤러리 화면이다.
- 각각의 사진을 클릭하면 사진 원본을 보여주는 새로운 Activity로 이동한다. PhotoView 라이브러리를 사용해 해당 이미지를 두 손가락으로 확대 및 축소할 수 있도록 구현하였다.

***

### Tab 3 - 단어장
<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/e75d4043-528c-4ae2-9be8-ab6a8f856bf4" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/0c959ade-a809-448e-8276-8e481ad3347f" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/5e811508-e93f-4bf7-b704-c2f18504fae3" height="32%" width="32%">
  <br><br><br>
</p>

#### 기능 설명
- 900여 개의 영단어를 하루 30개씩 30일 간 학습할 수 있도록 구현한 단어장 어플리케이션이다.
- 단어를 학습하는 Activity와 학습한 단어를 바탕으로 카드 짝 맞추기, 퀴즈 풀기, wordle의 3가지 게임을 제공하는 게임 시작 Activity로 구성되어 있다.
- 단어 학습
  - 단어 학습 버튼을 클릭하면 학습 일자를 선택할 수 있는 페이지가 나온다.
  - 각각의 학습 일자를 선택하면 단어와 그 뜻이 나란히 적힌 목록을 제공한다.
  - 해당 목록은 RecyclerView를 활용한다.
 
<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/d073106f-8505-4ada-847e-6da83f121e49" height="32%" width="32%>
  <br>
</p>
 
- 단어 게임
  - 단어 게임을 클릭하면 카드 짝 맞추기, 퀴즈 풀기, wordle의 3가지 게임 중 하나를 선택할 수 있는 페이지로 이동한다.

***

<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/07a8a6e8-6996-46c6-9ae5-f88e12f9d47a" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/f4445243-fd66-4def-8c31-c1a36d20f943" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/9df5fbc7-b1fe-4bc3-8495-4fda305811ee" height="32%" width="32%">
  <br><br><br><br>
</p>

- 카드 짝 맞추기
  - 카드 뒷면에 적힌 영단어와 뜻을 맞추는 게임이다.
  - 일자별 단어 데이터에서 임의로 5개의 단어를 선정한다.
  - 2장의 카드를 고를 때 마다 '매치 성공' 혹은 '매치 실패' 메세지를 보여주며, 5쌍의 카드를 모두 올바르게 뒤집은 경우 '게임 종료' 메세지를 띄운다.

<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/a9243703-1a31-476e-9253-402bf6a450b7" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/118dc42b-f0d6-4806-aa77-4c7b0014dfad" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/d5e08179-5010-499a-a48a-06ea4fcf3218" height="32%" width="32%">
  <br><br><br>
</p>

- 퀴즈 풀기
  - 단어의 뜻을 보고 알맞은 영단어를 고르는 게임이다.
  - 일자별 단어 데이터에서 임의로 10개의 정답 단어를 선정한다. 그 외 선지에 포함될 단어들은 문제가 넘어갈 때 마다 임의로 3개를 골라 표시한다.
  - 답을 고른 뒤 '정답 확인' 버튼을 클릭하면 정답인 경우 해당 단어가 초록색으로 바뀌며, 오답인 경우 빨간색으로 표시한 뒤 정답 단어를 초록색으로 바꾼다. 이후 다음 버튼을 눌러 게임을 진행하는 방식이다.
  - 10문제를 모두 풀면 결과 페이지로 이동해 점수를 표시한다.
  - 문제 진행 과정은 ProgressBar에 표시된다.

<p>
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/a3a855fc-4c1b-4160-a94a-f75e4f46414b" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/86d7131d-b989-4f66-a1bd-d3c56be8ce52" height="32%" width="32%">
  <img src="https://github.com/Naeunnkim/madcamp_week1/assets/128071056/6e5b37c8-14da-4f2e-9e1f-1bf6f96335be" height="32%" width="32%">
  <br><br><br>
</p>

- wordle
  - 5개의 알파벳으로 구성된 단어가 무엇인지 6회 안에 맞추는 게임이다.
  - 맞는 자리에 맞는 알파벳을 넣은 경우 초록색, 단어에 포함되어있는 알파벳을 골랐지만 위치가 틀린 경우 노란색, 단어에 없는 알파벳을 고른 경우 회색으로 표시한다.
  - 6회 안에 정답을 맞추지 못할 경우 정답을 알려주는 메세지가 표시된다.
  - 화면 우측 상단 버튼을 누르면 wordle 게임의 규칙을 설명하는 다이얼로그가 나타난다.
