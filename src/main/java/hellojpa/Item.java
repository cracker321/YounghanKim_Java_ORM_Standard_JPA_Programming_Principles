package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@DiscriminatorColumn()
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;




}
