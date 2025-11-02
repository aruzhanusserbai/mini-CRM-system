package resApi.api.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import resApi.api.entities.Courses;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
