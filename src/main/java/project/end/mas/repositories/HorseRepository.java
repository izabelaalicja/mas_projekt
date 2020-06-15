package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Horse;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Long> {
}
