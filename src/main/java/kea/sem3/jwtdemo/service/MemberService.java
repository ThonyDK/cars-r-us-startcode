package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberResponse(member,false)).collect(Collectors.toList());
    }

    public MemberResponse getMemberByUserName(String username) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new Client4xxException("User not found", HttpStatus.NOT_FOUND));
        return new MemberResponse(member,false);
    }

    public MemberResponse addMember(MemberRequest body) {

        if (memberRepository.existsById((body.getUsername()))) {
            throw new Client4xxException("Provided user name is taken");
        }
        if (memberRepository.emailExist(body.getEmail())) {
            throw new Client4xxException("Provided email is taken");
        }
        Member member = new Member(body);
        member.addRole(Role.USER);
        member = memberRepository.save(member);
        return new MemberResponse(member.getUsername(), member.getCreated(), member.getRoles());
    }
    public MemberResponse editMember(MemberRequest body, String username){

        Member m1 = memberRepository.findById(username).orElseThrow(()-> new Client4xxException("Not found"));
        //From baseuser
        m1.setUsername(body.getUsername());
        m1.setEmail(body.getEmail());
        m1.setPassword(body.getPassword());

        //From user

        m1.setFirstName(body.getFirstName());
        m1.setLastName(body.getLastName());
        m1.setStreet(body.getStreet());
        m1.setCity(body.getCity());
        m1.setStreet(body.getStreet());
        m1.setZip(body.getZip());

        return new MemberResponse(memberRepository.save(m1),true);
    }
    public void updateNames(String username, String firstName, String lastName) {
        Member member = memberRepository.findById(username).orElseThrow(()-> new Client4xxException("User name: " + username + "Doesn't exist in database"));
        member.setFirstName(firstName);
        member.setLastName(lastName);
        memberRepository.save(member);
    }

    public void deleteMember(String username){
        memberRepository.deleteById(username);
    }
}

