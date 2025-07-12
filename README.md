# 5-sprint-mission

## 요구사항

---

### 기본 요구사항

### 프로젝트 초기화

[ ] IntelliJ를 통해 다음의 조건으로 Java 프로젝트를 생성합니다.

[ ]  IntelliJ에서 제공하는 프로젝트 템플릿 중 Java를 선택합니다.

[ ]  프로젝트의 경로는 스프린트 미션 리포지토리의 경로와 같게 설정합니다.

예를 들어 스프린트 미션 리포지토리의 경로가 /some/path/1-sprint-mission 이라면:

Name은 1-sprint-mission

Location은 /some/path

으로 설정합니다.

[ ]  Create Git Repository 옵션은 체크하지 않습니다.

[ ]  Build system은 Gradle을 사용합니다. Gradle DSL은 Groovy를 사용합니다.

[ ]  JDK 17을 선택합니다.

[ ]  GroupId는 com.sprint.mission로 설정합니다.

[ ]  ArtifactId는 수정하지 않습니다.

[ ]  .gitignore에 IntelliJ와 관련된 파일이 형상관리 되지 않도록 .idea디렉토리를 추가합니다.

...

.idea

...

### 도메인 모델링

[ ] 디스코드 서비스를 활용해보면서 각 도메인 모델에 필요한 정보를 도출하고, Java Class로 구현하세요.

[ ] 패키지명: com.sprint.mission.discodeit.entity

[ ] 도메인 모델 정의

[ ] 공통

[ ] id: 객체를 식별하기 위한 id로 UUID 타입으로 선언합니다.

[ ] createdAt, updatedAt: 각각 객체의 생성, 수정 시간을 유닉스 타임스탬프로 나타내기 위한 필드로 Long 타입으로 선언합니다.

[ ] User

[ ] Channel

[ ] Message

[ ] 생성자

[ ] id는 생성자에서 초기화하세요.

[ ] createdAt는 생성자에서 초기화하세요.

[ ] id, createdAt, updatedAt을 제외한 필드는 생성자의 파라미터를 통해 초기화하세요.

[ ] 메소드

[ ] 각 필드를 반환하는 Getter 함수를 정의하세요.

[ ] 필드를 수정하는 update 함수를 정의하세요.

### 서비스 설계 및 구현

[ ] 도메인 모델 별 CRUD(생성, 읽기, 모두 읽기, 수정, 삭제) 기능을 인터페이스로 선언하세요.

[ ] 인터페이스 패키지명: com.sprint.mission.discodeit.service

[ ] 인터페이스 네이밍 규칙: [도메인 모델 이름]Service

[ ] 다음의 조건을 만족하는 서비스 인터페이스의 구현체를 작성하세요.

[ ] 클래스 패키지명: com.sprint.mission.discodeit.service.jcf

[ ] 클래스 네이밍 규칙: JCF[인터페이스 이름]

[ ] Java Collections Framework를 활용하여 데이터를 저장할 수 있는 필드(data)를 final로 선언하고 생성자에서 초기화하세요.

[ ] data 필드를 활용해 생성, 조회, 수정, 삭제하는 메소드를 구현하세요.

### 메인 클래스 구현

[ ] 메인 메소드가 선언된 JavaApplication 클래스를 선언하고, 도메인 별 서비스 구현체를 테스트해보세요.

[ ] 등록

[ ] 조회(단건, 다건)

[ ] 수정

[ ] 수정된 데이터 조회

[ ] 삭제

[ ] 조회를 통해 삭제되었는지 확인
