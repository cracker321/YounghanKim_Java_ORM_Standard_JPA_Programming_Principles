package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


//< '단일 테이블' 전략 >
//- 그냥 'DB에서 '상속받는 하위 테이블들(ALBUM, MOVIE, BOOK)'을 '테이블 ITEM' 하위에 다 때려박는 것'.
//  그러면 이제 '자바 객체와 연동되는 H2에서 생성되는 DB 테이블'에서는 '테이블 ITEM' 하위에 '컬럼 ALBUM', '컬림 BOOK', '컬럼 MOVIE'가
//  다 하나에 때려박혀서 생성된다!
//- 즉, '자바 객체'에서 '조인 전략', '단일 테이블 전략' 등의 각기 다른 방법으로 '인텔리제이에서 코드를 작성했을 때 발생하는 변화'는,
//  '자바 객체 서버가 실행될 때 연동되어 생성되는 H2 DB의 테이블과 컬럼의 차이'이다!!
//- '조인 전략': '테이블 ITEM'과는 별도의 '테이블 ALBUM', '테이블 MOVIE', '테이블 BOOK'이 생성됨.
//- '단일 테이블 전략': '테이블 ITEM의 하위 컬럼'으로, '컬럼 ALBUM', '컬럼 MOVIE', '컬럼 BOOK'이 다 하나의 '테이블 ITEM'에
//                   때려박혀져서 생성됨.


@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //- '조인 테이블 전략'에서의 'InheritanceType.JOINED'를
                                                      //여기 '단일 테이블 전략'에서는 'InheritanceType.SINGLE_TABLE'로 바꾸기만
                                                      //하면 끝임.
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;


}

//=================================================================================================================
//
//< '조인 테이블' 전략 >
//
//@DiscriminatorColumn(name = "DTYPE")
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//public class Item {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ITEM_ID")
//    private Long id;
//
//    private String name;
//
//    private int price;
//
//
//}
