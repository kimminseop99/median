package org.example.service;

import org.example.container.Container;
import org.example.dao.MemberDao;
import org.example.dto.Member;

import java.util.List;

public class MemberService {
    private static MemberDao memberDao;

    public MemberService() {
        memberDao = Container.memberDao;
    }

    public static List<Member> getAllMember() {
        return memberDao.getAllMember();
    }

    public static void deletePatient(int patientNum) {
        memberDao.deletePatient(patientNum);
    }

    public static void deleteArticle(int patientNum) {
        memberDao.deleteArticle(patientNum);
    }

    public static void deleteReservation(int patientNum) {
        memberDao.deleteReservation(patientNum);
    }

    public int join(String name, int age, String phone, String rrn, double height, double weight, String ud, String loginId, String loginPw) {
        Member member = new Member(name, age, phone, rrn, height, weight, ud, loginId, loginPw);
        return memberDao.join(member);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public Member getMember(int memberId) {
        return memberDao.getMember(memberId);
    }


    public static void StringUpdate(String Info, String changeInfo, int id) {
        memberDao.StringUpdate(Info, changeInfo, id);
    }

    public static void IntUpdate(String Info, int changeInfo, int id) {
        memberDao.IntUpdate(Info, changeInfo, id);
    }

    public static void DoubleUpdate(String Info, double changeInfo, int id) {
        memberDao.DoubleUpdate(Info, changeInfo, id);
    }

    public static String getMemberName(int patientId) {
        return memberDao.getMemberName(patientId);
    }
}