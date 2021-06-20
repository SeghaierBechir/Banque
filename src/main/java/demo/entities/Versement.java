package demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(Date dateOp, double solde, Compte compte) {
		super(dateOp, solde, compte);
		// TODO Auto-generated constructor stub
	}
	

}
