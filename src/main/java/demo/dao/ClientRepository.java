package demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
