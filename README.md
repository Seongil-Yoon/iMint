# 🌿아이민트
![iMint_Black](https://user-images.githubusercontent.com/93465128/169989949-caccfa6e-0060-4167-af12-56e7058ba793.png)

🔗 서비스 URL : https://imint.yukenet.wo.tc/ <br/>
🔗 팀 소개: https://hailey-hy.github.io/react-team7-porfolio/index.html <br/>
🔗 팀 노션페이지: https://night-hydrangea-f7d.notion.site/iMint-8268bf915a6f409e8008c60e83b4c9f7

## ****🗂****개요

- 멀티캠퍼스 지능형 웹개발 풀스택 과정의 팀 프로젝트로 진행한 아이민트입니다.
- 아이민트 서비스는 아이들의 안전에 초점을 맞춘 지역기반 중고 거래 웹사이트입니다.
- 아이민트에서 아이는 보호자와 연동을 마치면 상품 등록과 거래에 참여할 수 있습니다.
- 아이민트에서 보호자는 연동된 모든 아이들의 활동현황(상품별 거래 상태, 채팅내역, 차단목록)을 웹페이지 내 알림, 메일 알림을 통해 실시간으로 확인할 수 있습니다.

## ****👊****기획의도

![Untitled](https://user-images.githubusercontent.com/93465128/166223754-08cfcc7a-68b0-4b02-b7c0-bdea59a1fb28.png)

## ****👥****팀원 구성
![Untitled](https://user-images.githubusercontent.com/93465128/169990164-006f624b-b30f-461a-be52-e99e083c106d.png)
| 이름 | 윤성일 | 양정민 | 이강산 | 전해연 |
| --- | --- | --- | --- | --- |
| 역할 | 팀장 | 팀원 | 팀원 | 팀원 |
| 상세 | AWS EC2 서버 구축 <br/> 메인 페이지 <br/>상품 CRUD <br/>상품 무한 스크롤 <br/>음성 인식(STT) 상품 검색 <br/>파일 다중 업로드 <br/>거래 관련 메일 발송 <br/>관리자 페이지 - 상품 <br/>HTTPS/SSL <br/>HTTP 예외처리 | SNS 로그인/회원가입(OAuth2.0) <br/>동네 설정(카카오 API) <br/>회원 정보 수정 <br/>회원 탈퇴 <br/>Spring Security를 활용한 권한 인가 <br/>관리자 페이지 - 회원 목록 조회, 회원 통계, 회원 강제 탈퇴 <br/>테스트 코드 작성(JUnit5) <br/>로그인 페이지 CSS 구현 <br/>PPT 제작 | 마이페이지(백엔드) <br/>거래프로세스(관심/예약/거래/평가) <br/>웹소켓을 이용한 채팅/알림 <br/>JSP to Thymeleaf 이전 작업 수행 | 마이페이지(프론트엔드) <br/>팀 소개 페이지(리액트) <br/>사이트 공통 디자인 <br/>협업용 Notion 페이지 제작 |


---


## ****♟****정보 구조도

![Untitled](https://user-images.githubusercontent.com/93465128/166220866-fa827937-b32a-48d4-a677-2182bce80bbe.png)

## ****🧩****시스템 구조도

![시스템 구조도.drawio .png](https://user-images.githubusercontent.com/60749057/171457368-af427c37-5b37-44b4-b801-cb60a80e211c.png)

## ****🗺****ER 다이어그램

![Untitled](https://user-images.githubusercontent.com/93465128/166220903-db7ca9d7-4714-4fe9-a86a-75687b896fd8.png)

## ⚡️사용자 권한

| 권한명 | 용도 |
| --- | --- |
| CHILD | 보호자 연동이 완료된 아이 회원 |
| UN_CHILD | 미연동 or 탈퇴한 아이 회원 |
| GUARD | 위치 정보 인증이 완료된 보호자 회원 |
| UN_GUARD | 미인증 or 탈퇴한 보호자 회원 |
| GUEST | 관리자에 의해 강제 탈퇴된 회원 |
| ADMIN | 관리자 |


## 🛠 기능

🔗 권한관리: [https://youtu.be/2yc33skTdFw](https://youtu.be/2yc33skTdFw)

🔗 유해상품: [https://youtu.be/cr3zbbF5774](https://youtu.be/cr3zbbF5774)

🔗 거래평가: [https://youtu.be/0d5YHgXblKQ](https://youtu.be/0d5YHgXblKQ)

🔗 채팅필터링: [https://youtu.be/UackI0HQokM](https://youtu.be/UackI0HQokM)

🔗 차단: [https://youtu.be/Ow_irM2oB28](https://youtu.be/Ow_irM2oB28)

🔗 알림: [https://youtu.be/QZVVTugBYwk](https://youtu.be/QZVVTugBYwk)

🔗 이메일: [https://youtu.be/lrIIvCNdqro](https://youtu.be/lrIIvCNdqro)


## ⏰ 이슈 해결

### 1. 메일발송 후 대기시간 개선

- @Async로 처리 전 : 문서로딩에 4.57초 소요
    
    ![Untitled](https://user-images.githubusercontent.com/93465128/169992097-a0c74d7a-8039-4343-a2b9-f98b047d7938.png)
    
- @Async로 처리 후 : 문서로딩에 67밀리초 소요
    
    ![Untitled](https://user-images.githubusercontent.com/93465128/169992151-de7d4216-b0d3-4d53-b438-4c30ffa6e442.png)
    
### 2. [EC2 **자동 재부팅 (cron)(tomcat자동시작)**](https://www.notion.so/EC2-cron-tomcat-9c6d874656e64bc0aa99a93b0616a2ec)

### 3. **[[EC2] 프리티어(t2.micro)에서 Jenkins 용량 초과 문제](https://www.notion.so/EC2-t2-micro-Jenkins-909c1c483e4749e0a7d327478ce7a2a1)**

## 📒 ****프로젝트 디렉토리 구조****

<details>
<summary>자세히 보기</summary>
<div markdown="1">
    
    C:.
    |   mvnw
    |   mvnw.cmd
    |   pom.xml
    |   README.md
    |   
    \---src
        +---main
        |   +---java
        |   |   \---multi
        |   |       \---fclass
        |   |           \---iMint
        |   |               |   AsyncConfiguration.java
        |   |               |   ImintApplication.java
        |   |               |   MyWebConfig.java
        |   |               |   ServletInitializer.java
        |   |               |   
        |   |               +---admin
        |   |               |   +---controller
        |   |               |   |       AdminController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IAdminDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       AdminDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           AdminServiceImpl.java
        |   |               |           IAdminService.java
        |   |               |           
        |   |               +---ai
        |   |               |   +---controller
        |   |               |   |       AiController.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           INaverService.java
        |   |               |           sttServiceImpl.java
        |   |               |           
        |   |               +---block
        |   |               |   +---controller
        |   |               |   |       BlockController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IBlockDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       BlockDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           BlockServiceImpl.java
        |   |               |           IBlockService.java
        |   |               |           
        |   |               +---chat
        |   |               |   +---controller
        |   |               |   |       ChatController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IChatDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       ChatMessageDTO.java
        |   |               |   |       ChatroomJoinCheckDTO.java
        |   |               |   |       ChatroomOpenCheckDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           ChatServiceImpl.java
        |   |               |           IChatService.java
        |   |               |           
        |   |               +---common
        |   |               |   +---code
        |   |               |   |       ErrorCode.java
        |   |               |   |       
        |   |               |   +---exception
        |   |               |   |   |   HandlableException.java
        |   |               |   |   |   
        |   |               |   |   \---hadler
        |   |               |   |           ExceptionAdvice.java
        |   |               |   |           ForbiddenException.java
        |   |               |   |           InternalServerErrorException.java
        |   |               |   |           NotFoundException.java
        |   |               |   |           UnauthorizedException.java
        |   |               |   |           
        |   |               |   \---service
        |   |               |           FileServiceImpl.java
        |   |               |           IFileService.java
        |   |               |           IUtilService.java
        |   |               |           UtilServiceImpl.java
        |   |               |           
        |   |               +---goods
        |   |               |   +---controller
        |   |               |   |       GoodsController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IGoodsDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       GoodsDTO.java
        |   |               |   |       GoodsImagesDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           GoodsServiceImpl.java
        |   |               |           IGoodsService.java
        |   |               |           
        |   |               +---mail
        |   |               |   |   MailHandler.java
        |   |               |   |   
        |   |               |   +---controller
        |   |               |   |       MailController.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       MailDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           IMailService.java
        |   |               |           MailServiceImpl.java
        |   |               |           
        |   |               +---main
        |   |               |   +---controller
        |   |               |   |       MainController.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           IMainService.java
        |   |               |           MainServiceImpl.java
        |   |               |           
        |   |               +---member
        |   |               |   +---controller
        |   |               |   |       MemberCotroller.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IMemberDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       MemberDTO.java
        |   |               |   |       Role.java
        |   |               |   |       SessionMember.java
        |   |               |   |       ThumbnailDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           IMemberService.java
        |   |               |           MemberServiceImpl.java
        |   |               |           
        |   |               +---mypage
        |   |               |   +---controller
        |   |               |   |       MypageCotroller.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IMypageDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       MypageBlockDTO.java
        |   |               |   |       MypageChatroomDTO.java
        |   |               |   |       MypageConnectionDTO.java
        |   |               |   |       MypageDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           IMypageService.java
        |   |               |           MypageServiceImpl.java
        |   |               |           
        |   |               +---notification
        |   |               |   +---dto
        |   |               |   |       NotificationDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           INotificationService.java
        |   |               |           NotificationServiceImpl.java
        |   |               |           
        |   |               +---rating
        |   |               |   +---controller
        |   |               |   |       RatingController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       IRatingDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       RatingDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           IRatingService.java
        |   |               |           RatingServiceImpl.java
        |   |               |           
        |   |               +---security
        |   |               |   |   GenerateCertCharacter.java
        |   |               |   |   
        |   |               |   +---auth
        |   |               |   |   +---config
        |   |               |   |   |       OAuthAttributes.java
        |   |               |   |   |       SecurityConfig.java
        |   |               |   |   |       
        |   |               |   |   \---provider
        |   |               |   |           KakaoUserInfo.java
        |   |               |   |           NaverUserInfo.java
        |   |               |   |           OAuth2UserInfo.java
        |   |               |   |           
        |   |               |   +---controller
        |   |               |   |       IndexController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       ISecurityDAO.java
        |   |               |   |       
        |   |               |   +---parsing
        |   |               |   |   +---mbid
        |   |               |   |   |       ParseMbId.java
        |   |               |   |   |       
        |   |               |   |   \---role
        |   |               |   |           ParseMbRole.java
        |   |               |   |           
        |   |               |   \---service
        |   |               |           CustomOAuth2UserService.java
        |   |               |           
        |   |               +---transaction
        |   |               |   +---controller
        |   |               |   |       TransactionController.java
        |   |               |   |       
        |   |               |   +---dao
        |   |               |   |       ITransactionDAO.java
        |   |               |   |       
        |   |               |   +---dto
        |   |               |   |       TransactionDTO.java
        |   |               |   |       
        |   |               |   \---service
        |   |               |           ITransactionService.java
        |   |               |           TransactionServiceImpl.java
        |   |               |           
        |   |               +---websocket
        |   |               |   +---config
        |   |               |   |       WebSocketConfig.java
        |   |               |   |       WebSocketPrincipal.java
        |   |               |   |       
        |   |               |   \---interceptor
        |   |               |           WebSocketInterceptor.java
        |   |               |           
        |   |               \---wishlist
        |   |                   +---controller
        |   |                   |       WishlistController.java
        |   |                   |       
        |   |                   +---dao
        |   |                   |       IWishlistDAO.java
        |   |                   |       
        |   |                   +---dto
        |   |                   |       WishlistDTO.java
        |   |                   |       
        |   |                   \---service
        |   |                           IWishlistService.java
        |   |                           WishlistServiceImpl.java
        |   |                           
        |   +---resources
        |   |   |   application.properties
        |   |   |   keystore.jks
        |   |   |   
        |   |   +---mybatis
        |   |   |   |   mybatis-config.xml
        |   |   |   |   
        |   |   |   \---mappers
        |   |   |           admin-mapper.xml
        |   |   |           block-mapper.xml
        |   |   |           chat-mapper.xml
        |   |   |           goods-mapper.xml
        |   |   |           member-mapper.xml
        |   |   |           mypage-mapper.xml
        |   |   |           rating-mapper.xml
        |   |   |           security-mapper.xml
        |   |   |           transaction-mapper.xml
        |   |   |           wishlist-mapper.xml
        |   |   |           
        |   |   +---static
        |   |   |   +---css
        |   |   |   |   |   common_style.css
        |   |   |   |   |   error.css
        |   |   |   |   |   footer.css
        |   |   |   |   |   header-search.css
        |   |   |   |   |   header.css
        |   |   |   |   |   main.css
        |   |   |   |   |   
        |   |   |   |   +---admin
        |   |   |   |   |       admin_goods.css
        |   |   |   |   |       
        |   |   |   |   +---chat
        |   |   |   |   |       chatbox.css
        |   |   |   |   |       
        |   |   |   |   +---goods
        |   |   |   |   |       carousel.css
        |   |   |   |   |       goods-common-style.css
        |   |   |   |   |       goods-detail.css
        |   |   |   |   |       goods-modify.css
        |   |   |   |   |       goods-write.css
        |   |   |   |   |       
        |   |   |   |   +---member
        |   |   |   |   |       admin_member.css
        |   |   |   |   |       member_basic.css
        |   |   |   |   |       member_login.css
        |   |   |   |   |       member_register.css
        |   |   |   |   |       member_register_connect.css
        |   |   |   |   |       member_register_new.css
        |   |   |   |   |       
        |   |   |   |   +---mypage
        |   |   |   |   |       mypage_asidebar.css
        |   |   |   |   |       mypage_block.css
        |   |   |   |   |       mypage_edit.css
        |   |   |   |   |       mypage_location.css
        |   |   |   |   |       mypage_main.css
        |   |   |   |   |       mypage_mylist.css
        |   |   |   |   |       mypage_mylist_content.css
        |   |   |   |   |       mypage_withdraw.css
        |   |   |   |   |       
        |   |   |   |   \---trx
        |   |   |   |           rating.css
        |   |   |   |           
        |   |   |   +---images
        |   |   |   |       401.png
        |   |   |   |       403.png
        |   |   |   |       404.jpg
        |   |   |   |       500.jpg
        |   |   |   |       background.png
        |   |   |   |       background2.jpg
        |   |   |   |       btn_google_dark_normal_xxxhdpi.9.png
        |   |   |   |       btn_google_light_focus_xxxhdpi.9.png
        |   |   |   |       caution.png
        |   |   |   |       children.png
        |   |   |   |       default-icon.jpeg
        |   |   |   |       delete-icon.png
        |   |   |   |       fileupload.png
        |   |   |   |       goods_slide_icon_left.png
        |   |   |   |       goods_slide_icon_right.png
        |   |   |   |       hamster.png
        |   |   |   |       iMint_Black.png
        |   |   |   |       iMint_Black.psd
        |   |   |   |       iMint_White.png
        |   |   |   |       kakao_login_medium_wide.png
        |   |   |   |       login_chat.png
        |   |   |   |       login_map.png
        |   |   |   |       login_transaction.png
        |   |   |   |       mic.png
        |   |   |   |       mint.png
        |   |   |   |       mint_background.jpg
        |   |   |   |       naver_icon.png
        |   |   |   |       night-rain_0.png
        |   |   |   |       noimage.png
        |   |   |   |       plus.png
        |   |   |   |       rain_1.png
        |   |   |   |       search.png
        |   |   |   |       sun-cloud_3.png
        |   |   |   |       sun-rain_2.png
        |   |   |   |       sun_4.png
        |   |   |   |       write-icon.png
        |   |   |   |       
        |   |   |   +---js
        |   |   |   |   |   header-search.js
        |   |   |   |   |   header.js
        |   |   |   |   |   main.js
        |   |   |   |   |   
        |   |   |   |   +---admin
        |   |   |   |   |       admin_goods.js
        |   |   |   |   |       admin_member.js
        |   |   |   |   |       admin_stats.js
        |   |   |   |   |       
        |   |   |   |   +---block
        |   |   |   |   |       block.js
        |   |   |   |   |       
        |   |   |   |   +---chat
        |   |   |   |   |       chatbox.js
        |   |   |   |   |       
        |   |   |   |   +---goods
        |   |   |   |   |       carousel.js
        |   |   |   |   |       goods-detail.js
        |   |   |   |   |       goods-modify.js
        |   |   |   |   |       goods-write.js
        |   |   |   |   |       
        |   |   |   |   +---member
        |   |   |   |   |       member_login.js
        |   |   |   |   |       member_register.js
        |   |   |   |   |       
        |   |   |   |   +---mypage
        |   |   |   |   |       mypage-aside.js
        |   |   |   |   |       mypage-block.js
        |   |   |   |   |       mypage-edit.js
        |   |   |   |   |       mypage-main.js
        |   |   |   |   |       mypage-mylist.js
        |   |   |   |   |       mypage-withdraw.js
        |   |   |   |   |       
        |   |   |   |   \---trx
        |   |   |   |           rating.js
        |   |   |   |           
        |   |   |   \---libs
        |   |   |           c3.css
        |   |   |           c3.min.css
        |   |   |           c3.min.js
        |   |   |           filepond-plugin-file-encode.js
        |   |   |           filepond-plugin-file-metadata.js
        |   |   |           filepond-plugin-image-crop.js
        |   |   |           filepond-plugin-image-preview.css
        |   |   |           filepond-plugin-image-preview.js
        |   |   |           filepond.css
        |   |   |           filepond.js
        |   |   |           sweetalert.min.css
        |   |   |           sweetalert.min.js
        |   |   |           
        |   |   \---templates
        |   |       |   frags.html
        |   |       |   index.html
        |   |       |   libs.html
        |   |       |   mail.html
        |   |       |   main.html
        |   |       |   
        |   |       +---admin
        |   |       |       admin_asideBar.html
        |   |       |       admin_goods.html
        |   |       |       admin_member.html
        |   |       |       stats_member.html
        |   |       |       
        |   |       +---chat
        |   |       |       chatbox.html
        |   |       |       
        |   |       +---err
        |   |       |       401.html
        |   |       |       403.html
        |   |       |       404.html
        |   |       |       500.html
        |   |       |       deniedpage.html
        |   |       |       
        |   |       +---goods
        |   |       |       goods-detail.html
        |   |       |       goods-libs.html
        |   |       |       goods-modify.html
        |   |       |       goods-write.html
        |   |       |       
        |   |       +---member
        |   |       |       register.html
        |   |       |       register_connect.html
        |   |       |       
        |   |       +---mypage
        |   |       |   |   asideBar.html
        |   |       |   |   blocklist.html
        |   |       |   |   edit.html
        |   |       |   |   location.html
        |   |       |   |   main.html
        |   |       |   |   mylist.html
        |   |       |   |   withdraw.html
        |   |       |   |   
        |   |       |   \---mylist
        |   |       |           chat.html
        |   |       |           comp.html
        |   |       |           trade.html
        |   |       |           wish.html
        |   |       |           
        |   |       \---trx
        |   |               rating.html
        |   |               
        |   \---webapp
        \---test
            \---java
                |   DataSourceTest.java
                |   
                \---multi
                    \---fclass
                        \---iMint
                            |   ImintApplicationTest.java
                            |   
                            +---admin
                            |   +---controller
                            |   |       AdminCotrollerTest.java
                            |   |       
                            |   \---dto
                            |           AdminDTOTest.java
                            |           
                            +---block
                            |   +---controller
                            |   |       BlockCotrollerTest.java
                            |   |       
                            |   \---dto
                            |           BlockDTOTest.java
                            |           
                            +---goods
                            |   \---dto
                            |           GoodsDTOTest.java
                            |           GoodsImagesDTOTest.java
                            |           
                            +---member
                            |   \---dto
                            |           MemberDTOTest.java
                            |           
                            \---security
                                \---controller
                                        IndexControllerTest.java
    ```
  </div>
</details>
