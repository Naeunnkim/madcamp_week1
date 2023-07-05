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

## 어플리케이션 소개(Application Introduction)
***

### Tab 1 - 연락처
#### 기능 설명
- 프로필사진, 이름, 누르면 다이얼로 이동하는 통화 버튼이 포함된 연락처 목록을 RecyclerView를 활용하여 구현하였다.
- 각각의 연락처를 클릭하면 프로필사진과 이름, 전화번호, 이메일을 표시하는 상세정보 페이지로 이동한다. 각각의 통화, 문자, 이메일 버튼은 클릭시 해당 어플리케이션으로 이동하여 통화 발신과 문자 및 이메일 전송의 기능을 수행한다.
- 연락처 목록 상단 툴바에 있는 연락처 추가 아이콘을 클릭하면 연락처 추가 페이지로 이동해 이름, 전화번호, 이메일을 입력받는다.
***

#### 기타 디테일
- 사용자 이름이 길어 화면 크기를 넘어가는 경우 생략 표시(...)로 보여지도록 구현하였다.
- Activity간 이동이 발생하는 경우 toolbar에 뒤로가기 버튼을 추가해두어 이전화면으로 되돌아갈 수 있도록 했다.
***

### Tab 2 - 갤러리
#### 기능 설명
- 프로그램의 drawable폴더에 저장된 20개의 이미지를 RecyclerView의 GridLayoutManager를 활용해 한 행에 3개씩 보여주는 갤러리 화면이다.
- 각각의 사진을 클릭하면 사진 원본을 보여주는 새로운 Activity로 이동한다. PhotoView 라이브러리를 사용해 해당 이미지를 두 손가락으로 확대 및 축소할 수 있도록 구현하였다.

### Tab 3 - 단어장
#### 기능 설명
- 900여 개의 영단어를 하루 30개씩 30일 간 학습할 수 있도록 구현한 단어장 어플리케이션이다.
- 단어를 학습하는 Activity와 학습한 단어를 바탕으로 카드 짝 맞추기, 퀴즈 풀기, wordle의 3가지 게임을 제공하는 게임 시작 Activity로 구성되어 있다.
- 단어 학습
  - 단어 학습 버튼을 클릭하면 학습 일자를 선택할 수 있는 페이지가 나온다.
  - 각각의 학습 일자를 선택하면 단어와 그 뜻이 나란히 적힌 목록을 제공한다.
  - 해당 목록은 RecyclerView를 활용한다.
 
- 단어 게임
  - 단어 게임을 클릭하면 카드 짝 맞추기, 퀴즈 풀기, wordle의 3가지 게임 중 하나를 선택할 수 있는 페이지로 이동한다.
  - 카드 짝 맞추기 게임은 일자별 단어 데이터에서 임의로 5개의 단어를 선정해 카드 뒷편에 적힌 영단어와 그 뜻을 맞추는 게임이다. 2장의 카드를 고를 때 마다 '매치 성공' 혹은 '매치 실패' 메세지를 보여주며, 5쌍의 카드를 모두 올바르게 뒤집은 경우 '게임 종료' 메세지를 띄운다.
  - 퀴즈 풀기는 일자별 단어 데이터에서 임의로 10개의 단어를 선정한다. 해당 단어의 뜻을 보여주면 해당하는 영어단어를 고르는 방식으로 진행되며, 총 10문제로 구성된다. 4개의 선지에서 답을 고르고 아래 '정답 확인' 버튼을 클릭하면 정답인 경우 초록색, 오답인 경우 빨간색으로 해당 선지의 색을 바꾸며, 오답인 경우 맞는 답에 초록색으로 표시가 된다. 10문제를 모두 풀면 몇 문제를 맞았는지 보여주는 결과 페이지로 이동한다. 문제 진행 과정은 ProgressBar에 표시된다.
  - wordle 게임은 5개의 알파벳으로 구성된 단어가 무엇인지 6회안에 맞추는 게임이다. 맞는 자리에 맞는 알파벳을 넣은 경우 초록색, 맞는 알파벳을 골랐지만 위치가 틀린 경우 노란색, 둘 다 해당사항이 없는 경우 회색으로 표시된다. 6회 안에 정답을 맞추지 못할 경우 정답을 알려주는 메세지가 표시된다. 화면 우측 상단 버튼을 누르면 wordle 게임의 규칙을 설명하는 다이얼로그가 나타난다.

  
