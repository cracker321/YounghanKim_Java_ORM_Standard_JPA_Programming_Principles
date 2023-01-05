package hellojpa;


import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;

    private String name;


    //  < 'Member 객체(1)'와 'Locker 객체(1)' 간의 '1대 1 양방향' 연관관계 매핑 >
    //  '주 테이블(외래키를 소유)'은 '테이블 MEMBER '인 경우
    @OneToOne(mappedBy = "locker")
    private Member member;
}
