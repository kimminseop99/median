<p align="center">
   <img src="https://github.com/user-attachments/assets/20fbcf40-f860-44d5-bdee-c29ab3f00720" alt="로고" width="500"/>
</p>

# 병원 예약 프로그램
<br><br>

# 💬 프로젝트 설명
-  환자가 효율적으로 병원 예약을 관리하고 예약된 시간에 의료 서비스를 받을 수 있도록
지원하는 프로그램으로 환자들은 오랜 대기 시간을 피하고 병원은 예약 및 진료 관리를 효율적으로 할 수 있습니다.

# 💨 개발 기간
- 2024 4.08 ~ 2024 4.23

# 🛠 개발 환경
- 운영체제 : Windows 10, 11
- 통합개발환경(IDE) : IntelliJ
- JDK 버전 : JDK 21
- 빌드 툴 : Gradle
- 관리 툴 : GitHub

# 💻 기술 스택

### Version Control
<div>
    <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

### Backend Technologies
<div>
    <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
</div>

### Databases
<div>
    <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
    <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
</div>


# 👥 개발 팀원
|                                                               **김민섭**                                                                |
|:------------------------------------------------------------------------------------------------------------------------------------:|
| [<img src="https://avatars.githubusercontent.com/u/161572638?v=4" height=150 width=150> <br/> @kimminseop99](https://github.com/kimminseop99)|


### 🕴️ 김민섭
+ UI, 페이지
  - 메인 페이지
  - 회원 페이지
  - 예약 페이지
  - 게시판 페이지
  - 의료진 페이지
  - 관리자 페이지
+ 기능
  - 회원가입(아이디, 비밀번호, 나이, 번호, 주민 번호, 신장, 체중, 기저 질환, 이름을 입력하면 가입 가능)
  - 예약 (진료 과와 의사 그리고 진료 시간을 선택 후 증상을 입력하면 예약이 완료)
  - 예약 취소 (취소를 희망하는 예약 번호를 입력하면 취소가 가능)
  - 진료 (의료진은 자신에게 예약한 환자의 정보를 확인하고 진료가능 진료가 완료된다면 예약 번호를 입력해 진료완료)
  - 게시글 생성 (회원은 공지 게시판을 제외한 모든 게시판에 게시물 생성 가능 그리고 공지 게시판은 오직 관리자만이 게시물 생성이 가능)
  - 게시 글 수정, 삭제 (게시물을 작성한 작성자만이 수정과 삭제가 가능)
  - 게시 글 댓글 (회원, 의사, 관리자 모두 게시 글에 댓글 입력 가능)
  - 관리자 암호 (관리자 페이지를 확인 하기 위해서는 관리자 암호를 입력)
  - 관리자 관리 (모든 회원, 의료진, 예약, 공자 게시물의 정보를 확인 할 수 있으며 삭제 가능)

# 🗃️ ER 다이어그램

![ER Diagram](src/main/resources/static/images/functionImage/ARTAUCTION-ER-DIAGRAM.png)

# 🗂️ 프로젝트 구조

```

```


# 👤 주요 기능
<details>
   <summary>🖼️ 경매 기능</summary>
   <br/>

   ### 1. 작가가 자신의 작품을 경매에 내놓기 위해 관리자에게 경매 신청을 합니다.
   ![경매 기능 스크린샷 1](src/main/resources/static/images/functionImage/artauction-artistProductSummit.png)
   <br><br>

   ### 2. 관리자는 신청된 작품을 보고 검수합니다.
   ![경매 기능 스크린샷 2](src/main/resources/static/images/functionImage/artauction-adminProductSummitManage.png)
   <br><br>

   ### 3. 검토가 완료되면 관리자는 해당 제품을 경매에 내놓습니다.
   ![경매 기능 스크린샷 3](src/main/resources/static/images/functionImage/artauction-adminAddAuction.png)
   <br><br>

   ### 4. 사용자는 경매 페이지에서 진행 중인 경매 작품들을 확인할 수 있습니다.
   ![경매 기능 스크린샷 4](src/main/resources/static/images/functionImage/artauction-auctionPage.png)
   <br><br>

   ### 5. 경매 응찰을 하기 전 (다른 사용자가 이미 입찰 금액을 제시한 상황)
   ![경매 기능 스크린샷 5](src/main/resources/static/images/functionImage/artauction-beforeBid.png)
   <br><br>

   ### 6. 경매 응찰을 하고 난 후
   ![경매 기능 스크린샷 6](src/main/resources/static/images/functionImage/artauction-afterBid.png)
   <br><br>

   ### 7. 사용자의 마이페이지에서 경매 입찰 목록을 확인할 수 있습니다.
   ![경매 기능 스크린샷 7](src/main/resources/static/images/functionImage/artauction-myPagebeforeBidList.png)
   <br><br>

   ### 8. 경매가 종료되고 낙찰자로 선정이 되면 낙찰 목록에서 확인할 수 있습니다.
   ![경매 기능 스크린샷 8](src/main/resources/static/images/functionImage/artauction-myPageBidList.png)
   <br><br>

</details>



<details>
   <summary>🔔 알림 기능</summary>
   <br/>

  -  <details>
      <summary>📨 경매 관련 알림 설정 및 시작 알림</summary>
      <br/>

      ### 1. 사용자는 예정된 경매의 상세 페이지에서 알림 설정을 할 수 있습니다.
      ![알림 기능 스크린샷 1](src/main/resources/static/images/functionImage/artauction-mailBefore1hour.png)
      <br><br>

      ### 2. 사용자의 개인 메일로 경매 시작 1시간 전 경매 시작 알림 메일이 발송됩니다.
      ![알림 기능 스크린샷 2](src/main/resources/static/images/functionImage/artauction-1hourNotificationMail.png)
      <br><br>
      
     </details>

 -   <details>
      <summary>🎉 경매 결과 알림</summary>
      <br/>

      ### 1. 해당 작품의 작가에게 축하 메일이 발송됩니다.
      ![알림 기능 스크린샷 3](src/main/resources/static/images/functionImage/artauction-auctionProductArtist.png)
      <br><br>

      ### 2. 해당 작품을 낙찰한 낙찰자에게 축하 메일이 발송됩니다.
      ![알림 기능 스크린샷 4](src/main/resources/static/images/functionImage/artauction-auctionWinningMail.png)
      <br><br>

      ### 3. 낙찰하지 못했지만 해당 작품의 경매에 참여한 참여자들에게 결과 알림 메일이 발송됩니다.
      ![알림 기능 스크린샷 5](src/main/resources/static/images/functionImage/artauction-auctionFailMail.png)
      <br><br>
   </details>

</details>

<details>
   <summary>👩‍🎨 작가 권한 부여</summary>
   <br/>
    
   ### 1. 사용자는 작가의 권한을 받기 위해 작가 신청을 할 수 있습니다.(이용약관 동의 및 증빙 자료 제출은 필수!!)
   ![경매 기능 스크린샷 1](src/main/resources/static/images/functionImage/artauction-artistRequestSummitPage.png)
   <br><br>

   ### 2. 신청을 완료하면 관리자의 승인을 기다립니다.
   ![경매 기능 스크린샷 2](src/main/resources/static/images/functionImage/artauction-artistRequestPage.png)
   <br><br>

   ### 3. 관리자는 작가 신청 내역에서 증빙 자료를 확인하고 작가 승인을 허가해 줄 수 있습니다.
   ![경매 기능 스크린샷 3](src/main/resources/static/images/functionImage/artauction-adminArtistRequestList.png)
   <br><br>

   ### 4. 이 후 관리자는 회원 권한 설정 페이지에서 해당 회원에 작가의 권한을 부여해줄 수 있습니다.
   ![경매 기능 스크린샷 4](src/main/resources/static/images/functionImage/artauction-adminMemberAuthorityPage.png)
   <br><br>

   ### 5. 작가권한을 받은 사용자는 작가 프로필을 생성할 수 있습니다.
   ![경매 기능 스크린샷 5](src/main/resources/static/images/functionImage/artauction-artistProfileCreate.png)
   <br><br>

   ### 6. 작가 프로필을 생성하고나면 작가 개인 페이지가 생성됩니다.
   ![경매 기능 스크린샷 6](src/main/resources/static/images/functionImage/artauction-artistProfileForm.png)
   <br><br>

   ### 7. 작가는 자신의 작품을 등록할 수 있습니다.(작가 프로필을 생성 후 작품 등록 가능!!)
   ![경매 기능 스크린샷 7](src/main/resources/static/images/functionImage/artauction-productCreatePage.png)
   <br><br>

</details>

# ⚙️ 페이지별 기능
<details>
   <summary>회원 가입</summary>
   <br/>

#### 1. 회원가입
- 아이디, 닉네임, 비밀번호, 비밀번호 확인, 전화번호, 이메일, 주소를 모두 작성하면 회원가입기능 버튼이 활성화됩니다.
- 비밀번호와 비밀번호 확인란의 문자열이 같지 않을 시에 회원가입이 불가능합니다.
- 아이디와 닉네임은 중복확인이 필요하며 이메일인증은 필수 입니다.
- 주소는 KaKao Api를 사용하여 쉽게 찾아볼 수 있습니다.

| 회원가입 |
|----------|
| <img width="1273" alt="artauction-joinPage" src="https://github.com/user-attachments/assets/a9648ba7-facc-468e-ae58-75d8a7513761"> |
<br>

</details>

<details>
   <summary>로그인</summary>
   <br/>

#### 1. 로그인
 - 아이디와 비밀번호 입력시 해당 유저가 가입되어있으면 로그인 됩니다.
 - 아이디 저장을 체크하고 로그인을 하면 자동으로 아이디 정보가 저장됩니다.

#### 2. 아이디 및 비밀번호 찾기
 - 아이디와 비밀번호가 일치하지 않을때 일부 정보를 입력해서 찾기가 가능합니다.
 - 아이디 찾기는 계정의 메일 정보를 입력하면 해당 메일로 아이디 정보가 발송됩니다.
 - 비밀번호 찾기는 계정의 아이디와 메일 정보를 입력하면 해당 메일로 임시 비밀번호가 발송됩니다.

#### 3. 소셜 로그인
 - 카카오, 네이버, 구글로 소셜로그인이 가능합니다.

| 로그인 |
|----------|
| <img width="1274" alt="artauction-loginPage" src="https://github.com/user-attachments/assets/13419e4e-a4ad-4b7f-8e9c-4e96c07f8f42"> |
<br>

</details>

<details>
   <summary>경매 페이지</summary>
   <br/>

#### 1. 진행중인 경매 리스팅
 - 경매 페이지에서는 현재 진행중인 경매 작품의 목록을 확인 할 수 있습니다.
 - 작품의 작가이름, 작품명, 크기, 사용재료, 현재가를 확인할 수 있습니다.
 - 예정된 경매가 활성화 되거나 진행중이였던 경매가 종료되면 경매 페이지에서 자동으로 사라집니다.
 - 작품의 상세보기를 클릭하면 각각의 작품의 상세정보가 포함되어 있으며 경매를 할 수 있는 페이지로 이동하게 됩니다.
 - 페이징 기능이 구현되어어 있어 16개 이상이 되면 자동으로 페이지가 추가됩니다.

#### 2. 정렬 및 검색
 - 올라온 작품들을 최신순, 가격이 높은순과 낮은순으로 확인할 수 있습니다.
 - 작품명을 검색하여 원하는 작품을 간편하게 찾을 수 있습니다.
 - 카테고리를 확인 및 클릭하면 경매 제목별로 작품을 정렬화해 볼 수 있습니다. 


| 경매 페이지 |
|----------|
| <img width="1274" alt="artauction-auctionPage" src="https://github.com/user-attachments/assets/1c680d00-615c-4ad1-a496-5569cad2a46a"> |
<br>

</details>

<details>
   <summary>캘린더 페이지</summary>
   <br/>

#### 1. 예정된 경매 목록
 - 캘린더 페이지에서는 예정된 경매 목록을 확인 할 수 있습니다.
 - month, week, day별로 경매를 확인 할 수 있으며 today 버튼으로 현재 날짜를 확인 할 수 있습니다.
 - 경매바를 통해 예정된 경매의 시작시간을 확인할 수 있으며 경매 바 클릭시에 경애의 상세 페이지로 이동할 수 있습니다.

| 캘린더 페이지 |
|----------|
| <img width="1271" alt="artauction-calendarPage" src="https://github.com/user-attachments/assets/ac3427de-ceac-4ca0-87f5-8df64f9bd6d9"> |
<br>

</details>

<details>
   <summary>작품 페이지</summary>
   <br/>

#### 1. 작품 목록
 - 모든 작품을 확인할 수 있으며 작품을 클릭하면 작품 상세 페이지로 이동됩니다.
 - 작품의 작가이름, 작품명, 크기, 사용재료, 현재가를 확인할 수 있습니다.
 - 페이징 기능이 구현되어 있어 16개 이상이 되면 자동으로 페이지가 추가됩니다. 

#### 2. 정렬 및 검색
 - 올라온 작품들을 최신순, 가격이 높은순과 낮은순으로 확인할 수 있습니다.
 - 작품명과 작가이름을 검색하여 원하는 작품을 간편하게 찾을 수 있습니다.

#### 3. 찜 기능
 - 원하는 작품의 하트 버튼을 누르면 찜 기능이 활성화됩니다.
 - 찜 한 작품은 마이페이지의 찜 메뉴에서 확인 가능합니다. 


| 작품 페이지 |
|----------|
| <img width="1274" alt="artauction-productPage" src="https://github.com/user-attachments/assets/9cdd5842-9038-4fc9-b561-a3cbd3f9ab77"> |
<br>

</details>

<details>
   <summary>작가 페이지</summary>
   <br/>

#### 1. 작가 정보
 - 작가 페이지에서 작가의 정보(이름, 생년월일, 소개, 전화번호, 메일, 작가 작품 정보)를 확인 할 수 있습니다.
 - 작가 본인의 프로필 페이질 경우 소개와 작품 정보를 수정 할 수 있으며 작가 개인 프로필폼을 삭제 할 수 있습니다.

#### 2. 경매 작품 신청
 - 작가 본인의 계정일 경우 작가 프로필의 개인 작품 페이지에서 자신의 작품을 경매에 올릴 수 있습니다.
 - 경매 신청 작품에는 작품 올리기 버튼이 보이지 않습니다.  


| 작가 페이지 |
|----------|
| <img width="1271" alt="artauction-artistPage" src="https://github.com/user-attachments/assets/f9fa27f3-77ae-4332-a0b4-0cf068a3d116"> |
<br>

</details>

<details>
   <summary>마이 페이지</summary>
   <br/>

#### 1. 정보 수정
 - 회원의 정보를 확인 하고 수정(닉네임, 비밀번호, 메일, 프로필 이미지, 배송주소) 할 수 있습니다.
 - 본인의 충전 금액을 확인 할 수 있습니다.
   

#### 2. 찜 메뉴
 - 작품 페이지에서 찜 하트를 누르면 찜 메뉴에서 확인할 수 있습니다.
 - 찜 메뉴에서 작품 보기를 누르면 해당 작품의 상세 페이지로 이동 할 수 있습니다.
 - 찜 작품의 정보를 확인 할 수 있습니다.

#### 3. 입찰 내역 메뉴
 - 현재 계정이 입찰중인 작품의 정보를 확인 할 수 있습니다.
 - 입찰 내역에 올라온 작품의 이미지를 클릭하면 입찰중인 작품의 상세 페이지로 이동됩니다.

#### 4. 낙찰 내역
 - 현재 계정이 낙찰한 작품의 정보를 확인 할 수 있습니다.
 - 낙찰된 날짜와 낙찰가를 확인 할 수 있습니다.
 - 주문 상세 페이지로 이동할 수 있습니다.
 - 주문 상세 페이지에서는 주문 정보와 낙찰자의 정보를 확인 할 수 있으며 배송 페이지로 이동가능합니다.
 - 배송 페이지에서는 현재 작품의 배송 정보를 확인 할 수 있습니다.

#### 5. 문의 내역
 - 자신이 문의한 문의 내역을 확인 할 수 있습니다.
 - 문의의 상태는 관리자가 답변을 작성해주지 않았을 경우 "처리 전" 답변을 작성 해주었을 경우 "처리 완료"가 됩니다.


| 마이 페이지 |
|----------|
| <img width="1273" alt="artauction-myPage" src="https://github.com/user-attachments/assets/85018117-6cfb-41e8-a697-dace0678e2d8"> |
<br>

</details>


# 🔥 트러블 슈팅
<details>
   <summary>김민섭</summary>
   <br/>
   
## 🚨 이슈
Calendar API를 사용하여 데이터를 화면에 표시하려고 했지만, 데이터는 정상적으로 보이지만 캘린더 뷰가 제대로 나타나지 않았습니다. 결과적으로 검은 화면에 데이터만 나타나는 문제가 발생했습니다. <br> <br>

## 🛑 원인
api/calendar에서 전달된 데이터 포맷과 캘린더 뷰를 나타내는 매핑이 혼합되어 있었기 때문에 페이지 URL에 접속했을 때 검은 화면에 데이터만 표시되었습니다. <br> <br>

## 🚥 해결
데이터와 캘린더 뷰를 명확히 구분하여 각각 별도의 매핑을 통해 문제를 해결했습니다. 데이터 처리와 뷰 렌더링을 독립적으로 관리하여 정상적으로 캘린더 뷰가 표시되도록 조정하였습니다.
</br></br>
</details>   

# 🌱 개선 목표
## 1. 관리자 회원권한 설정
**문제점**: 회원의 활성/비활성 기능이 작동하지 않으며, 권한 설정 후 페이지 새로고침 시 접근 권한 오류가 발생하고, 권한 설정한 계정으로 자동 로그인되는 오류와 작품 페이지 접근 오류가 발생합니다.</br>

**개선 사항**: 회원 권한 설정 기능을 수정하여 활성/비활성 상태가 제대로 작동하도록 하고, 페이지 새로고침 후 접근 권한 오류를 해결합니다. 또한, 자동 로그인 문제를 방지하고, 작품 페이지 접근 오류를 수정하여 안정적인 사용자 권한 관리를 구현합니다.
</br></br>

# 👍 프로젝트 후기

### 🕴️ 김민섭
주제 선정과 기획부터 어려움이 있어 무사히 프로젝트를 끝낼 수 있을까 걱정도 많이 되었고 이전에 다뤄보지 않았던 새로운 시스템들을 구현해낼 수 있을까하는 우려도 적지 않았던 것 같습니다. 프로젝트 진행 중에도 시스템에 관한 변수나 예외가 지속적으로 발생했고 처리 능력이 부족하다보니 제가 맡은 부분에만 집중하게 되었고 때문에 팀원들에게 도움을 주지 못했던 것 같습니다. 그럼에도 팀원들이 각자 맡은 역활을 충분히 해 주었고 제가 부족한 부분도 팀원들이 채워주어 프로젝트를 무사히 끝낼 수 있었다고 생각합니다. 이번 프로젝트로 디자인이나 유용한 기능들을 많이 알아가는 것 같아 뜻 깊은 시간이 되었고 또 꼭 한번 쯤은 만들어보고 싶었던 사이트를 구현해 낸 것에서 의미 있는 시간이 되었습니다.    
   
