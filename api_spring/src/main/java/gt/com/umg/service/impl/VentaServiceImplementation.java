package gt.com.umg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import gt.com.umg.dao.api.VentaDao;
import gt.com.umg.general.GenericServiceImplementation;
import gt.com.umg.model.Ventas;
import gt.com.umg.services.api.VentaService;

@Service
public class VentaServiceImplementation extends GenericServiceImplementation<Ventas, Integer> implements VentaService{
	
	@Autowired
	private VentaDao ventaDao;
	
	@Override
	public CrudRepository<Ventas, Integer> getDao() {
		return ventaDao;
	}

}
