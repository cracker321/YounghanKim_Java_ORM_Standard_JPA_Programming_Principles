package hellojpa;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity //여기선 기본적으로 '@Entity(name = "Member")'이고, 여기서 '(name = "Member")'는 생략된 상태임
//@Table(name = "MBR") //- 만약, 회사 내부 규정 등의 이유로 아래 '자바 객체 Member'를
                       //이미 존재하는 'DB 속 테이블 MBR'에 매핑해야 한다면 이렇게 작성함.
                       //그러면 이제, 아래 'Mebmer 객체'는 DB에서 '테이블 Member'에 매핑되는 것이 아니고,
                       //'테이블 MBR'에 매핑됨.
                       //- 따라서, 쿼리도 이제 'INSERT INTO MBR VALUES..'로 DB에 날려지게 된다.

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //- 'id': 'DB 테이블 Member의 컬럼 id'로 매핑됨.
                     //- '@Id': 'DB 테이블 Member'의 'PK'는 '여기 자바 객체의 id'임을 말해주기 위해 '@Id'를 붙인 것임.
                     //- '@GeneratedValue': '기본 키' '자동 생성'
                     //- 'GenerationType.IDENTITY': '기본키 생성'을 'DB에 위임'.
                     //                             기본키가 1, 2, 3, ... 자동적으로 생성, 증가..
                     //                             주로 MySQL, PostgreSQL 등에서 사용
                     //- 'GenerationType.SEQUENCE':

    //@Column(name = "username") //만약, DB에 이미 '컬럼 username'이 있다면, 아래 '필드 nanme'은 DB에서
                                 //'컬럼 name'으로 입력되는 것이 아니라, '컬럼 username'으로 입력된다!
    @Column(name = "name", insertable = true, updatable = true, nullable = false, length = 10) //'컬럼' 매핑
    private String username; //- 'name = "name"': 현재 '이 자바 엔티티 객체의 필드명은 username'이지만, 이 어노테이션에 의해
                             //이미 존재하는 'DB 테이블 Member의 컬럼 name'과 매핑시키고 싶으면, 어노테이션 저렇게 하면 됨
                             //- 'insertable = true(기본값)': '자바 객체의 이 필드'를 수정했을 때,
                             //                              'DB 테이블의 이 컬럼'에 'INSERT 쿼리'를 보내서 수정을 해라 라는 의미
                             //- 'updatable = true(기본값)': '자바 객체의 이 필드'를 수정했을 때,
                             //                             'DB 테이블의 이 칼렘'에 'UPDATE 쿼리'를 보내서 수정을 해라 라는 의미
                             //- 'nullabe = false': '기본값'은 'true'임.
                             //- 'length = ': 문자 길이 제약조건. 'String 타입'에서만 사용함.
                             //               만약, 'length = 10'이면 DB에서는 'varchar(10)'이 됨.

    @Column(precision = 20) //'컬럼' 매핑
    private BigDecimal age; //- 'precision': 아주 큰 숫자 또는 소수점 자릿수 사용할 때

    @Enumerated(EnumType.STRING)  //'enum 타입' 매핑
    private RoleType roleType; //- '자바앱'에는 'enum 타입'이 있으나, 'DB'에는 'enum 타입'이 없음.
                               //그럴 때, 바로 이 '@Enumertaed'를 쓰면 된다!
                               //- 'enum 타입'을 매핑할 때는, 그냥 무조건 '@Enumerated(EnumType.STRING)' 딱 이렇게만
                               //쓴다고 보면 된다. 절대 'EnumType.ORDINAL' 쓰면 안된다!

    @Temporal(TemporalType.TIMESTAMP) //'날짜 타입' 매핑
    private Date createdDate; //- 그러나, 요즘에는 그냥 'private LocalDate testLocalDate' 또는
                              //'private LocalDateTime testLocalDateTime'으로 쓰면, 자바 8 버전 이상일 경우,
                              //최신 하이버네이트에서 알아서 DB에 각각 'Date 타입'과'TimeStamp 타입'으로 매핑시켜준다!
                              //따라서, '@Temporal'은 요즘에는 거의 사용하지 X.
                              //- 'TemporalType'에는 총 3가지 타입이 있음.
                              //Date(날짜), Time(시간), TimeStamp(날짜와 시간 둘 다)
                              //- 'TemporalType' 위에 마우스 올려놓고 컨트롤 누르면, 그 안으로 들어가짐. 거기서 확인 가능
                              //여기서는 'TemporalType' 중에서 'TimeStamp'를 사용하고 있음


    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;


    //JPA는 기본적으로 '기본 생성자'가 하나 반드시 있어야 한다.
    //왜냐하면, JPA는 내부적으로 '리플렉션' 등을 써야 하기 때문에, '동적으로 객체를 생성'하여야 하고,
    //따라서, '기본 생성자'가 하나 있어야 한다.
    public Member() { //꼭 'public'이어야 할 필요는 없음

    }

    public Member(Long id, String username, BigDecimal age, RoleType roleType, Date createdDate, Date lastModifiedDate, String description) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



