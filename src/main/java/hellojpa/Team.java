package hellojpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

//    < 'Team 객체(1)'와 'Member 객체(N)'와 간의 '1대 N 단방향' 연관관계 매핑. '주인 객체(외래키를 소유)'는 'Team 객체'인 경우. 잘 사용X >
//    - '주인(1)'이 'Team 객체의 필드 members'인 경우
//    '단방향' 연관관계 이기에, 여기 'Team 객체 내부'에서만 로직 작성하면 끝.
//    'Member 객체'에다 무엇을 또 작성할 필요X
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<Member>();





//    < 'Member 객체(N)'와 'Team 객체(1)' 간의 'N대 1 양방향' 연관관계 매핑. '주인 객체(외래키를 소유)'는 'Member 객체'인 경우 >
//    - '주인(N)'이 'Member 객체의 필드 team'인 경우
//    @OneToMany(mappedBy = "team") //'양방향 연관관계와 연관관계의 주인 1- 기본'강 09:00~
//                                  //'양방향 객체 연관관계 매핑'
//                                  //- 'mappedBy = team': 반대평 연관관계인 'Member 객체'의 '필드 team'과 '연결(매핑)'되어 있다 라는 뜻
//                                  //- '양방향 연관관계 매핑의 주인'은 'Member 객체의 필드 team'이다!
//                                  //   왜냐하면, '주인'은 '외래 키'가 있는 곳이기 때문! 강의 pdf자료에 상세 필기해놨음
//                                  //   현재 이 필드는 연관관계의 주인이 아니기 때문에, 이 '필드 members'를 통해
//                                  //   회원을 추가, 삭제하는 등의 Create, Update, Delete(아마도)는 할 수 없다.
//                                  //   여기서 그렇게 코드 작성해서 시도해봤자, DB에 아무런 영향도 가지 않는다!
//
//    private List<Member> members = new ArrayList<>(); //- 'List<Member>': '양방향 객체 연관관계 매핑'
//                                                      //                  '회원(Member)'는 '다수(N)'이기에 'List' 씀.
//                                                      //- 'new ArrayList<>()': 'ArrayList로 초기화'. 관례임.
//                                                      //                    이래야, 'add'할 때, 'null point exception'안 뜸.





//====================================================================================================================

    public Team (String id, String name){
        this.id = id;
        this.name = name;
    }




//====================================================================================================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
