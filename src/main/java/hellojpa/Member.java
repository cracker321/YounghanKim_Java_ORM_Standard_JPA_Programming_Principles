package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


//- '단방향 매핑'만으로 최대한 '객체 설계'를 끝내고('N:1'에서 'N쪽 엔티티'에 '단방향 매핑'을 최대한 다 바르고),
//  이후 필요한 경우에만 '양방향 매핑'을 사용하는 것이다.
//  '양방향 매핑'을 많이 하는 것은 '자바 객체 입장'에서는 좋을 것이 없다. 복잡해지고 고민거리만 많아지는 것이기 때문.
//- 다만, 실무에서는 '양방향으로 객체를 참조'하는 경우가 많긴 함.
//  그러나, 이 때에도 '단방향 매핑'만 잘 해두면, '양방향 매핑'은 필요할 때 추가하면 됨.
//  그냥 엔티티 객체에 코드 몇 줄만 추가해주면 되는 것이기 때문.



@Getter
@Setter
@Entity //여기선 기본적으로 '@Entity(name = "Member")'이고, 여기서 '(name = "Member")'는 생략된 상태임
//@Table(name = "MBR") //- 만약, 회사 내부 규정 등의 이유로 아래 '자바 객체 Member'를
                       //이미 존재하는 'DB 속 테이블 MBR'에 매핑해야 한다면 이렇게 작성함.
                       //그러면 이제, 아래 'Mebmer 객체'는 DB에서 '테이블 Member'에 매핑되는 것이 아니고,
                       //'테이블 MBR'에 매핑됨.
                       //- 따라서, 쿼리도 이제 'INSERT INTO MBR VALUES..'로 DB에 날려지게 된다.

public class Member extends  BaseEntity{


//< '클래스 BaseEntity'의  '@MappedSupperClass' 기능 >
//- '클래스 BaseEntity'에  '@MappedSupperClass' 기능을 붙여주어서,
// 이제 '현재 프로젝트의 모든 자바 객체의 내부 필드들' 중에, '필드 id', '필드 name', '필드 createdBy', '필드 createdDate'...은
// 이제 '클래스 BaseEntity'로부터 '상속받기로' 하고, 저 '두 필드들'은 더 이상 해당 자바 객체 내에 작성하지 않아도 된다!
//- 또한, 해당 자바 객체들의 '생성자 내부 필드'에 'this.id = id', 'this.name = name', 'this.createdBy = createdBy'...
// 역시 이제 작성하면 안된다! 해당 필드들의 Getter, Setter도 작성하면 안된다!
//- '클래스 BaseEntity를 상속받아 사용하려는 자바 객체들'은 '반드시 extends를 통해 상속받아야 한다!'
//- '클래스 BaseEntity'는 당연히 '엔티티 객체가 아니기 때문에', 절대 '@Entity'를 붙이면 안되고,
//   따라서, 'DB 테이블'로도 올라가지 않는다!
//- '클래스 BaseEntity'는 직접 사용하지 않기 때문에, '추상 클래스 BaseEntity'로 설정해두는 것이 낫다.

//    @Id
//    @GeneratedValue
//    @Column(name = "SELLER_ID")
//    private Long id;
//    private String name;
//    private String createdBy;
//    private LocalDateTime createdDate;
//    private String lastModifedBy;
//    private LocalDateTime lastModifiedDate;

                     //- 'private int id'로 쓰게 되면, 'int'에 '0'이 들어갈 수 있기 때문에 이렇게는 지양해야 하고,
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



    private String email;

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



//    < 'Member 객체(N)'와 'Team 객체(1)' 간의 'N대 1 양방향' 연관관계 매핑. '주인 객체(외래키를 소유)'는 'Member 객체'인 경우 >
//    - '주인(N)'이 'Member 객체의 필드 team'인 경우
//   '단방향 연관관계'강 16:30~
    @ManyToOne(fetch = FetchType.LAZY) //'프록시 객체'로 조회하는 것! 즉, '지연로딩'을 실행시키는 것이다!
    @JoinColumn(name = "TEAM_ID") //'자바의 참조 객체 Team team'과 'DB의 테이블의 외래키 TEAM_ID'를 연결해주는 '연관관계 매핑'
    private Team team; //- '외래키'를 관리하는 이 필드가 바로 여기서 '양방향 연관관계'의 '주인'이다!
                       //   따라서, 이 필드를 통해서 '회원을 추가, 삭제, 변경 등'을 하거나, '팀을 변경 등'을 할 수 있다(확실히?)
                       //- 전제: '회원'은 '하나의 팀'에만 소속될 수 있다.
                       //       '회원'과 '팀'은 'N대 1 관계'이다.
                       //- 'Many': 'Member'
                       //- 'One': 'Team'


//  < 'Member 객체(1)'와 'Locker 객체(1)' 간의 '1대 1 양방향' 연관관계 매핑 >
//  '주 테이블(외래키를 소유)'은 '테이블 MEMBER '인 경우
    @OneToOne
    @JoinColumn(name = "LOCKER_ID") //'테이블 MEMBER의 외래키 컬럼 LOCKER_ID'
    private Locker locker;


//====================================================================================================================


