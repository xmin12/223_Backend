package com.example.demo.domain.mylistentry;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;

public interface MyListEntryRepository extends JpaRepository<MyListEntry, UUID> {
    List<MyListEntry> findByUserId(UUID userId, Sort sort);
    List<MyListEntry> findByUserId(UUID userId);
}
