# 크리스마스 프로모션

## 🛠'12월 이벤트 플래너' 개발 요청 사항

- [x] 인사 출력 - "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."


- [x] 날짜 입력받기 - 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
    
  - **[예외처리]**
      - [x] 방문할 날짜가 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.


- [x] 메뉴와 개수 입력받기 - 주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  - **[예외처리]**
    - [x] 고객이 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - [x] 메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - [x] 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - [x] 중복 메뉴를 입력한 경우 (e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - [x] 음료만 주문 시, 주문할 수 없습니다.
    - [x] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
    - (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)


*********

- [x] 주문메뉴 출력
    - 주문 메뉴의 출력 순서는 자유롭게 출력해 주세요.


- [x] 할인 전 총주문 금액
    - <애피타이저>
        - 양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
    - <메인>
        - 티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
    - <디저트>
        - 초코케이크(15,000), 아이스크림(5,000)
    - <음료>
        - 제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)


- [x] 증정 메뉴
    - 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
    - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 보여 주세요.


- [x] 혜택 내역
    1. [x] 크리스마스 디데이 할인
        - 이벤트 기간: 2023.12.1 ~ 2023.12.25
        - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
        - 총주문 금액에서 해당 금액만큼 할인
          (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
    2. [x] 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    3. [x] 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    4. [x] 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    5. [x] 증정 이벤트: 증정메뉴 가격만큼 할인

    - 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용 
    - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    - 고객에게 적용된 이벤트 내역만 보여 주세요.
    - 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 보여 주세요.
    - 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력해주세요.


- [x] 총혜택 금액
    - 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격


- [x] 할인 후 예상 결제 금액
    - 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액


- [x] 12월 이벤트 배지
    - 총혜택 금액에 따라 이벤트 배지의 이름을 다르게 보여 주세요.
        - 5천 원 이상: 별
        - 1만 원 이상: 트리
        - 2만 원 이상: 산타
    - 이벤트 배지가 부여되지 않는 경우, "없음"으로 보여 주세요.
    - 적용된 이벤트가 하나도 없는 경우는 아래 예시를 참고해 주세요.