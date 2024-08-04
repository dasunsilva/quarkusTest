package org.dasun.events.eventConsumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.dasun.events.UserEvent;
import org.jboss.logging.Logger;


@ApplicationScoped
public class UserEventConsumer {

    @Inject
    Logger logger;

    public void onUserEvent(@Observes UserEvent event){

        if(event.getEvent().equals("Add")){
            logger.info("A new user is added with id: " + event.getId() + " And name: " + event.getName());
        }

        if(event.getEvent().equals("Edit")){
            logger.info("A user with id : " + event.getId() + " And name: " + event.getName() + " is updated");

        }
        if(event.getEvent().equals("Delete")){
            logger.info("User is with id: " + event.getId() + " And name: " + event.getName() + " is deleted!");

        }
    }
}
