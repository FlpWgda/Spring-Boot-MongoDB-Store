package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.KategoriaRepository;
import com.example.demo.dao.ProducentRepository;
import com.example.demo.dao.ProduktRepository;
import com.example.demo.dao.ZamowienieProduktRepository;
import com.example.demo.dao.ZamowienieRepository;
import com.example.demo.entity.Kategoria;
import com.example.demo.entity.KategoriaEditor;
import com.example.demo.entity.Producent;
import com.example.demo.entity.ProducentEditor;
import com.example.demo.entity.Produkt;
import com.example.demo.entity.Uzytkownik;
import com.example.demo.entity.Zamowienie;
import com.example.demo.entity.ZamowienieProdukt;



@Controller
@RequestMapping("/produkt")
public class ProduktController {
	
	@Autowired
	private ProduktRepository produktRepository;
	
	@Autowired
	private ProducentRepository producentRepository;
	
	@Autowired
	private KategoriaRepository kategoriaRepository;
	
	@Autowired
	private ZamowienieRepository zamowienieRepository;
	
	@Autowired
	private ZamowienieProduktRepository zamowienieProduktRepository;
	
	@RequestMapping("/access")
    public String testAction(@RequestParam("fieldName") String fieldName, Model theModel) {
        // yourValue contain the value post from the html form
    	if(fieldName.equals("Admin")) {
    		return "redirect:/produkt/list";
    	}
    	else if(fieldName.equals("Worker")) {
    		return "redirect:/produkt/list2";
    	}
    	else{
    		return "redirect:/produkt/list3";
    	}
    }
	
