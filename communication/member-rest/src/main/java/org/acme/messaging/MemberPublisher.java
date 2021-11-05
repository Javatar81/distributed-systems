package org.acme.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.acme.Member;

@ApplicationScoped
public class MemberPublisher {
	@Inject
	ConnectionFactory connectionFactory;

	public void publishTopic(Member newMember) {
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
			Topic topic = context.createTopic("membersTopic");
			context.createProducer().send(topic, newMember);
		 } 
	}
	
	public void publishQueue(Member newMember) {
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
			Queue queue = context.createQueue("members");
			context.createProducer().send(queue, newMember);
		} 
	}

}
