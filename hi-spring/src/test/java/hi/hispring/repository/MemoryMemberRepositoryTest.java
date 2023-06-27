package hi.hispring.repository;

import hi.hispring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        //given
        Member member = new Member(); //test 할 Member 생성
        member.setName("spring"); //test 할 name 생성
        //when
        repository.save(member); //테스트 데이터 저장
        //get()으로 데이터 꺼내기
        Member result = repository.findById(member.getId()).get();
        // 세팅한 데이터와 저장한 데이터가 같다고 제대로 출력이 되는지 확인
        // 참이면 true 출력
        System.out.println("result=" + (result == member));
        //assertThat을 이용한 검증
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();
        //then

        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when

        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
