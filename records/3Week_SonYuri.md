## Title: [3Week] 손유리

### 미션 요구사항 분석 & 체크리스트

---

- [x] 필수 미션 - 일정 시간 동안은 호감표시 수정 및 삭제를 할 수 없게끔 체크하는 로직 추가
  - [x] canCancel(), canModifyLike() 메서드에 로직 추가
  - [x] 수정 및 삭제가 가능하기까지 남은 시간을 쉽게 확인할 수 있도록 LikeablePerson 클래스의 getModifyUnlockDateRemainStrHuman() 메서드를 제대로 구현
- [x] 선택 미션 - 알림 기능 구현
  - [x] 호감표시를 새로 했을 경우와 수정했을 경우를 나눠서 Notification을 생성하는 메서드 구현(NotificationService.java)
  - [x] 호감표시 및 수정할 경우 실행되는 InstaMemberService 클래스의 whenAfterModifyAttractiveType(), whenAfterLike() 메서드에 Notification을 생성하도록 적용
  - [x] 알림을 읽으면 readDate가 현재 날짜로 변경되는 로직 구현(Notification 엔티티의 updateReadDate() 메서드 생성 후 서비스 단, 컨트롤러 단에 차례로 적용)
  - [x] 알림 목록 화면(/usr/notification/list)에서 생성된 알림들을 확인할 수 있도록 list.html 제대로 구현
  - [x] 알림 목록 화면에 사용할 메서드 생성 및 적용(Notification 엔티티의 getOldAttractiveTypeDisplayName(), getNewAttractiveTypeDisplayName())
  - [x] 로컬 서버에서의 테스트를 위해 initData 추가(NotProd.java)
  - [x] 로컬 서버에서 테스트


### 3주차 미션 요약

---

**[접근 방법]**

< 필수 미션 >
- LikeablePerson 클래스의 isModifyUnlocked() 메서드를 통해 쿨타임이 지났는지 여부를 바로 확인 가능했다.
- 호감표시 수정 시 modifyUnlockDate 값을 변경하는 로직은 이미 updateAttractionTypeCode() 메서드에 구현되어 있었다.
- 결과적으로 canCancel(), canModifyLike() 메서드에 호감표시 수정 및 삭제가 가능한지 확인하는 로직을 추가하기만 하면 되었다.
- getModifyUnlockDateRemainStrHuman() 메서드를 제대로 구현하여 호감표시 수정 및 삭제가 가능하기까지 남은 시간을 쉽게 확인할 수 있도록 했다.
- 앞서 구현한 getModifyUnlockDateRemainStrHuman()를 canCancel(), canModifyLike() 메서드에도 적용했다.

< 선택 미션 >
- 알림 기능을 구현하기 위해 먼저 알림을 생성하는 메서드를 서비스 단에 구현했다.
- 호감표시를 새로 했을 경우와 수정했을 경우의 typeCode가 다르고, 수정 시에는 변경 전 호감사유와 변경 후 호감사유 두 가지가 모두 필요하기 때문에 알림 생성 메서드 또한 나누어 만들었다.
- 호감표시 및 수정할 경우 실행되는 InstaMemberService 클래스의 whenAfterModifyAttractiveType(), whenAfterLike() 메서드에 알림 생성 메서드를 적용했다.
- 알림을 읽으면 readDate가 현재 날짜로 변경되는 메서드를 Notification 엔티티에 구현하고 서비스 단, 컨트롤러 단에 적용했다.
- 컨트롤러 단의 showList() 에 readDate를 변경하는 로직을 추가하였으므로 알림 목록 페이지(/usr/notification/list)에 접근하면 바로 readDate가 변경된다.
- 알림 목록 화면을 재구성하고 알림의 typeCode가 Like인지 ModifyAttractiveType 인지에 따라 다른 텍스트가 보여질 수 있도록 구현했다.
- NotProd.java에 나의 인스타그램 아이디로 호감 표시를 한 더미 데이터를 몇 개 생성하고, 로컬 서버에서 알림 화면이 제대로 구성되어 있는지 테스트했다.



**[특이사항]**

- 필수 미션은 생각보다 쉽게 끝났지만 선택 미션인 알림 기능을 구현하면서, 필요한 기능들을 어떻게 구현하고 적용해야 할 지... 예를 들면 구체적으로 어떤 메서드를 만들어야 할 지, 그 메서드는 어느 클래스에 구현되어야 적절한지 등에 대해 생각보다 고민을 많이 한 것 같다. 추후에 간단하게나마 비슷한 서비스를 만들어보면서 이에 대한 감을 더 익혀야 할 것 같다.
- 알림 목록 화면에서 '3분 전'과 같이 알림이 지금 시간으로부터 얼마 전에 생성되었는지 확인하는 내용은 어떻게 구현해야 할 지 감이 안 잡혀 일단은 알림 생성 날짜만 보이도록 했다.