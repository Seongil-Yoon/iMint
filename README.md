# 🌿아이민트

🔗 배포 URL


## ****🗂****개요

- 멀티캠퍼스 지능형 웹개발 풀스택 과정의 팀 프로젝트로 진행한 아이민트입니다.
- 아이민트 서비스는 아이들의 안전에 초점을 맞춘 지역기반 중고 거래 웹사이트입니다.
- 아이민트에서는 보호자와 연동이 인증된 아이들만 상품 거래에 참여할 수 있으며, 보호자는 연동된 모든 아이들의 거래 현황(상품별 거래 상태 및 채팅내역)을 실시간으로 확인할 수 있습니다.

## ****👥****팀원 구성

1. [윤성일](https://github.com/Seongil-Yoon)
    - 팀장
    
    **대표: 메인페이지, 상품, 배포 및 서버구축,  팀장**
    
    메인페이지 설계 및 구현(상품목록 무한스크롤), 음성인식 상품검색
    
    상품 조회 / 등록 / 수정 / 삭제 구현, 파일다중업로드
    
    HTTPS/SSL, HTTP 상태코드 예외처리
    
2. [양정민](https://github.com/overult01)
    
    **대표: SNS 로그인, 회원서비스, 관리자, PPT**
    
    SNS 로그인(OAuth2.0) / 회원가입(동네설정, 보호자 연동) / 정보 수정 / 탈퇴 설계 및 구현
    
    Spring Security를 활용한 권한 인가
    
    관리자 페이지 회원 목록 조회, 회원 통계 설계 및 구현
    
    로그인 페이지 CSS 구현
    
    PPT 제작
    
3. 이강산
    
    **대표: 마이페이지(백엔드), 관심등록 / 예약 / 판매, 채팅**
    
    마이페이지 서비스 설계 및 구현
    
    관심등록 / 예약 / 판매 서비스 설계 및 구현
    
    채팅 서비스 설계 및 구현
    
4. [전해연](https://github.com/hailey-hy)
    
    **대표: 마이페이지(프론트엔드), 서비스내 공통 CSS, JS**
    
    구현된 마이페이지 서비스를 사용 및 수정하여 사용자에게 표시
    
    마이페이지 CSS, JS 구현
    
    전체 서비스에서 사용되는 화면 CSS, JS 구현

---

## ****♟****정보 구조도

![Untitled](https://user-images.githubusercontent.com/93465128/166220866-fa827937-b32a-48d4-a677-2182bce80bbe.png)

## ****🗺****ER 다이어그램

![Untitled](https://user-images.githubusercontent.com/93465128/166220903-db7ca9d7-4714-4fe9-a86a-75687b896fd8.png)

## ****🧩****시스템 아키텍쳐

## 디렉토리 구조

```java
iMint
|
|  .gitignore
|  pom.xml
|
├─src
    └─main
        ├─java
        │  │
        │  └─multi
        │      └─fclass
        │          └─iMint
        │              │  ConnectorConfig.java
        │              │  ImintApplication.java
        │              │  MyWebConfig.java
        │              │  ServletInitializer.java
        │              │
        │              ├─admin
        │              │  ├─controller
        │              │  │      AdminCotroller.java
        │              │  │
        │              │  ├─dao
        │              │  │      IAdminDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      AdminDTO.java
        │              │  │
        │              │  └─service
        │              │          AdminServiceImpl.java
        │              │          IAdminService.java
        │              │
        │              ├─ai
        │              │  ├─controller
        │              │  │      AiController.java
        │              │  │
        │              │  └─service
        │              │          INaverService.java
        │              │          sttServiceImpl.java
        │              │
        │              ├─block
        │              │  ├─controller
        │              │  │      BlockCotroller.java
        │              │  │
        │              │  ├─dao
        │              │  │      IBlockDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      BlockDTO.java
        │              │  │
        │              │  └─service
        │              │          BlockServiceImpl.java
        │              │          IBlockService.java
        │              │
        │              ├─chat
        │              │  ├─config
        │              │  │      ChatInterceptor.java
        │              │  │      ChatPrincipal.java
        │              │  │      WebSocketConfig.java
        │              │  │
        │              │  ├─controller
        │              │  │      ChatController.java
        │              │  │
        │              │  ├─dao
        │              │  │      IChatDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      ChatCheckDTO.java
        │              │  │      ChatMessageDTO.java
        │              │  │
        │              │  └─service
        │              │          ChatServiceImpl.java
        │              │          IChatService.java
        │              │
        │              ├─common
        │              │  ├─code
        │              │  │      ErrorCode.java
        │              │  │
        │              │  ├─exception
        │              │  │  │  HandlableException.java
        │              │  │  │
        │              │  │  └─hadler
        │              │  │          ExceptionAdvice.java
        │              │  │          ForbiddenException.java
        │              │  │          InternalServerErrorException.java
        │              │  │          NotFoundException.java
        │              │  │          UnauthorizedException.java
        │              │  │
        │              │  ├─interceptor
        │              │  └─service
        │              │          FileServiceImpl.java
        │              │          IFileService.java
        │              │          IUtilService.java
        │              │          UtilServiceImpl.java
        │              │
        │              ├─goods
        │              │  ├─controller
        │              │  │      GoodsController.java
        │              │  │
        │              │  ├─dao
        │              │  │      IGoodsDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      GoodsDTO.java
        │              │  │      GoodsImagesDTO.java
        │              │  │
        │              │  └─service
        │              │          GoodsServiceImpl.java
        │              │          IGoodsService.java
        │              │
        │              ├─main
        │              │  ├─controller
        │              │  │      MainController.java
        │              │  │
        │              │  └─service
        │              │          IMainService.java
        │              │          MainServiceImpl.java
        │              │
        │              ├─member
        │              │  ├─controller
        │              │  │      MemberCotroller.java
        │              │  │
        │              │  ├─dao
        │              │  │      IMemberDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      MemberDTO.java
        │              │  │      Role.java
        │              │  │      SessionMember.java
        │              │  │      ThumbnailDTO.java
        │              │  │
        │              │  └─service
        │              │          IMemberService.java
        │              │          MemberServiceImpl.java
        │              │
        │              ├─mypage
        │              │  ├─controller
        │              │  │      MypageCotroller.java
        │              │  │
        │              │  ├─dao
        │              │  │      IMypageDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      MypageChatroomDTO.java
        │              │  │      MypageChildDTO.java
        │              │  │      MypageDTO.java
        │              │  │
        │              │  └─service
        │              │          IMypageService.java
        │              │          MypageServiceImpl.java
        │              │
        │              ├─security
        │              │  │  GenerateCertCharacter.java
        │              │  │
        │              │  ├─auth
        │              │  │  ├─config
        │              │  │  │      OAuthAttributes.java
        │              │  │  │      SecurityConfig.java
        │              │  │  │
        │              │  │  └─provider
        │              │  │          KakaoUserInfo.java
        │              │  │          NaverUserInfo.java
        │              │  │          OAuth2UserInfo.java
        │              │  │
        │              │  ├─controller
        │              │  │      IndexController.java
        │              │  │
        │              │  ├─dao
        │              │  │      ISecurityDAO.java
        │              │  │
        │              │  ├─parsing
        │              │  │  ├─mbid
        │              │  │  │      ParseMbId.java
        │              │  │  │
        │              │  │  └─role
        │              │  │          ParseMbRole.java
        │              │  │
        │              │  └─service
        │              │          CustomOAuth2UserService.java
        │              │
        │              ├─transaction
        │              │  ├─controller
        │              │  │      TransactionController.java
        │              │  │
        │              │  ├─dao
        │              │  │      ITransactionDAO.java
        │              │  │
        │              │  ├─dto
        │              │  │      TransactionChatroomDTO.java
        │              │  │      TransactionCheckDTO.java
        │              │  │
        │              │  └─service
        │              │          ITransactionService.java
        │              │          TransactionServiceImpl.java
        │              │
        │              └─wishlist
        │                  ├─controller
        │                  │      WishlistController.java
        │                  │
        │                  ├─dao
        │                  │      IWishlistDAO.java
        │                  │
        │                  ├─dto
        │                  │      WishlistDTO.java
        │                  │
        │                  └─service
        │                          IWishlistService.java
        │                          WishlistServiceImpl.java
        │
        ├─resources
        │  │  application.properties
        │  │  keystore.jks
        │  │
        │  ├─mybatis
        │  │  │  mybatis-config.xml
        │  │  │
        │  │  └─mappers
        │  │          admin-mapper.xml
        │  │          block-mapper.xml
        │  │          chat-mapper.xml
        │  │          goods-mapper.xml
        │  │          member-mapper.xml
        │  │          mypage-mapper.xml
        │  │          security-mapper.xml
        │  │          transaction-mapper.xml
        │  │          wishlist-mapper.xml
        │  │
        │  └─static
        │      ├─css
        │      │  │  common_style.css
        │      │  │  error.css
        │      │  │  footer.css
        │      │  │  header-search.css
        │      │  │  header.css
        │      │  │  main.css
        │      │  │
        │      │  ├─chat
        │      │  │      chatbox.css
        │      │  │
        │      │  ├─goods
        │      │  │      carousel.css
        │      │  │      goods-common-style.css
        │      │  │      goods-detail.css
        │      │  │      goods-modify.css
        │      │  │      goods-write.css
        │      │  │
        │      │  ├─member
        │      │  │      admin_member.css
        │      │  │      member_basic.css
        │      │  │      member_login.css
        │      │  │      member_register.css
        │      │  │      member_register_connect.css
        │      │  │      member_register_new.css
        │      │  │
        │      │  └─mypage
        │      │          mylist-content.css
        │      │          mypage_asidebar.css
        │      │          mypage_block.css
        │      │          mypage_edit.css
        │      │          mypage_location.css
        │      │          mypage_main.css
        │      │          mypage_mylist.css
        │      │          mypage_withdraw.css
        │      │
        │      ├─images
        │      │      401.png
        │      │      403.png
        │      │      404.jpg
        │      │      500.jpg
        │      │
        │      ├─js
        │      │  │  footer.js
        │      │  │  header-search.js
        │      │  │  header.js
        │      │  │  main.js
        │      │  │
        │      │  ├─admin
        │      │  │      admin_member.js
        │      │  │      admin_stats.js
        │      │  │
        │      │  ├─chat
        │      │  │      chatbox.js
        │      │  │
        │      │  ├─goods
        │      │  │      carousel.js
        │      │  │      goods-detail.js
        │      │  │      goods-modify.js
        │      │  │      goods-write.js
        │      │  │
        │      │  ├─member
        │      │  │      member_login.js
        │      │  │      member_register.js
        │      │  │
        │      │  └─mypage
        │      │          mypage-aside.js
        │      │          mypage-edit.js
        │      │          mypage-mylist.js
        │      │          mypage-withdraw-child.js
        │      │          mypage-withdraw.js
        │      │
        │      └─libs
        │              bootstrap.bundle.min.js
        │              c3.css
        │              c3.min.css
        │              c3.min.js
        │              filepond-plugin-file-encode.js
        │              filepond-plugin-file-metadata.js
        │              filepond-plugin-image-crop.js
        │              filepond-plugin-image-preview.css
        │              filepond-plugin-image-preview.js
        │              filepond.css
        │              filepond.js
        │              sweetalert.min.css
        │              sweetalert.min.js
        │
        └─webapp
            └─WEB-INF
                └─views
                    │  index.jsp
                    │  main.jsp
                    │
                    ├─admin
                    │      admin_asideBar.jsp
                    │      admin_member.jsp
                    │      stats_member.jsp
                    │
                    ├─chat
                    │      chatbox.jsp
                    │
                    ├─err
                    │      401.jsp
                    │      403.jsp
                    │      404.jsp
                    │      500.jsp
                    │      deniedpage.jsp
                    │
                    ├─goods
                    │      goods-detail.jsp
                    │      goods-libsScript.jsp
                    │      goods-libsStyles.jsp
                    │      goods-modify.jsp
                    │      goods-write.jsp
                    │
                    ├─include
                    │      footer.jsp
                    │      header.jsp
                    │
                    ├─libs
                    │      libsScript.jsp
                    │      libsStyles.jsp
                    │
                    └─member
                        │  login.jsp
                        │  register.jsp
                        │  register_connect.jsp
                        │
                        ├─baby-mypage
                        │      baby-asideBar.jsp
                        │      baby-blocklist.jsp
                        │      baby-edit.jsp
                        │      baby-main.jsp
                        │      baby-myList.jsp
                        │      baby-withdraw.jsp
                        │      mylist-content-complete.jsp
                        │      mylist-content-selling.jsp
                        │      mylist-content-wish-buy.jsp
                        │
                        └─guard-mypage
                                guard-asideBar.jsp
                                guard-blocklist.jsp
                                guard-edit.jsp
                                guard-location.jsp
                                guard-main.jsp
                                guard-mylist.jsp
                                guard-withdraw.jsp
                                mylist-content-complete.jsp
                                mylist-content-selling.jsp
                                mylist-content-wish-buy.jsp
                                mylist-content.jsp
```
