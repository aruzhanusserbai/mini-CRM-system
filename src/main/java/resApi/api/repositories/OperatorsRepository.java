package resApi.api.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resApi.api.entities.Operators;

@Repository
@Transactional
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
}
