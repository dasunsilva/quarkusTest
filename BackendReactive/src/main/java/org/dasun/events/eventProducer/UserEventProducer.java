package org.dasun.events.eventProducer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import lombok.Setter;
import org.dasun.dto.UserDTO;
import org.dasun.events.UserEvent;

@Setter
@ApplicationScoped
public class UserEventProducer {
    @Inject
    Event<UserEvent> userEvent;

    private UserDTO userDTO;
    private String event;

    public void publishUserEvent(){

        UserEvent tempUserEvent = new UserEvent();
        tempUserEvent.setName(userDTO.getName());
        tempUserEvent.setId(userDTO.getId());
        tempUserEvent.setEvent(event);
        userEvent.fire(tempUserEvent);
    }
}
