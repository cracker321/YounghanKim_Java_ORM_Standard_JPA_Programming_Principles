package hellojpa;


import javax.persistence.Entity;
import javax.persistence.Inheritance;


@Entity
public class Book extends Item {

    private String author;

    private int ISBN;

}
