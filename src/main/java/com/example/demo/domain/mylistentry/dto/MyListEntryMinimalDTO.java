package com.example.demo.domain.mylistentry.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserMinimalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class MyListEntryMinimalDTO extends AbstractDTO {

    private String title;
    private String text;
    private int importance;
    private UserMinimalDTO user;

    public MyListEntryMinimalDTO(String title, String text, int importance, UserMinimalDTO user){
        this.title = title;
        this.text = text;
        this.importance = importance;
        this.user = user;
    }
}
