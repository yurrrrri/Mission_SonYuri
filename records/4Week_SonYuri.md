## Title: [4Week] 손유리

### 미션 요구사항 분석 & 체크리스트

---

- [x] 필수 미션 : 내가 받은 호감리스트(/usr/likeablePerson/toList)에서 성별 필터링 기능 구현
  - [x] 파라미터 값을 받을 수 있게 @RequestParam 작성
  - [x] 파라미터 값을 인자로 받는 getClassifiedLikeablePeople() 메서드 구현
  - [x] gender 파라미터 값이 들어올 경우 stream().filter()로 필터링한 호감 목록을 반환
  - [x] 반환된 호감 목록을 html 단으로 넘겨준다.
- [x] 선택 미션 - 내가 받은 호감리스트(/usr/likeablePerson/toList)에서 호감사유 필터링 기능 구현
  - [x] 성별 필터링 기능 구현과 같은 흐름
  - [x] attractiveTypeCode 파라미터 값이 들어올 경우 stream().filter()로 필터링한 호감 목록을 반환
  - [x] 반환된 호감 목록을 html 단으로 넘겨준다.


### 4주차 미션 요약

---

**[접근 방법]**

- 호감리스트에서 필터링을 위한 값을 선택하면 파라미터에 값이 넘어간다. 그 값을 받을 수 있도록 @RequestParam 어노테이션을 붙인 매개변수를 showToList() 메서드에 추가했다.
- 파라미터 값이 들어오지 않을 경우 gender와 attractiveTypeCode는 "", sortCode는 "1"로 초기화된다.
- 파라미터 값을 인자로 받는 getClassifiedLikeablePeople() 메서드를 생성, 해당 메서드에서 호감 목록을 필터링한다.
- 필터링한 목록을 반환하고, 받환받은 값은 Model 객체를 통해 html 단으로 넘어가게 된다.



**[특이사항]**

- 처음 구현할 때는 Repository 단에서 findBy ~ 메서드를 생성하여 구현했는데, 모든 조건을 맞추기 위해서 메서드를 여러 개 추가해야 하는 번거로움이 있어 stream()을 이용하는 방법으로 바꾸었다.
- 확실한 것은 아니지만 findBy ~ 메서드를 생성하여 구현하면 stream()보다 효율은 더 좋지 않을까 싶다.