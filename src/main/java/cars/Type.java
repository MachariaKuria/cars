package cars;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Type {

	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String features;
	
	
	@ManyToMany
	private Collection<Car> cars;
	
	public long getId() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getFeatures() {
		return features;
	}
	
	public Collection<Car> getCars() {
		
		return cars;
	}

	public Type() {
		
	}

	public Type(String name, String features, Car...cars) {
		this.name = name;
		this.features = features;
		this.cars = new HashSet<>(Arrays.asList(cars));
	}




}
