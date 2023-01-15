package hellojpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Seller extends BaseEntity {


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

    private String shopName; //'Seller 객체만의 고유한 필드'


}
