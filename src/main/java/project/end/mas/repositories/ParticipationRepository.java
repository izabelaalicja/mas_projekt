package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Participation;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation, Long>{
}
