package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("xxx", "xxx@a.dk", "test12", "Peter", "Larsen", "Lyngbyvej", "kbh Ø", 2000, true, 3 ));
        memberRepository.save(new Member("yyy", "yyy@a.dk", "test12","Hans","Jensen", "Lyngbyvej", "Kbh Ø", 2000, true, 4));
    }
    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());
    }
}