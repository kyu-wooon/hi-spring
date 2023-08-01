package hi.hispring.service;

import hi.hispring.domain.Member;
import hi.hispring.repository.MemberRepository;
import hi.hispring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링 컨테이너와 테스트를 함께 실행
@SpringBootTest
//테스트는 반복이 가능해야 한다. 커밋 안하고 롤백하여 데이터를 남기지 않음
@Transactional
class MemberServiceIntegrationTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;





    @Test
    void join() {
        //given - 무언가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when - 이것을 실행했을 때
        Long saveId = memberService.join(member);

        //then - 결과가 이렇게 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void duplicated_exception(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //중복된 값이므로 join되면 안됨

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }
   @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}