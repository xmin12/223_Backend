package com.example.demo.domain.mylistentry.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserMinimalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MyListEntryDTO extends AbstractDTO{

    private String title;
    private String text;
    private Date creationDate;
    private int importance;
    private UserMinimalDTO user;

    public MyListEntryDTO(String title, String text, Date creationDate, int importance, UserMinimalDTO user){
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.importance = importance;
        this.user = user;
    }
}
