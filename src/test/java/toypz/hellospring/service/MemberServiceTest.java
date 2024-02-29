package toypz.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toypz.hellospring.domain.Member;
import toypz.hellospring.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        //테스트가 실행되고 끝날때마다 한번씩 clear
        memberRepository.clearStore();
    }

    @Test
    public void join() {
        // given
        Member member = new Member();
        member.setName("member");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void joinDuplicate() {
        Member member1 = new Member();
        member1.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //e.getMessage();
        //assertThrows(NullPointerException.class, () -> memberService.join(member2));

/*
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
*/

    }


    @Test
    public void findMembers() {
    }

    @Test
    public void findOne() {
    }
}