	@RequestMapping("/list")
	public String listProdukts(Model theModel) {
		
		
		List<Produkt> theProdukts = produktRepository.findByAvailable(false);
		
		theModel.addAttribute("produkts", theProdukts);
		
		return "list-produkts";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(@Valid Model theModel) {
		
		Produkt theProdukt2 = new Produkt();
		List<Kategoria> theKategorias = kategoriaRepository.findAll();
		List<Producent> theProducents = producentRepository.findAll();
		List<String> theKat = new ArrayList<String>();
		for(Kategoria k: theKategorias) {
			theKat.add(k.getNazwaKat());
		}
		
		if(!theModel.containsAttribute("produkt")) {
			theModel.addAttribute("produkt",theProdukt2);
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
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("produktId") String theId, Model theModel) {
		Produkt theProdukt = produktRepository.findByIdP(theId);
		List<Kategoria> theKategorias = kategoriaRepository.findAll();
		List<Producent> theProducents = producentRepository.findAll();
		List<String> theKat = new ArrayList<String>();
		for(Kategoria k: theKategorias) {
			theKat.add(k.getNazwaKat());
		}
		theModel.addAttribute("produkt",theProdukt);
		theModel.addAttribute("kategoria",theKategorias);		
		theModel.addAttribute("producent",theProducents);
		
		return "produkt-form";
	}
	
	
	@GetMapping("/delete")
	public String deleteProdukt(@RequestParam("produktId") String theId) {
		
		Produkt theProdukt = produktRepository.findByIdP(theId);
		theProdukt.setAvailable(true);
		produktRepository.save(theProdukt);
		return "redirect:/produkt/list";
	}
	@GetMapping("/details")
	public String details(@RequestParam("produktId") String theId, Model theModel) {
		
		Produkt theProdukt = produktRepository.findByIdP(theId);
		theModel.addAttribute("produkt",theProdukt);
		return "produkt-details";
	}
	@RequestMapping("/saveProdukt")
	public String saveProdukt(Model theModel, @Valid @ModelAttribute("produkt") Produkt theProdukt, BindingResult result, RedirectAttributes attr, HttpSession session) {
		
		Produkt theProdukt2 = new Produkt();
		theProdukt2.setCena(new BigDecimal(0.00));
		List<Kategoria> theKategorias = kategoriaRepository.findAll();
		List<Producent> theProducents = producentRepository.findAll();
		
		theModel.addAttribute("produkt",theProdukt2);
		theModel.addAttribute("kategoria",theKategorias);
		theModel.addAttribute("producent",theProducents);
		
		
		if(result.hasErrors()) {
			System.out.println("Sa bledy");
			attr.addFlashAttribute("org.springframework.validation.BindingResult.produkt", result);
		    attr.addFlashAttribute("produkt", theProdukt);
			return "redirect:/produkt/showFormForAdd";
		}
		
		else {
			produktRepository.save(theProdukt);
			return "redirect:/produkt/list";
		}		
		
	}
	@RequestMapping("/saveProdukt2")
	public String saveProdukt2(@Valid @ModelAttribute("produkt") Produkt theProdukt, BindingResult result) {
		
		
		if(result.hasErrors()) {
			return "redirect:/produkt/showFormForAdd2";
		}
		
		else {
			produktRepository.save(theProdukt);
			return "redirect:/produkt/list2";
		}		
		
	}
	@RequestMapping("/basket")
	public String basket(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		List<ZamowienieProdukt> temp = new ArrayList<ZamowienieProdukt>();
		/**if(zamowienie.getZamowienieProdukt() != null) {
			for(ZamowienieProdukt zp2: zamowienie.getZamowienieProdukt()) {
				boolean tmp = false;
				for(ZamowienieProdukt zp3: temp) {
					if(zp2.getId_produktu().getIdP().equals(zp3.getId_produktu().getIdP())) {
						tmp = true;
						break;
					}
				}
				if(tmp = false) {
					temp.add(zp2);
				}
			}
			zamowienie.setZamowienieProdukt(temp);
			System.out.println("temp:" + temp);
			zamowienieRepository.save(zamowienie);
		}**/
		
		double cena = 0.00;
		if(zamowienie.getZamowienieProdukt() != null) {
			for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
				cena += zp.getIlosc()* zp.getId_produktu().getCena().doubleValue();
			}
		}
		
		theModel.addAttribute("uzytkownik",uzytkownik);
		theModel.addAttribute("zamowienie",zamowienie);
		theModel.addAttribute("cena", cena);
		return "basket";
	}
	@RequestMapping("/showFormForOrderAdd")
	public String formForOrderAdd(@RequestParam("produktId") int theId, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		ZamowienieProdukt zamowienieProdukt = new ZamowienieProdukt();
		// dodaj szukanie produktu w koszyku
		
		theModel.addAttribute("zamowienieProdukt",zamowienieProdukt);
		return "produkt-zamowienie-form";
	}
	@RequestMapping("/addToBasket")
	public String addToBasket(@RequestParam("produktId") String theId,@RequestParam("ilosc") int ilosc, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		ZamowienieProdukt zamowienieProdukt = new ZamowienieProdukt();
		Produkt produkt = produktRepository.findByIdP(theId);
		int ile = produkt.getLiczbaSztuk();
		int addIlosc;
		if(ilosc > ile) {
			addIlosc = ile;
			produkt.setLiczbaSztuk(0);
			produktRepository.save(produkt);
		}
		else {
			addIlosc = ilosc;
			produkt.setLiczbaSztuk(ile-ilosc);
			produktRepository.save(produkt);
		} 
		zamowienieProdukt.setId_produktu(produkt);
		zamowienieProdukt.setIlosc(addIlosc);
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		zamowienieProdukt.setId_produkt_zamowienie(produkt.getIdP());
		
		if(zamowienie.getZamowienieProdukt() != null) {
			for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
				if(zp.getId_produktu().getIdP().equals(produkt.getIdP())) {
					zp.setIlosc(zp.getIlosc()+addIlosc);
					zamowienieRepository.save(zamowienie);
					return "redirect:/produkt/basket";
				}
			}
		}
		zamowienie.addToList(zamowienieProdukt);
		zamowienieRepository.save(zamowienie);
				
		theModel.addAttribute("zamowienieProdukt",zamowienieProdukt);
		return "redirect:/produkt/basket";
	}
	@RequestMapping("/showFormForOrderUpdate")
	public String formForOrderUpdate(@RequestParam("produktZamowienieId") String theId, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik, Model theModel) {
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		ZamowienieProdukt zamowienieProdukt = null;
		for(ZamowienieProdukt zp :zamowienie.getZamowienieProdukt()) {
			if(zp.getId_produkt_zamowienie().equals(theId)) {
				zamowienieProdukt = zp;
				break;
			}
		}
		theModel.addAttribute("zamowienieProdukt",zamowienieProdukt);
		return "produkt-zamowienie-form";
	}
	@RequestMapping("/deleteProduktZamowienie")
	public String deleteProduktZamowienie(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik, @RequestParam("produktZamowienieId") String theId) {
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
		}
		Produkt produkt = null;
		ZamowienieProdukt zmProd = null;
		for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
			
