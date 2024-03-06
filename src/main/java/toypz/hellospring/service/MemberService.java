package toypz.hellospring.service;

import org.springframework.transaction.annotation.Transactional;
import toypz.hellospring.domain.Member;
import toypz.hellospring.repository.MemberRepository;
import toypz.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가임
    public Long join(Member member) {
        // 같은 이름은 중복 회원으로 간주
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 중복회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException(" @@@ 이미 존재하는 회원입니다. @@@");
                });
    }


}
