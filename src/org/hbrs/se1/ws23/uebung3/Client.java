package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung3.Container.ConcreteMember;
import org.hbrs.se1.ws23.uebung3.Container.Container;
import org.hbrs.se1.ws23.uebung3.Container.ContainerException;
import org.hbrs.se1.ws23.uebung3.Container.Member;

import java.util.List;

public class Client {
    public static void main(String[] args) throws ContainerException {
        Member member1 = new ConcreteMember(0);
        Member member2 = new ConcreteMember(1);
        Member member3 = new ConcreteMember(2);
        Member member4 = new ConcreteMember(8);
        Member member5 = new ConcreteMember(1234);
        CreateContainer creator = new CreateContainer();
        Container container = creator.getContainer();
        container.addMember(member1);
        container.addMember(member2);
        container.addMember(member3);
        container.addMember(member4);
        container.addMember(member5);
        List<Member> member = container.getCurrentList();
        MemberView.dump(member);
    }
}
