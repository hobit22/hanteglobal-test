# 문제 1

## 주요 기능
- **계층 구조 지원**: 카테고리는 부모-자식 관계를 가질 수 있습니다.
- **게시판 번호 지정**: 최하위 카테고리에 게시판 번호를 부여할 수 있습니다.
- **검색 기능**: ID 또는 이름으로 카테고리를 검색할 수 있습니다.
- **JSON 직렬화**: 카테고리와 하위 카테고리를 JSON으로 변환 가능합니다.
  - 직렬화를 위해 gson 라이브러리를 사용하였습니다.

## 구성 요소
### 1. `Category` 클래스
- **역할**: 단일 카테고리를 표현합니다.
- **주요 메서드**:
    - `addChild(Category child)`: 하위 카테고리 추가.
    - `toJson()`: 카테고리를 JSON으로 변환.

### 2. `CategoryRepository` 클래스
- **역할**: 카테고리를 관리하고 검색 기능 제공.
- **주요 메서드**:
    - `addCategory(Category category)`: 카테고리와 모든 하위 카테고리 추가.
    - `searchById(int id)`: ID로 카테고리 검색.
    - `searchByName(String name)`: 이름으로 카테고리 검색.

### 3. `Main` 클래스
- **역할**: 카테고리 구조를 초기화하고, 검색 및 JSON 변환을 테스트합니다.

## 사용 방법

### 카테고리 설정
카테고리는 `setupCategories` 메서드에서 계층 구조로 정의됩니다.
모든 카테고리는 `CategoryRepository`에 추가되어 관리됩니다.

### 검색
- **ID로 검색**:
  ```java
  repository.searchById(4);
  ```
- **이름으로 검색**:
  ```java
  repository.searchByName("로제");
  ```

### JSON 변환
카테고리 및 하위 구조를 JSON으로 변환합니다.
```java
System.out.println(category.toJson());
```

## 예시 구조
```
남자 (ID: 1)
├── 엑소 (ID: 3)
│   ├── 첸 (ID: 6, Board: 1)
│   ├── 백현 (ID: 7, Board: 2)
│   └── 시우민 (ID: 8, Board: 3)
├── 방탄소년단 (ID: 4)
│   ├── 뷔 (ID: 9, Board: 4)
│   └── 익명게시판 (ID: 14, Board: 9)

여자 (ID: 2)
└── 블랙핑크 (ID: 5)
    ├── 로제 (ID: 10, Board: 5)
    └── 익명게시판 (ID: 14, Board: 9)
```

## 개선 사항
### DB를 사용하여 데이터 추가
1. **ID 자동 할당 및 중복 방지**:
    - 데이터베이스의 `AUTO_INCREMENT` 또는 유사한 기능을 사용하여 카테고리 ID를 자동으로 할당.
    - 기본 키와 유니크 제약 조건을 활용하여 중복 데이터 추가 방지.
    - **예시 SQL**:
      ```sql
      CREATE TABLE category (
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255) NOT NULL UNIQUE,
          parent_id INT,
          board_number INT,
          FOREIGN KEY (parent_id) REFERENCES category(id)
      );
      ```

### `CategoryType` 활용
1. **유형 관리**:
    - 카테고리를 유형별로 구분하기 위한 추가 필드 `categoryType` 추가.
    - **예시 필드 값**: `gender`, `소속사`, `그룹`, `멤버` 등.
      - **예시 SQL**:
        ```sql
        CREATE TABLE category (
            id LONG AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL UNIQUE,
            parent_id LONG,
            board_number INT,
            category_type VARCHAR(50),
            FOREIGN KEY (parent_id) REFERENCES category(id)
        );
        ```
        ```java
         public enum CategoryType {
             GENDER("Gender"),
             AGENCY("Agency"),
             GROUP("Group"),
             MEMBER("Member");
        
             private final String displayName;
        
             CategoryType(String displayName) {
             this.displayName = displayName;
             }
        
             public String getDisplayName() {
             return displayName;
             }
         }
        ```

### ID로 계층 구조 표현

1. ID 형식    
- 첫 번째 자리: 성별 (1: 남자, 2: 여자)
- 두 번째, 세 번째 자리: 소속사 (1: SM, 2: YG)
- 네 번째, 다섯 번째 자리: 그룹
- 여섯 번째, 일곱 번째 자리: 멤버

2. 예시:
- 10000000: 남자 카테고리
- 10100000: 남자 > SM
- 10101000: 남자 > SM > 엑소 > 공지사항
- 10101001: 남자 > SM > 엑소 > 첸

# 문제 2

## 주요 기능
- **조합 가짓수 계산**: 주어진 동전을 사용해 특정 합계를 만들 수 있는 방법의 수를 계산합니다.
- **조합 출력**: 합계를 만들기 위한 모든 가능한 동전 조합을 출력합니다.
- **정렬된 출력**: 조합은 항상 정렬된 형태(오름차순)로 출력됩니다.
- **효율적 구현**: 동적 프로그래밍(Dynamic Programming, DP)을 사용하여 성능을 최적화했습니다.

---

## 구성 요소

### 1. `Solution` 클래스
- **역할**: 동적 프로그래밍을 활용해 조합의 수를 계산하고, 가능한 모든 조합을 출력합니다.
- **주요 메서드**:
    - `printCombinations(int[] coins, int sum)`: 조합의 수와 방법을 출력합니다.
        - 동적 프로그래밍을 통해 조합을 계산하고 출력합니다.
        - 동전 배열은 자동으로 정렬되어 탐색합니다.

---

## 사용 방법

### 입력
- **동전 배열 (`coins`)**: 사용 가능한 동전 종류를 배열로 입력합니다. 예: `{1, 2, 3}`.
- **합계 (`sum`)**: 만들고자 하는 목표 합계를 입력합니다. 예: `4`.

### 출력
- **조합의 수**: 주어진 동전으로 합계를 만들 수 있는 방법의 수를 출력합니다.
- **조합 방법**: 각 조합을 리스트 형태로 출력합니다.


### **시간 복잡도 분석** 
- 동전의 종류 개수: N 
- 목표 합계: sum 
- dp[i] += dp[i - coin]
- dp의 시간복잡도 O(N * sum)
- 출력시 정렬 시간복잡도 O(N log N)
- 총 시간 복잡도 O(N * sum * N log N)
