package ar.edu.unlp.pas.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.pas.cart.models.SystemConfig;

@Repository
public interface JpaSystemConfigRepository extends JpaRepository<SystemConfig, Long> {
}
