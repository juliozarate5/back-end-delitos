package co.edu.iudigital.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.iudigital.app.model.Caso;

@Repository
public interface ICasoRepository extends JpaRepository<Caso, Long>{
	
	// casos fecha mayor que determinada fecha
	//@Query("SELECT c FROM Caso c WHERE c.fechaHora > ?1")
	//public List<Caso> findByFechaHoraGreaterThan(LocalDateTime fechaHora);
	@Query("UPDATE Caso c SET c.visible=?1 WHERE c.id = ?2")
	public Boolean setVisible(Boolean visible, Long id);
}
