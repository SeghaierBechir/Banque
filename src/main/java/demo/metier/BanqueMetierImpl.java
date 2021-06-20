package demo.metier;

import java.util.Date;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dao.CompteRepository;
import demo.dao.OperationRepository;
import demo.entities.Compte;
import demo.entities.CompteCourant;
import demo.entities.Operation;
import demo.entities.Retrait;
import demo.entities.Versement;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		Optional<Compte> cp=compteRepository.findById(codeCpte);
		if(!cp.isPresent()) throw new RuntimeException("compte introuvable");
		return cp.get();
	}

	@Override
	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp=consulterCompte(codeCpte);
		Versement v= new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp=consulterCompte(codeCpte);
		double facilitesCaisse=0;
		if(cp instanceof CompteCourant)
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<montant)
			throw new RuntimeException("solde insuffisant");
		Retrait r= new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		// TODO Auto-generated method stub
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException("impossible de faire virement sur le même compte");
		retirer(codeCpte1,montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
	}

}
