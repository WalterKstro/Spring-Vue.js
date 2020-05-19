package gt.com.umg.general;

import java.io.Serializable;
import java.util.List;


public interface GenericService<V,K extends Serializable > {
	public V save(V v);
	public void delete(K k);
	public V search(K k);
	public List<V> getAll();
}
