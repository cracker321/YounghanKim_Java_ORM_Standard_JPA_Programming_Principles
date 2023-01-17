package hellojpa;


import lombok.NoArgsConstructor;

import javax.persistence.*;


//[ '임베디드 타입'강 05:00~ ]: 정말 긍정적으로 '객체지향적인 방식'이라서 강력추천!
//                           'Member 엔티티'가 더욱 '객체지향적'이고, '응집력 있게' 되었다.
//< '새로운 기본값 타입'으로 아래 두 필드를 합쳐서 쓸 수 있는 '주소 Address 임베디드 타입'을 생성하기 >
//- 즉, '새로운 클래스 PhoneNumber'를 '생성하고', '그 새로운 클래스 PhoneNumber' 안에 원래라면 저렇게 지저분하게 많이 있어햐 하는
//  아래 주석 처리한 세 필드들(areaCode, localNumber, provider)을 넣기.
//  그리고, 아래 세 필드들을 대신하여, 아래에 '필드 phoneNumber'를 대신 사용하는 것임.
//- 그 후, '이 클래스 PhoneNumber 위에는' '@Embeddable'을 쓰거나, 또는
//- 저 아래 'Member 객체의 필드 phoneNumber 위에는' '@Embedded'를 작성하거나 둘 중 하나만 하거나, 둘 다 작성해도 된다.


@NoArgsConstructor
@Embeddable
@Entity
public class PhoneNumber {

    String areaCode;
    String localNumber;


//================================================================================================================


    //[ '임베디드 타입'강 14:30~ ]
    //< '임베디드 타입'이 다시 '다른 클래스 객체'와 '연관관계 매핑'을 맺는 것 >
    //이미 '임베디드 타입의 새로운 클래스 PhoneNumber 객체'가 또 다시, '다른 클래스 PhoneServiceProvider 객체'와
    //여기서 'N:1 단방향 연관관계 매핑'을 맺는 것

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_SERVICE_PROVIDER_ID")
    private PhoneServiceProvider phoneServiceProvider;


//================================================================================================================


}
