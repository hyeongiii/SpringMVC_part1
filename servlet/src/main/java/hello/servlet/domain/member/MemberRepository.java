package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap 은 동시성 문제가 고료되지 않기 때문에, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야한다.
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

//    싱글톤으로 인스턴스 생성 (스프링을 톰캣 서버를 띄울 때 외엔 사용하지 않기 때문에 수동으로 싱글톤을 설정해주어야 한다.)
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

//    싱글톤 생성을 위한 생성자 막기
    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
//        store 자체를 보호하기 위해 새로운 ArrayList 를 생성한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
