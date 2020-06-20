package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.KategoriaRepository;
import com.example.demo.dao.ProducentRepository;
import com.example.demo.dao.UzytkownikRepository;
import com.example.demo.dao.ZamowienieRepository;
import com.example.demo.entity.Kategoria;
import com.example.demo.entity.Producent;
import com.example.demo.entity.Produkt;
import com.example.demo.entity.Rola;
import com.example.demo.entity.Uzytkownik;
import com.example.demo.entity.Zamowienie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@SessionAttributes("uzytkownik")
public class AuthController {
 
	@Autowired
	private UzytkownikRepository uzytkownikRepository;
	
	@Autowired
	private KategoriaRepository kategoriaRepository;
	
	@Autowired
	private ProducentRepository producentRepository;
	
	@Autowired
	private ZamowienieRepository zamowienieRepository;
	
	@ModelAttribute("uzytkownik")
	public Uzytkownik setUpUserForm(Uzytkownik uzytkownik) {
		return uzytkownik;
	}
	@RequestMapping("/")
	public String index() {
		Kategoria kat = new Kategoria();
		kat.setNazwaKat("Laptopy");
		
		Kategoria kat2 = new Kategoria();
		kat2.setNazwaKat("Smartfony");
		kategoriaRepository.save(kat);
		kategoriaRepository.save(kat2);
		
		return "main-menu";
	}
	@RequestMapping("/menu")
	public String menu(Model theModel) {
		theModel.addAttribute("noweKonto",true);
		return "main-menu";
	}
	
	@RequestMapping("/register")
	public String register(Model theModel) {
		Uzytkownik uzytkownik = new Uzytkownik();
		
		theModel.addAttribute("uzytkownik2", uzytkownik);
		return "register";
	}
	
	@RequestMapping("/login")
	public String login(Model theModel) {
		Uzytkownik uzytkownik = new Uzytkownik();
		
		theModel.addAttribute("uzytkownik", uzytkownik);
		return "login";
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(@SessionAttribute("uzytkownik") Uzytkownik uzytkownikSesja, @Valid @ModelAttribute("uzytkownik2") Uzytkownik uzytkownik, Model theModel, RedirectAttributes attr) {
		
		if(uzytkownikSesja.getRola() == Rola.admin) {
			uzytkownik.setRola(Rola.pracownik);
		}
		else {
			uzytkownik.setRola(Rola.klient);
		}
		if(uzytkownikRepository.existsById(uzytkownik.getLogin())) {
			theModel.addAttribute("exists",true);
			return "register";
		}
		Zamowienie zamowienie = new Zamowienie();
		zamowienie.setSealed(false);
		zamowienie.setUzytkownik(uzytkownik);
		uzytkownikRepository.save(uzytkownik);
		zamowienieRepository.save(zamowienie);
		attr.addFlashAttribute("noweKonto", true);
		if(uzytkownikSesja.getRola() == Rola.admin) {
			System.out.println("True");
			return "redirect:/sessionUser";
		}
		else {
			return "redirect:/menu";
		}
	    	
	}
	@RequestMapping("/logUser")
	public String logUser(@Valid @ModelAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
				
		Optional<Uzytkownik> userOptional = uzytkownikRepository.findById(uzytkownik.getLogin());
		if(userOptional.isPresent()) {
			Uzytkownik user = userOptional.get();
			if(uzytkownik.getHaslo().equals(user.getHaslo())) {
				uzytkownik.setRola(user.getRola());
				setUpUserForm(user);
				return "redirect:/sessionUser";
			}
			else {
				theModel.addAttribute("niepoprawneHaslo",true);
			    return "login";			
			}	
		}
		else {
			theModel.addAttribute("loginNieIstnieje",true);
			return "login";
		}
	}
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
				
		status.setComplete();
		return "redirect:/";
	}
	
	
	@RequestMapping("/sessionUser")
	public String showUser(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		theModel.addAttribute("uzytkownik",uzytkownik);
		List<Zamowienie> zamowienia = zamowienieRepository.findByUzytkownik(uzytkownik);
		for(Zamowienie z: zamowienia) {
			if(!z.isSealed()) {
				zamowienia.remove(z);
				break;
			}		
		}
		theModel.addAttribute("zamowienia",zamowienia);
		return "worker-panel";
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(@Valid Model theModel) {
		
		Produkt theProdukt2 = new Produkt();
		List<Kategoria> theKategorias = kategoriaRepository.findAll();
		List<Producent> theProducents = producentRepository.findAll();
		
		if(!theModel.containsAttribute("produkt")) {
			theModel.addAttribute("produkt",theProdukt2);
			System.out.println("Źle");
		}
		else {
			System.out.println("Good czapeczka");
		}
		if(theModel.containsAttribute("bindingErrors")) {
			System.out.println("BindingErros");
		}
		theModel.addAttribute("kategoria",theKategorias);
		theModel.addAttribute("producent",theProducents);
		
		return "produkt-form";
	}
	@RequestMapping("/userList")
	public String userList(Model theModel) {
		List<Uzytkownik> uzytkownicy = uzytkownikRepository.findAll();
		theModel.addAttribute("uzytkownicy",uzytkownicy);
		return "list-users";
	}
	
	@RequestMapping("/editPassword")
	public String editPassword(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		theModel.addAttribute("uzytkownik",uzytkownik);
		return "edit-password";
	}
	@RequestMapping("/editWrongPassword")
	public String editWrongPassword(@ModelAttribute ("niepoprawneHaslo") boolean niepoprawneHaslo, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		theModel.addAttribute("uzytkownik",uzytkownik);
		theModel.addAttribute("niepoprawneHaslo",niepoprawneHaslo);
		return "edit-password";
	}
	@RequestMapping("/savePassword")
	public String savePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik, RedirectAttributes attr) {
		if(oldPassword.contentEquals(uzytkownik.getHaslo())) {
			uzytkownik.setHaslo(newPassword);
			uzytkownikRepository.save(uzytkownik);
			return "redirect:/sessionUser";
		}
		else {
			attr.addFlashAttribute("niepoprawneHaslo", true);
			return "redirect:/editWrongPassword";
		}
	}
 
}
