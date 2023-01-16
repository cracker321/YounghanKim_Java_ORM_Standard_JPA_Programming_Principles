package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    //< '영속성 전이(CASCADE)와 고아 객체'강 >. '고아 객체 제거'. 'orphanRemoval = true'
    //만약, 'Child 객체'가 'Parent 객체와의 연관관계'가 '끊어지게 되는 일이 발생한다면',
    //'Child 객체'를 '자동으로 삭제시켜주는 기능'
    //
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Child> children = new ArrayList<>();


    //< 연관관계 편의 메소드 >
    public void addChild(Child child){

        children.add(child);
        child.setParent(this);



    }


}
