package org.jboss.as.quickstarts.kitchensink.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Member;

@Stateless
public class MemberDeletion {
	
	@Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;
    
    @Inject
    private MemberRepository memberRepository;
	
	public void delete(Long id) throws Exception {
    	// Check whether user is already in database
    	Member existingMember = memberRepository.findById(id);
    	if (existingMember == null) {
    		throw new RuntimeException(String.format("User with id '%s' does not exist", id));
    	} else {    		
    		log.info("Deleting " + existingMember.getName());
    		em.remove(existingMember);
    		memberEventSrc.fire(existingMember);
    	}
    	
    }
}
