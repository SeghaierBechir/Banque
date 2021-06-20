package demo.metier;

import org.springframework.data.domain.Page;

import demo.entities.Compte;
import demo.entities.Operation;

public interface IBanqueMetier {
	public Compte consulterCompte(String codeCpte);
	public void verser(String codeCpte,double montant);
	public void retirer(String codeCpte,double montant);
	public void virement(String codeCpte1,String codeCpte2,double montant);
	public Page<Operation> listOperation(String codeCpte,int page,int size);

}
