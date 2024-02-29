package toypz.hellospring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import toypz.hellospring.domain.Member;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static toypz.hellospring.repository.MemoryMemberRepository.store;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        //테스트가 실행되고 끝날때마다 한번씩 clear
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println(" @@ result ==> " + (result == member));
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        Member result = repository.findByName("member1").get();
        assertEquals(member1, result);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("member2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(2, result.size());
    }

}
