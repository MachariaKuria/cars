package cars;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

public class TypeControllerTest {
	
	@InjectMocks
	TypeController underTest;
	
	@Mock
	private Type type;

	@Mock
	private Type anotherType;
	
	@Mock
	private Car car;
	
	@Mock
	private Car anotherCar;
	
	@Mock
	private CarRepository carRepo;
	
	@Mock
	private TypeRepository typeRepo;
	
	@Mock
	private Model model;
	
	@Before 
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddTypeToModel() throws TypeNotFoundException {
		
		long arbitraryTypeId = 1L;	
		when(typeRepo.findById(arbitraryTypeId)).thenReturn(Optional.of(type));
		
		underTest.findOneType(arbitraryTypeId, model);
		verify(model).addAttribute("types",type);
	}

	@Test
	public void shouldAddAllTypesToModel() {
		
		Collection<Type> allTypes = Arrays.asList(type, anotherType);
		when(typeRepo.findAll()).thenReturn(allTypes);
		
		underTest.findAllTypes(model);
		verify(model).addAttribute("types", allTypes);
	}
	
	@Test
	public void shouldAddSingleCarToModel() throws CarNotFoundException {
		
		long arbitraryCarId = 1L;	
		when(carRepo.findById(arbitraryCarId)).thenReturn(Optional.of(car));
		
		underTest.findOneCar(arbitraryCarId, model);
		verify(model).addAttribute("cars",car);		
	}
	
	@Test
	public void shouldAddAllCarsToModel() {
		
		Collection<Car> allCars = Arrays.asList(car, anotherCar);
		when(carRepo.findAll()).thenReturn(allCars);
		
		underTest.findAllCars(model);
		
		
	}
	
}

