package hi.hispring.repository;
import hi.hispring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository  {
//alt+enter 눌러서 구현가능
    //member 정보를 담을 해쉬맵
    private static Map<Long, Member> store = new HashMap<>();
    // 시퀀스
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//member의 Id를 sequence값을 더하는거로 지정
        store.put(member.getId(), member);//store 맵에 member id와 member 정보 넣기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//store 맵에서 id를 찾아서 반환;
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //loop 돌리기
                // lambda 사용. member.getName이 input name과 같은지 확인
                .filter(member -> member.getName().equals(name))
                // 찾을 시 반환한다.
                .findAny();
    }

    @Override
    // store의 values는 member
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
