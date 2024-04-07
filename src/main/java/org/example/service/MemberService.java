package org.example.service;

import org.example.container.Container;
import org.example.dao.MemberDao;
import org.example.dto.Member;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        memberDao = Container.memberDao;
    }

    public int join(String loginId, String loginPw, int age, String phone, String rrn, double height, double weight, String ud, String name) {
        Member member = new Member(loginId, loginPw,age,phone,rrn,height,weight,ud, name);
        return memberDao.join(member);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public Member getMember(int memberId) {
        return memberDao.getMember(memberId);
    }


}