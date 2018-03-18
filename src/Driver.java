import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Driver {

	private static Set<String> adultFriends = new HashSet<>();
	private static Set<String> dependentFriends = new HashSet<>();
	private static List<Person> allPeople = new ArrayList<>();

	public Driver() {
		init();
	}

	public void init() {
		new HardCodedDataLoader().loadDataToArrayList(allPeople);
	}

	public void addPersonToNetwork(Person p) {
		allPeople.add(p);
	}

	public Person selectPersonByName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Name cannot by null or empty!");
		}

		for (Person p: allPeople) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}


	public void makeFriends(Adult a1, Adult a2) {
		adultFriends.add(a1.getName() + "-" + a2.getName());
	}

	public void makeFriends(Dependent d1, Dependent d2) {
		if (d1.getAge() < 3) {
			throw new IllegalArgumentException("Dependent " + d1.getName() + " is too young to have a friend.");
		} else if (d2.getAge() < 3) {
			throw new IllegalArgumentException("Dependent " + d2.getName() + " is too young to have a friend.");
		} else if (Math.abs(d2.getAge() - d1.getAge()) > 3) {
			throw new IllegalArgumentException("The age difference between these two young friends cannot be more than 3 years.");
		}

		dependentFriends.add(d1.getName() + "-" + d2.getName());
	}

	public boolean checkFriendship(Adult a1, Adult a2) {
		if (adultFriends.contains(a1.getName() + "-" + a2.getName())) {
			return true;
		}

		if (adultFriends.contains(a2.getName() + "-" + a1.getName())) {
			return true;
		}

		return false;
	}

	public boolean checkFriendship(Dependent d1, Dependent d2) {
		if (dependentFriends.contains(d1.getName() + "-" + d2.getName())) {
			return true;
		}

		if (dependentFriends.contains(d2.getName() + "-" + d1.getName())) {
			return true;
		}

		return false;
	}

	public List<Adult> getAllAdults() {
		return allPeople.stream()
				.filter(p -> p instanceof Adult)
				.map (p -> (Adult) p)
			    .collect(Collectors.toList());
	}

	public List<Dependent> getAllDependents() {
		return allPeople.stream()
				.filter(p -> p instanceof Dependent)
				.map (p -> (Dependent) p)
			    .collect(Collectors.toList());
	}

	public List<Person> getAllPeople() {
		return allPeople;
	}

	public void delete(Person person) {
		this.getAllPeople().remove(person);
		this.getAllAdults().remove(person);
		this.getAllDependents().remove(person);
	}
}
