

package cars;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TypeController {

	
	@Resource
	TypeRepository typeRepo;
	
	@Resource
	CarRepository carRepo;
	
	@RequestMapping("/type")
	public String findOneType(@RequestParam(value="id") long id, Model model) throws TypeNotFoundException {
		Optional<Type> type = typeRepo.findById(id);
		
		if(type.isPresent()) {
			model.addAttribute("types", type.get());
			return "type";
		}
		throw new TypeNotFoundException();
		
	}

	@RequestMapping("/show-types")
	public String findAllTypes(Model model) {
		model.addAttribute("types", typeRepo.findAll());
		return("types");
		
	}
	
	@RequestMapping("/car")
	public String findOneCar(@RequestParam(value="id") long id, Model model) throws CarNotFoundException {
		
		Optional<Car> car = carRepo.findById(id);
		
		if(car.isPresent()) {
			model.addAttribute("cars", car.get());
			return "car";
		}
		throw new CarNotFoundException();
		
	}

	@RequestMapping("/show-cars")
	public String findAllCars(Model model) {
		model.addAttribute("cars", carRepo.findAll());
		return("cars");
	}

}

