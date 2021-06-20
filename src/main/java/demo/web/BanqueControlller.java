package demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.entities.Compte;
import demo.entities.Operation;
import demo.metier.IBanqueMetier;

@Controller
public class BanqueControlller {
	@Autowired
	private IBanqueMetier ibanqueMetier;
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model,String codeCompte,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name = "size",defaultValue = "5")int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp=ibanqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperation=ibanqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listOperations", pageOperation.getContent());
			int [] pages= new int[pageOperation.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("compte", cp);
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveOperation",method = RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,
			String codeCompte2,double montant) {
		try {
			if(typeOperation.equals("VERS")) {
				ibanqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("RET")) {
				ibanqueMetier.retirer(codeCompte, montant);
			}
			else if(typeOperation.equals("VIR")) {
				ibanqueMetier.virement(codeCompte, codeCompte2, montant);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}

}
