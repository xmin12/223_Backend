package com.example.demo.domain.mylistentry;

import com.example.demo.core.generic.AbstractEntity;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "my_list_entry")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Log4j2
public class MyListEntry extends AbstractEntity {

    @Column(name = "title", nullable = false)
    @Size(max = 20, min = 5)
    private String title;

    @Column(name = "text", nullable = false)
    @Size(max = 100, min = 5)
    private String text;

    @Column(name = "creation_date", nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "importance", nullable = false)
    @Min(1)
    @Max(5)
    private int importance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        log.info("Attempting to create new MyListEntry");
        this.creationDate = new Date();
    }

    public MyListEntry(UUID id, String title, String text, Date creationDate, int importance, User user) {
        super(id);
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.importance = importance;
        this.user = user;
    }
}
