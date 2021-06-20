package demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.dao.ClientRepository;
import demo.dao.CompteRepository;
import demo.dao.OperationRepository;
import demo.entities.Client;
import demo.entities.Compte;
import demo.entities.CompteCourant;
import demo.entities.CompteEpargne;
import demo.entities.Retrait;
import demo.entities.Versement;
import demo.metier.IBanqueMetier;

@SpringBootApplication
public class BanqueApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier iBanqueMetier;

	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Client c1=clientRepository.save(new Client("Bechir", "bechir@gmail.com"));
		Client c2=clientRepository.save(new Client("seghaier", "seghaier@gmail.com"));
		
		Compte cp1=compteRepository.save(new CompteCourant("c1", new Date(), 2000, c1, 500));
		Compte cp2=compteRepository.save(new CompteEpargne("c2", new Date(), 4000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 500, cp1));
		operationRepository.save(new Versement(new Date(), 300, cp1));
		operationRepository.save(new Retrait(new Date(), 500, cp1));
		
		operationRepository.save(new Versement(new Date(), 500, cp2));
		operationRepository.save(new Versement(new Date(), 300, cp2));
		operationRepository.save(new Retrait(new Date(), 200, cp2));
		
		iBanqueMetier.verser("c1", 1111);
		*/
	}

}
