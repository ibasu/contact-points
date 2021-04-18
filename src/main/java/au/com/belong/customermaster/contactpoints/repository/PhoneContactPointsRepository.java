package au.com.belong.customermaster.contactpoints.repository;

import au.com.belong.customermaster.contactpoints.repository.entity.PhoneContactPoints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneContactPointsRepository extends BaseJpaRepository<PhoneContactPoints, String> {

    List<PhoneContactPoints> findByCustomerId(String customerId);
}
