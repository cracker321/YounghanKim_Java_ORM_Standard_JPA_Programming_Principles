package hellojpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //여기선 기본적으로 '@Entity(name = "Member")'이고, 여기서 '(name = "Member")'는 생략된 상태임
//@Table(name = MBR) //- 만약, 회사 내부 규정 등의 이유로 아래 '자바 객체 Member'를
                     //이미 존재하는 'DB 속 테이블 MBR'에 매핑해야 한다면 이렇게 작성함.
                     //그러면 이제, 아래 'Mebmer 객체'는 DB에서 '테이블 Member'에 매핑되는 것이 아니고,
                     //'테이블 MBR'에 매핑됨.
                     //- 따라서, 쿼리도 이제 'INSERT INTO MBR VALUES..'로 DB에 날려지게 된다.

public class Member {

    @Id
    private Long id; //- 'id': 'DB 테이블 Member의 컬럼 id'로 매핑됨.
                     //- '@Id': 'DB 테이블 Member'의 'PK'는 '여기 자바 객체의 id'임을 말해주기 위해 '@Id'를 붙인 것임.

    //@Column(name = "username") //만약, DB에 이미 '컬럼 username'이 있다면, 아래 '필드 nanme'은 DB에서
                               //'컬럼 name'으로 입력되는 것이 아니라, '컬럼 username'으로 입력된다!
    private String name; //'DB 테이블 Member의 컬럼 name'으로 들어감
    private String age;


    //JPA는 기본적으로 '기본 생성자'가 하나 반드시 있어야 한다.
    //왜냐하면, JPA는 내부적으로 '리플렉션' 등을 써야 하기 때문에, '동적으로 객체를 생성'하여야 하고,
    //따라서, '기본 생성자'가 하나 있어야 한다.
    public Member(){ //꼭 'public'이어야 할 필요는 없음
        
    }


    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}