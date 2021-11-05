package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MemberRepository {

	@Inject
    EntityManager em;
	
	@Transactional
	public void createMember(Member member) {
		em.persist(member);
	}
}
