package toypz.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toypz.hellospring.domain.Member;
import toypz.hellospring.repository.MemberRepository;
import toypz.hellospring.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() throws Exception{

        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void joinDuplicate() {
        Member member1 = new Member();
        member1.setName("test");

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

}