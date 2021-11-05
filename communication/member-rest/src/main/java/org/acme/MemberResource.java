package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.messaging.MemberPublisher;

@Path("/members")
public class MemberResource {

	@Inject
    EntityManager em;
	
	@Inject
	MemberPublisher publisher;
	
	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public void createMember(Member member) {
		//em.persist(member);
		publisher.publishTopic(member);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> getAllMembers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member).orderBy(cb.asc(member.get("firstName")));
        return em.createQuery(criteria).getResultList();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member findMemberById(@PathParam("id") Long id) {
        return em.find(Member.class, id);
	}
	
	@DELETE
	@Transactional
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMemberById(@PathParam("id") Long id) {
        em.remove(em.find(Member.class, id));
	}
	
	
}
