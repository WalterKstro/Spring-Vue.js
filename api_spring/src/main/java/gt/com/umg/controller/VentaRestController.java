package gt.com.umg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.umg.model.Ventas;
import gt.com.umg.services.api.VentaService;

//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
//@CrossOrigin(origins = "*")

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/v1")
public class VentaRestController {

	@Autowired
	private VentaService ventaService;
	
	/**
	 * Listar todos los registros
	 * @return
	 */
	@GetMapping(value="/all")
	public List<Ventas> getAll(){
		return ventaService.getAll();
	}
	
	/**
	 * Guardar y actualizar un registro
	 * @param v
	 * @return
	 */
	@PostMapping(value="/save")
	public ResponseEntity<Ventas> save(@RequestBody Ventas v){
		Ventas venta = ventaService.save(v);
		return new ResponseEntity<Ventas>(venta,HttpStatus.OK);
	}
	
	/**
	 * Borrar un registro
	 * @param id
	 * @return
	 */
	@GetMapping(value="/delete/{id}") 
	public ResponseEntity<Ventas> delete(@PathVariable Integer id){
		Ventas venta = ventaService.search(id);
		if (venta != null) {
			ventaService.delete(id);			
		}else {
			return new ResponseEntity<Ventas>(venta,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Ventas>(venta,HttpStatus.OK);
	}
	
	/**
	 * Buscar un registro
	 * @param id
	 * @return
	 */
	@GetMapping(value="/find/{id}")
	public Ventas search(@PathVariable Integer id) {
		return ventaService.search(id);
	}
	
}
