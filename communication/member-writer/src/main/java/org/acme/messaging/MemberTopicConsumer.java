package org.acme.messaging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.acme.Member;
import org.acme.MemberRepository;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class MemberTopicConsumer implements Runnable {

	@Inject
	ConnectionFactory connectionFactory;
	@Inject
	MemberRepository repository;
	
	private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

	void onStart(@Observes StartupEvent ev) {
		scheduler.submit(this);
	}

	void onStop(@Observes ShutdownEvent ev) {
		scheduler.shutdown();
	}

	@Override
	public void run() {
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(context.createTopic("membersTopic"));
            while (true) {
            	System.out.println("Waiting to receive message");
                Message message = consumer.receive();
                System.out.println("Message received");
                if (message == null) return;
                Member member = message.getBody(Member.class);
                System.out.println(String.format("Inserting new member %s", member));
                repository.createMember(member);
                System.out.println("success");
            }
        } catch (JMSException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}

}
