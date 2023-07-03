package hi.hispring.service;

import org.junit.jupiter.api.Test;
import hi.hispring.repository.MemoryMemberRepository;
import hi.hispring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //테스트 전 생성
    @BeforeEach
    public  void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //테스트 후 초기화
    @AfterEach
    public void afterEach(){

        memberRepository.clearStore();


    }
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