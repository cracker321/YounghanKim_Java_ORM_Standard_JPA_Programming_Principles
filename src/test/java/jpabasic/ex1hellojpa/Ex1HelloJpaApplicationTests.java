package jpabasic.ex1hellojpa;

import hellojpa.Member;
import hellojpa.Team;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class) //'junit'에게 '나 스프링부트와 관련된 테스트 할 거야~'라고 알려주는 어노테이션
@SpringBootTest
class Ex1HelloJpaApplicationTests {

    // 엔티티 매니저 팩토리 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    // 엔티티 매니저 생성
    EntityManager em = emf.createEntityManager();

    // 트랜잭션 획득
    EntityTransaction tx = em.getTransaction();

    @Transactional
    @Rollback(false)
    @Test
    public void testSave1(){

        //생성자
        // String id, String name 임

        //< 'Member 객체(주인객체. N)' : 'Team 객체(주인이 아닌 객체. 1)' 간의 '단방향 연관관계' 매핑 >

        //< 전제 given >


        //'회원'을 영속화하여 저장함
        Member member = new Member();
        member.setName("member1");
        member.setTeam(team); //1.'Member 객체(주인객체. N)'에 'Team 객체(주인이 아닌 객체. 1)'에 를 넣는(속성 추가) 것
                              //   (즉, Member 객체를 Team 객체에 '소속시키는(N:1이기 떄문에) 것')
                              //    즉, 'N:1 단방향 연관관계 매핑'을 구현한 것임.
        em.persist(member);


        //'팀'을 영속화하여 저장함
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);


        //< 조건 when >


        //< 검증 then >

    }


    @Transactional
    @Rollback(false)
    @Test
    public void TestSave2(){


        //< 'Member 객체(주인객체. N)' : 'Team 객체(주인이 아닌 객체. 1)' 간의 '양방향 연관관계' 매핑 >

        //[ '양방향 연관관계와 연관관계의 주인 2 - 주의점, 정리'강 02:20~ ]

        //< 전제 given >
        //'팀'을 영속화하여 저장함
        Team team = new Team();
        team.setName("팀1");
        //team.getMembers().add(member); //1.이미 N:1 단방향 매핑이 되어 있으니 당연히 'Team 객체 내부의 필드 members'를
                                         //  '읽어서(조회해서)' 'Member 객체에 접.근. 할 수 있고',
                                         //2.그 상태에서 'Member 객체'에 '영속화된 상태의 member를 추가'하려 시도했으나,
                                         //  'Team 객체'는 '주인이 아니기 때문'에, '조회(Read)' 이외의
                                         //  '수정(Update)', '생성(Create)', '삭제(Delete)'를 할 수가 없다!

                                         //그런데, 이거를 못하게 그대로 내버려두면, 이건 '양방향 매핑'에 어긋나고,
                                         //'객체지향스럽지 않기 때문'에,
        em.persist(team);



        //'회원'을 영속화하여 저장함
        Member member = new Member();
        member.setName("회원1");
        member.setTeam(team);
        em.persist(member);



        //< 조건 when >




        //< 결과 then >



    }
}
