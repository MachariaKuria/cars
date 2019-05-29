package cars;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {

	Collection<Type> findByCarsContains(Car car);

	Collection<Type> findByCarsId(long Id);

}