			if(zp.getId_produkt_zamowienie().equals(theId)) {
				zmProd = zp;
				produkt = zp.getId_produktu();
				zamowienie.getZamowienieProdukt().remove(zp);
				zamowienieRepository.save(zamowienie);
				break;
			}
		}
		produkt.setLiczbaSztuk(produkt.getLiczbaSztuk()+zmProd.getIlosc());;
		produktRepository.save(produkt);
		return "redirect:/produkt/basket";
	}
	@RequestMapping("/saveProduktZamowienie")
	public String saveProduktZamowienie(Model theModel, @Valid @ModelAttribute("zamowienieProdukt") ZamowienieProdukt theZamowienieProdukt, @SessionAttribute("uzytkownik") Uzytkownik uzytkownik ) {
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		Produkt produkt = theZamowienieProdukt.getId_produktu();
		int ileMaByc = theZamowienieProdukt.getIlosc();
		int ileBylo = 0;
		int ileJest = produkt.getLiczbaSztuk();
		int addIlosc;
		ZamowienieProdukt zpTemp = null;
		
		for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
			if(zp.getId_produktu().getIdP().equals(produkt.getIdP())) {
				ileBylo = zp.getIlosc();
				zpTemp = zp;
			}
		}
		
		if(ileMaByc-ileBylo > ileJest) {
			addIlosc = ileJest;
			produkt.setLiczbaSztuk(0);
			produktRepository.save(produkt);
		}
		else {
			addIlosc = ileMaByc - ileBylo;
			produkt.setLiczbaSztuk(ileJest-(ileMaByc-ileBylo));
			produktRepository.save(produkt);
		}
		zpTemp.setIlosc(ileMaByc);
		zamowienieRepository.save(zamowienie);
		return "redirect:/produkt/basket";
	}
	
	@RequestMapping("/orderDetails")
	public String orderDetails(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik, @RequestParam("zamowienieId") String theId, Model theModel) {
		Zamowienie zamowienie = zamowienieRepository.findByIdZ(theId);
		double cena = 0.00;
		for(ZamowienieProdukt zp: zamowienie.getZamowienieProdukt()) {
			cena += zp.getIlosc()* zp.getId_produktu().getCena().doubleValue();
		}
		theModel.addAttribute("uzytkownik",uzytkownik);
		theModel.addAttribute("zamowienie",zamowienie);
		theModel.addAttribute("cena", cena);
		return "order-details";
	}
	@RequestMapping("/makeOrder")
	public String makeOrder(@SessionAttribute("uzytkownik") Uzytkownik uzytkownik) {
		Zamowienie zamowienie = zamowienieRepository.findByUzytkownikAndSealed(uzytkownik, false);
		zamowienie.setSealed(true);
		zamowienie.setDataZ(Calendar.getInstance().getTime());
		zamowienieRepository.save(zamowienie);
		
		Zamowienie zamowienie2 = new Zamowienie();
		zamowienie2.setSealed(false);
		zamowienie2.setUzytkownik(uzytkownik);
		zamowienieRepository.save(zamowienie2);
		return "redirect:/sessionUser";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder)     {
	      binder.registerCustomEditor(List.class,new KategoriaEditor(kategoriaRepository));
	      binder.registerCustomEditor(Producent.class, new ProducentEditor(producentRepository));
	}
	
	
}
