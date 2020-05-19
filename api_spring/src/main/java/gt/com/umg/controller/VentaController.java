package gt.com.umg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import gt.com.umg.model.Ventas;
import gt.com.umg.services.api.VentaService;

@Controller
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	/**
	 * Metodo de listar todos los registros
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list",ventaService.getAll());
		return "index";
	}
	
	/**
	 * Metodo de editar un registro
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/save/{id}")
	public String saveOne(@PathVariable("id") Integer id , Model model) {
		if (id != null && id != 0) {
			model.addAttribute("venta",ventaService.search(id));
		}else {
			model.addAttribute("venta", new Ventas());
		}
		return "save";
	}
	
	/**
	 * Metodo de guardar un registro
	 * @param v
	 * @param model
	 * @return
	 */
	@PostMapping("/save/")
	public String save(Ventas v, Model model) {
		ventaService.save(v);
		return "redirect:/";
	}
	
	
	/**
	 * Metodo que borra un registro	
	 * @param id
	 * @param model
	 * @return
	 */
	public String delete(@PathVariable Integer id,Model model) {
		ventaService.delete(id);
		return "redirect:/";
	}
}
