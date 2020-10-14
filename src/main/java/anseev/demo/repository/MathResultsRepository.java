package anseev.demo.repository;

import anseev.demo.model.MathResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathResultsRepository extends JpaRepository<MathResults, Long> {}
