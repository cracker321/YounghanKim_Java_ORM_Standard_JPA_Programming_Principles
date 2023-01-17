package hellojpa;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Setter
@Getter
@NoArgsConstructor //'임베디드 타입(값 타입)'은 '기본 생성자'를 만드는 것이 필수다!!!
@AllArgsConstructor //옵션적으로 하는 듯..?
@Entity
public class PhoneServiceProvider {

    @Id
    @GeneratedValue
    @Column(name = "PHONE_SERVICE_PROVIDER_ID")
    private Long id;

    private


}
