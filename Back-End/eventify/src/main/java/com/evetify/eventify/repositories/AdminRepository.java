package com.evetify.eventify.repositories;

import com.evetify.eventify.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}
