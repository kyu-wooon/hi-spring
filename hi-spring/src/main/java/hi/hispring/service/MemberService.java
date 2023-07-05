package hi.hispring.service;

import hi.hispring.domain.Member;
import hi.hispring.repository.MemberRepository;
import hi.hispring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService{


    //회원 레포지토리 코드가 회원 서비스 코드를 DI 가능하게 변경
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */

    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //null이 아니면 검사
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
