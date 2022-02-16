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
        memberRepository.save(new Member("aa","aa@a.dk","test12","Kurt","Wonnegut","a vej 12","Lyngby","2800"));
        memberRepository.save(new Member("bb","bb@a.dk","test12","Hanne","Wonnegut","b vej 12","Lyngby","2800"));
    }
    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());
    }
}