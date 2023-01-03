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
    @Column(name = "MEMBER_ID")
    private Long id; //- 'private int id'로 쓰게 되면, 'int'에 '0'이 들어갈 수 있기 때문에 이렇게는 지양해야 하고,
                     //   대신, 'Integer'를 써도 되는데, 그보다는 'Long'을 쓰는 게 가장 좋다.
                     //   왜냐하면, 'Long'은 id가 10억이 넘어가는 것까지 가능하다. 사실상, 메모리 점유 차이도 Integer와 별로 안남.
                     //- 'id': 'DB 테이블 Member의 컬럼 id'로 매핑됨.
                     //- '@Id': 'DB 테이블 Member'의 'PK'는 '여기 자바 객체의 id'임을 말해주기 위해 '@Id'를 붙인 것임.
                     //- '@GeneratedValue': '기본 키' '자동 생성'

                     //1. 'GenerationType.IDENTITY': '기본키 생성'을 'DB에 위임'.
                     //                             기본키가 1, 2, 3, ... '자동적으로' 생성, 증가..
                     //                             주로 'MySQL', 'PostgreSQL' 등에서 사용
                     //2. 'GenerationType.SEQUENCE': DB에 있는 '시퀀스 오브젝트(시퀀스 객체)'를 통해서, 기본키를 생성시키는 것
                     //                              주로 '오라클', 'H2' 등에서 사용.
                     //                              그냥 'GenerationType.IDENTITY' 대신 그 자리에
                     //                              'GenerationType.SEQUENCE'만 써주고 실행해도 되고,
                     //                              'DB 테이블마다' 시퀀스를 따로 개별적으로 관리하고 싶으면,
                     //                              아래처럼 '@SequenceGenerator'로 매핑설정 해줘도 됨.
                     //                              - 시퀀스: '유일한 값'을 생성해 주는 오라클의 객체
                     //                                       시퀀스를 생성하면 기본키와 같이 순차적으로 증가하는 컬럼을 자동 생성 가능.
                     //                                       보통 pk값을 생성하기 위해 사용함.
                     //                                       시퀀스는 테이블과는 독립적으로 저장되고 생성됨.
                     //                                       따라서, 하나의 시퀀스를 여러 테이블에서 사용할 수 있음.
                     //@Entity
                     //@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
                     //                   sequenceName = "MEMBER_SEQ", //매핑할 DB의 시퀀스 이름
                     //                   initialValue = 1, allocationSize = 1)
                     //public class Member{
                     //    @Id
                     //    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                     //                    generator = "MEMBER_SEQ_GENERATOR")
                     //    private Long id;
                     //
                     //3. 'GenerationType.TABLE': DB 자체 내부에 아래 쿼리문으로 미리 '기본키만을 생성할 테이블'을 하나 만들어놓음
                     //                           create table MY_SEQUENCES(
                     //                                 sequence_name varchar(255) not null,
                     //                                 next_val bigint,
                     //                                 primary key( sequence_name )
                     //                                 )
                     //@Entity
                     //@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
                     //                table = "MY_SEQUENCES", //DB내부에 위 쿼리문으로 생성한 '기본키 생성 전담 테이블명'
                     //                pkColumnValue = "MEMBER_SEQ", //'기본키 생성 테이블의 기본키 생성 전담 컬럼'
                     //                allocationSize = 1)
                     //public class Member{
                     //    @Id
                     //    @GeneratedValue(strategy = GenerationType.TABLE,
                     //                    generator = "MEMBER_SEQ_GENERATOR")
                     //    private Long id;




    //@Column(name = "username") //만약, DB에 이미 '컬럼 username'이 있다면, 아래 '필드 nanme'은 DB에서
                                 //'컬럼 name'으로 입력되는 것이 아니라, '컬럼 username'으로 입력된다!
    @Column(name = "USERNAME", insertable = true, updatable = true, nullable = false, length = 10) //'컬럼' 매핑
    private String username; //- 'name = "name"': 현재 '이 자바 엔티티 객체의 필드명은 username'이지만, 이 어노테이션에 의해
                             //이미 존재하는 'DB 테이블 Member의 컬럼 name'과 매핑시키고 싶으면, 어노테이션 저렇게 하면 됨
                             //- 'insertable = true(기본값)': '자바 객체의 이 필드'를 수정했을 때,
                             //                              'DB 테이블의 이 컬럼'에 'INSERT 쿼리'를 보내서 수정을 해라 라는 의미
                             //- 'updatable = true(기본값)': '자바 객체의 이 필드'를 수정했을 때,
                             //                             'DB 테이블의 이 칼렘'에 'UPDATE 쿼리'를 보내서 수정을 해라 라는 의미
                             //- 'nullabe = false': '기본값'은 'true'임.
                             //- 'length = ': 문자 길이 제약조건. 'String 타입'에서만 사용함.
                             //               만약, 'length = 10'이면 DB에서는 'varchar(10)'이 됨.


    //'단방향 연관관계'강 16:30~
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //'자바의 참조 객체 Team team'과 'DB의 테이블의 외래키 TEAM_ID'를 연결해주는 '연관관계 매핑'
    private Team team; //- 전제: '회원'은 '하나의 팀'에만 소속될 수 있다.
                       //       '회원'과 '팀'은 'N대 1 관계'이다.
                       //- 'Many': 'Member'
                       //- 'One': 'Team'

//    @Column(name = "TEAM_ID")
//    private Long teamId; //이 방법은 '객체'를 '테이블'에 맞추어 모델링한 것.
                           //즉, 객체지향적(객체지향모델링)이지 않다!

//    @Column(precision = 20) //'컬럼' 매핑
//    private BigDecimal age; //- 'precision': 아주 큰 숫자 또는 소수점 자릿수 사용할 때

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

    public Member(Long id, String username, RoleType roleType, Date createdDate, Date lastModifiedDate, String description) {
        this.id = id;
        this.username = username;
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



