package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setAge(10);
            member.setUsername("jang");
            em.persist(member);

            TypedQuery<Member> findResult = em.createQuery("select m from Member m where m.username = :username", Member.class); //반환타입이 명확할 때는 TypeQuery
            findResult.setParameter("username", "jang");
            Member singleResult = findResult.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());
            Query findResult2 = em.createQuery("select m.username, m.age from Member m"); //반환타입이 명확하지 않을 때는 Query

            List<Team> query = em.createQuery("select t from Member m join m.team t", Team.class).getResultList();

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("result[0] = " + result[0]);
//            System.out.println("result[0] = " + result[1]);


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

//        **데이터 등록**
//        try{
//
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Person person = new Person();
//            person.setName("person1");
//            person.changeTeam(team);
//            em.persist(person);
//
//            em.flush();
//            em.clear();
//
//            Team findTeam = em.find(Team.class, team.getId());
//
//            List<Person> persons = findTeam.getPersons();
//
//            for (Person person1 : persons) {
//                System.out.println("person1.getName() = " + person1.getName());
//            }
//
//
//            tx.commit();
//        }catch (Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }

        ////        **데이터 수정**
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("========수정 전========");
//            System.out.println("findMemberId = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//
//            findMember.setName("HelloB");
////            여기서 persist를 안해도된다!!
//            tx.commit();
//
//            System.out.println("========수정 후========");
//            System.out.println("findMemberId = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//
//        }catch (Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }


//        **데이터 삭제**
//        try {
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);
//            tx.commit();
//
//        }catch (Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }

//      JPQL로 모든 멤버 가져오기
//        try{
//            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//
//            for (Member member : findMembers) {
//                System.out.println("member.getName() = " + member.getName());
//            }
//            tx.commit();
//        }catch (Exception e) {
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        emf.close();
    }
}
