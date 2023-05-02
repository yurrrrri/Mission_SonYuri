## Title: [3Week] 손유리

### 미션 요구사항 분석 & 체크리스트

---

- [x] 필수 미션 - 일정 시간 동안은 호감표시 수정 및 삭제를 할 수 없게끔 체크하는 로직 추가
  - [x] canCancel(), canModifyLike() 메서드에 로직 추가
  - [x] 수정 및 삭제가 가능하기까지 남은 시간을 쉽게 확인할 수 있도록 LikeablePerson 클래스의 getModifyUnlockDateRemainStrHuman() 메서드를 제대로 구현
- [] 선택 미션 - 알림 기능 구현



### 3주차 미션 요약

---

**[접근 방법]**

< 필수 미션 >
- LikeablePerson 클래스의 isModifyUnlocked() 메서드를 통해 쿨타임이 지났는지 여부를 바로 확인 가능했다.
- 호감표시 수정 시 modifyUnlockDate 값을 변경하는 로직은 이미 updateAttractionTypeCode() 메서드에 구현되어 있었다.
- 결과적으로 canCancel(), canModifyLike() 메서드에 호감표시 수정 및 삭제가 가능한지 확인하는 로직을 추가하기만 하면 되었다.
- getModifyUnlockDateRemainStrHuman() 메서드를 제대로 구현하여 호감표시 수정 및 삭제가 가능하기까지 남은 시간을 쉽게 확인할 수 있도록 했다.
- 앞서 구현한 getModifyUnlockDateRemainStrHuman()를 canCancel(), canModifyLike() 메서드에도 적용했다.



**[특이사항]**

-