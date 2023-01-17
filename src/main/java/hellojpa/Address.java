package hellojpa;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


//[ '임베디드 타입'강 05:00~ ]: 정말 긍정적으로 '객체지향적인 방식'이라서 강력추천!
//                           'Member 엔티티'가 더욱 '객체지향적'이고, '응집력 있게' 되었다.
//< '새로운 기본값 타입'으로 아래 두 필드를 합쳐서 쓸 수 있는 '주소 Address 임베디드 타입'을 생성하기 >
//- 즉, '새로운 클래스 Address'를 '생성하고', '그 새로운 클래스 Address' 안에 원래라면 저렇게 지저분하게 많이 있어햐 하는
// 아래 주석 처리한 세 필드들(city, street, zipcode)을 넣기.
//  그리고, 아래 세 필드들을 대신하여, 아래에 '필드 homeAddress'를 대신 사용하는 것임.
//- 그 후, '이 클래스 Address 위에는' '@Embeddable'을 쓰거나, 또는
//- 저 아래 'Member 객체의 필드 homeAddress 위에는' '@Embedded'를 작성하거나 둘 중 하나만 하거나, 둘 다 작성해도 된다.


@Setter
@Getter
@NoArgsConstructor //'임베디드 타입(값 타입)'은 '기본 생성자'를 만드는 것이 필수다!!!
@AllArgsConstructor //옵션적으로 하는 듯..?
@Embeddable
public class Address { //새로운 '값 타입(클래스)'인 'Address 객체'

    //'접근제어자'는 사용 안한다!
    String city;
    String street;



//================================================================================================================


    //[ '임베디드 타입'강 14:30 ~ ]
    //< '임베디드 타입과 연관관계' >
    //- '하나의 임베디드 타입(새로운 값 타입 클래스)의 내부'에, 다시 '또 새로운 임베디드 타입(다시 새로운 값 타입 필드)'을
    //  그 내부에 포함시킬 수 있다.
    @Embedded
    Zipcode zipcode;


//================================================================================================================

}
