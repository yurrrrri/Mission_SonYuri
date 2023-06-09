## Title: [1Week] 손유리

### 미션 요구사항 분석 & 체크리스트

---

- [x] 호감 상대 삭제
  - [x] 호감 상대 삭제 테스트 코드 작성
  - [x] 호감 상대 삭제 메서드 작성(controller 단에서)
  - [x] (필요한 경우) 삭제 메서드에서 사용될 service 단의 메서드 작성
  - [x] (필요한 경우) repository 파일에 메서드 추가
  - [x] 테스트 코드 실행
  - [x] 로컬 서버 실행, 정상적으로 호감 상대 삭제 및 메시지가 뜨는지 확인
- [x] 구글 로그인 구현
  - [x] 구글 클라우드 홈페이지에서 Oauth 클라이언트 ID 발급
  - [x] yml 파일에 구글 로그인 시 필요한 필드 작성(git에 올라가지 않도록 파일 따로 생성)
  - [x] 로컬 서버 실행, 구글 로그인 및 DB에 정상적으로 저장되는지 확인


### 1주차 미션 요약

---

**[접근 방법]**

- 호감 상대 삭제 기능 구현 시 점프 투 스프링부트의 질문답변 삭제 메서드를 참고했다.
- 호감 상대 삭제를 할 때 다른 기능들과 마찬가지로 확인 메시지 창이 뜨도록 했다.

- 구글 로그인 기능 구현 시 카카오 로그인이 이미 잘 실행이 되므로, 별다른 코드 변경 없이 yml 파일에 클라이언트 ID 등을 추가하기만 하면 정상적으로 실행될 것이라고 판단했다.
- 검색해서 찾아본 블로그 글들은 엔티티 생성을 새로 하는 등 나의 진행 상황과는 맞지 않는 내용이 많아, 챗GPT를 이용하여 yml 파일에 작성해야 할 내용을 얻었다.
- 클라이언트 ID, 비밀번호는 깃에 올라가면 안 되므로 따로 yml 파일을 생성하고, gitignore에 등록하였다.

**[특이사항]**

- 호감 상대를 삭제할 때 삭제 권한이 있는지 판별하는 기준을 InstaMember 객체로 설정했는데, 좀더 알맞은 기준이 뭘지 더 고민해보아야 할 것 같다. => 리팩토링을 하면서 instaMember.getId()로 수정했다.