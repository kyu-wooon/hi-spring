package hi.hispring.service;

import hi.hispring.domain.Member;
import hi.hispring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {

    //MemoryMemberRepository 생성
    private final MemoryMemberRepository memberRepository =
            new MemoryMemberRepository();

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