    //[ '임베디드 타입'강 05:00~ ]: 정말 긍정적으로 '객체지향적인 방식'이라서 강력추천!
    //                           'Member 엔티티'가 더욱 '객체지향적'이고, '응집력 있게' 되었다.
    //< '새로운 기본값 타입'으로 아래 두 필드를 합쳐서 쓸 수 있는 '근무기간 Period 임베디드 타입'을 생성하기 >
    //- 즉, '새로운 클래스 Period'를 '생성하고', '그 새로운 클래스 Period' 안에 원래라면 저렇게 지저분하게 많이 있어햐 하는
    //  아래 주석 처리한 두 필드들(startDate, endDate)을 넣기.
    //  그리고, 아래 두 필드를 대신하여, 아래에 '필드 workPeriod'를 대신 사용하는 것임.
    //- 그 후, '저 클래스 Period 위에는' '@Embeddable'을 쓰거나, 또는
    //- 이 아래 'Member 객체의 필드 workPeriod 위에는' '@Embedded'를 작성하거나 둘 중 하나만 하거나, 둘 다 작성해도 된다.
    //- 사실, '
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    @Embedded
    private Period workPeriod;




    //< '새로운 기본값 타입'으로 아래 두 필드를 합쳐서 쓸 수 있는 '주소 Address 임베디드 타입'을 생성하기 >
    //- 즉, '새로운 클래스 Address'를 '생성하고', '그 새로운 클래스 Address' 안에 원래라면 저렇게 지저분하게 많이 있어햐 하는
    // 아래 주석 처리한 세 필드들(city, street, zipcode)을 넣기.
    //  그리고, 아래 세 필드들을 대신하여, 아래에 '필드 homeAddress'를 대신 사용하는 것임.
    //- 그 후, '저 클래스 Address 위에는' '@Embeddable'을 쓰거나, 또는
    //- 이 아래 'Member 객체의 필드 homeAddress 위에는' '@Embedded'를 작성하거나 둘 중 하나만 하거나, 둘 다 작성해도 된다.
//    private String city;
//    private String street;
//    private String zipcode;
    @Embedded
    private Address homeAddress;



    //< '새로운 기본값 타입'으로 아래 두 필드를 합쳐서 쓸 수 있는 '주소 Address 임베디드 타입'을 생성하기 >
    //- 즉, '새로운 클래스 PhoneNumber'를 '생성하고', '그 새로운 클래스 PhoneNumber' 안에 원래라면 저렇게 지저분하게 많이 있어햐 하는
    //  아래 주석 처리한 세 필드들(areaCode, localNumber, provider)을 넣기.
    //  그리고, 아래 세 필드들을 대신하여, 아래에 '필드 phoneNumber'를 대신 사용하는 것임.
    //- 그 후, '저 클래스 PhoneNumber 위에는' '@Embeddable'을 쓰거나, 또는
    //- 이 아래 'Member 객체의 필드 phoneNumber 위에는' '@Embedded'를 작성하거나 둘 중 하나만 하거나, 둘 다 작성해도 된다.
//    private String areaCode;
//    private String localNumber;
    @Embedded
    private PhoneNumber phoneNumber;




//====================================================================================================================


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

    public Member(String username, RoleType roleType, Date createdDate, Date lastModifiedDate, String description) {
        this.username = username;
        this.roleType = roleType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }



    //============================================================================================================
    public void setTeam(Team team) {
        this.team = team;

        team.getMembers().add(this); //'양방향 연관관계와 연관관계의 주인 2 - 주의점, 정리'강 12:20~
                                     //- '연관관계 편의 메소드'
    }
    //============================================================================================================



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



