package osipovmr.KteLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osipovmr.KteLabs.model.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
