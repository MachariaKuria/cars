package cars;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CarRepository carRepo;

	
	@Resource
	private TypeRepository typeRepo;

	
	
	@Test
	public void shouldSaveAndLoadCar() {
		
		Car car = carRepo.save(new Car("Toyota"));
		long carId = car.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Car> result = carRepo.findById(carId);
		car = result.get();
		
		assertThat(car.getName(),is("Toyota"));
	}

	
	@Test
	public void shouldGenerateCarId() {
		Car car = carRepo.save(new Car("Toyota"));
		long carId = car.getId();
		
		entityManager.flush();
		entityManager.clear();		
		
		assertThat(carId,is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldSaveAndLoadType() {
		Type camry = new Type("Camry","Station Wagon");
		camry = typeRepo.save(camry);
		long camryId = camry.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Type> result = typeRepo.findById(camryId);
		camry = result.get();
		
		assertThat(camry.getName(),is("Camry"));
	}
	
	
	@Test
	public void shouldEstablishCarToModelRelationships() {
		
		Car toyota = carRepo.save(new Car("Toyota"));
		Car mazda = carRepo.save(new Car("Mazda"));
		
		Type suv = new Type("truck","6-speed",toyota,mazda);
		suv = typeRepo.save(suv);
		long suvId = suv.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Type> result = typeRepo.findById(suvId);
		suv = result.get();
		
		assertThat(suv.getCars(),containsInAnyOrder(toyota,mazda));
		
	}
	
	@Test
	public void shouldFindTypesforCar() {
		
		Car nissan = carRepo.save(new Car("Nissan"));
		
		Type sedan = typeRepo.save(new Type("Sedan","automatic",nissan));
		Type fwd = typeRepo.save(new Type("4WD","automatic",nissan));
		
	
		
		Collection<Type> sedanForNissan = typeRepo.findByCarsContains(nissan);
		
		entityManager.flush();
		entityManager.clear();	
		
		assertThat(sedanForNissan,containsInAnyOrder(sedan,fwd));
		
	}
	
	@Test
	public void shouldFindTypesForCarId() {
		
		Car tesla = carRepo.save(new Car("Tesla"));
		long teslaId = tesla.getId();
		
		Type driverless = typeRepo.save(new Type("Salon","automatic",tesla));
		Type electric = typeRepo.save(new Type("2WD","automatic",tesla));
		
		Collection<Type> sedanForNissan = typeRepo.findByCarsId(teslaId);
	
		entityManager.flush();
		entityManager.clear();	
		
		assertThat(sedanForNissan,containsInAnyOrder(driverless,electric));	
	}
	
	
	
}
