package demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP",discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class Operation implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numOp;
	private Date dateOp;
	private double solde;
	@ManyToOne
	@JoinColumn(name = "Code_CPT")
	private Compte compte;
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(Date dateOp, double solde, Compte compte) {
		super();
		this.dateOp = dateOp;
		this.solde = solde;
		this.compte = compte;
	}
	public Long getNumOp() {
		return numOp;
	}
	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}
	public Date getDateOp() {
		return dateOp;
	}
	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	

}
