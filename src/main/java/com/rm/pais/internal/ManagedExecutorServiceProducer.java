package com.rm.pais.internal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

//Just to retrieve to Scheduler executor
@ApplicationScoped
public class ManagedExecutorServiceProducer {
	@Produces
    public ManagedScheduledExecutorService getManagedScheduledExecutorService() {
        try {
            // Look up the ManagedScheduledExecutorService from JNDI
            return (ManagedScheduledExecutorService) new InitialContext()
                    .lookup("java:comp/DefaultManagedScheduledExecutorService");
        } catch (NamingException e) {
            throw new RuntimeException("Error obtaining ManagedScheduledExecutorService", e);
        }
    }
}
