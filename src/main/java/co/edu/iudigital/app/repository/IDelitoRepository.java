package co.edu.iudigital.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.iudigital.app.model.Delito;

public interface IDelitoRepository extends JpaRepository<Delito, Long>{
}
