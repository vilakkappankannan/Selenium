package com.vk.repository;

import com.vk.entity.Eligibility;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepo extends JpaRepository<Eligibility,Long> {
}
