package org.dasun.events;

import jakarta.enterprise.context.RequestScoped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEvent {
    private Long id;
    private String name;
    private String event;
}
