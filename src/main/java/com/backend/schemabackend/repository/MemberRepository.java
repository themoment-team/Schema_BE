import com.backend.schemabackend.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void saveMember(Member member){
        em.persist(member);
    }

    public Member findByUserid(String userid){
        TypedQuery<Member> query = em.createQuery("select m from Member as m where m.userid = ?1", Member.class)
                .setParameter(1, userid);
        return query.getSingleResult();
    }
}
