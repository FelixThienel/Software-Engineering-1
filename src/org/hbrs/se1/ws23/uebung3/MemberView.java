package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung3.Container.Member;

import java.util.List;

public class MemberView {
    public static void dump(List<Member> list) {
        for(int x = 0; x < list.size(); x++)
            System.out.println(list.get(x).toString());
    }
}