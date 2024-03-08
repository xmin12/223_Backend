package com.example.demo.domain.user.dto;


import com.example.demo.core.generic.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserMinimalDTO extends AbstractDTO {
    public UserMinimalDTO(UUID id) {
        super(id);
    }
}
