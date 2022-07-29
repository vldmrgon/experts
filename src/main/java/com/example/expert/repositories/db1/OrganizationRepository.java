package com.example.expert.repositories.db1;

import com.example.expert.domain.entities.db1.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
