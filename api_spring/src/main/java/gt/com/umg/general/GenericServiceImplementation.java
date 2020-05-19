package gt.com.umg.general; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImplementation<V,K extends Serializable> implements GenericService<V, K>{

	@Override
	public V save(V v) {
		return getDao().save(v);
	}

	@Override
	public void delete(K k) {
		getDao().deleteById(k);
	}

	@Override
	public V search(K k) {
		Optional<V> venta = getDao().findById(k);
		if (venta.isPresent()) {
			return venta.get();
		}
		return null;
	}

	@Override
	public List<V> getAll() {
		List<V> results = new ArrayList<>();
		getDao().findAll().forEach(venta -> results.add(venta));
		return results;
	}
	
	public abstract CrudRepository<V, K> getDao();

}
