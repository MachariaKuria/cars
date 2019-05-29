package cars;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TypeController.class)
public class CarControllerMockMvcTest {

	@Resource		
	private MockMvc mvc;
	
	
	@MockBean
	private CarRepository carRepo;
	
	@MockBean
	private TypeRepository typeRepo;
	
	@Mock
	private Type type;
	
		
	
	@Test
	public void shouldRouteToSingleTypeView() throws Exception {
		long arbitraryTypeId = 1;
		when(typeRepo.findById(arbitraryTypeId)).thenReturn(Optional.of(type));
		mvc.perform(get("/type?id=1")).andExpect(view().name(is("type")));
		
	}
}
