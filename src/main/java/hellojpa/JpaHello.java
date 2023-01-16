package hellojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class JpaHello {
    public static void main(String[] args){

       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

       EntityManager em = emf.createEntityManager();

       EntityTransaction tx = em.getTransaction();
       tx.begin();

       try{
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

//           Member findMember = em.getReference(Member.class, member.getId());

           tx.commit();
       }catch(Exception e){
           tx.rollback();
       }finally{
           em.close();
       }

       emf.close();
    }


    private static void printMemberAndTeam(Member member){

    }
}