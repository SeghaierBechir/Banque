package demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
