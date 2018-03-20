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

	/**
	 * Init connections
	 */
	public void init() {
		new HardCodedDataLoader().loadDataToArrayList(allPeople);
		makeFriends(selectPersonByName("bran"), selectPersonByName("rickon"));
		makeFriends(selectPersonByName("sansa"), selectPersonByName("rickon"));
		makeFriends(selectPersonByName("arya"), selectPersonByName("sansa"));
		makeFriends(selectPersonByName("robb"), selectPersonByName("sansa"));
		makeFriends(selectPersonByName("joffrey"), selectPersonByName("tommen"));
	}


	/**
	 * add person to the list
	 * @param p
	 */
	public void addPersonToNetwork(Person p) {
		allPeople.add(p);
	}

	/**
	 * search a person by name
	 * @param name
	 * @return
	 */
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


	/**
	 * two adults make friend
	 * @param a1
	 * @param a2
	 */
	public void makeFriends(Adult a1, Adult a2) {
		adultFriends.add(a1.getName() + "-" + a2.getName());
	}

	/**
	 * two dependent make friend
	 * @param d1
	 * @param d2
	 */
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

	/**
	 * two person make friend
	 * @param p1
	 * @param p2
	 */
	public void makeFriends(Person p1, Person p2) {
		if (p1 instanceof Adult && p2 instanceof Adult) {
			makeFriends((Adult)p1, (Adult)p2);
			System.out.println("Adult " + p1.getName()+ " and " + p2.getName() + " are friends now.");
			return;
		}

		if (p1 instanceof Dependent && p2 instanceof Dependent) {
			makeFriends((Dependent)p1, (Dependent)p2);
			System.out.println("Dependent " + p1.getName()+ " and " + p2.getName() + " are friends now.");
			return;
		}
		System.out.println("Adult cannot make friends with dependent.");

	}

	/**
	 * check if two adults are friends
	 * @param a1
	 * @param a2
	 * @return
	 */
	public boolean checkFriendship(Adult a1, Adult a2) {
		if (adultFriends.contains(a1.getName() + "-" + a2.getName())) {
			return true;
		}

		if (adultFriends.contains(a2.getName() + "-" + a1.getName())) {
			return true;
		}

		return false;
	}

	/**
	 * check if two dependents are friends
	 * @param d1
	 * @param d2
	 * @return
	 */
	public boolean checkFriendship(Dependent d1, Dependent d2) {
		if (dependentFriends.contains(d1.getName() + "-" + d2.getName())) {
			return true;
		}

		if (dependentFriends.contains(d2.getName() + "-" + d1.getName())) {
			return true;
		}

		return false;
	}

	/**
	 * check if two person are friends
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean checkFriendship(Person p1, Person p2) {
		if (p1 instanceof Adult && p2 instanceof Adult) {
			return checkFriendship((Adult)p1, (Adult)p2);
		}

		if (p1 instanceof Dependent && p2 instanceof Dependent) {
			return checkFriendship((Dependent)p1, (Dependent)p2);
		}

		return false;
	}


	/**
	 * get all adults
	 * @return
	 */
	public List<Adult> getAllAdults() {
		return allPeople.stream()
				.filter(p -> p instanceof Adult)
				.map (p -> (Adult) p)
			    .collect(Collectors.toList());
	}

	/**
	 * get all dependents
	 * @return
	 */
	public List<Dependent> getAllDependents() {
		return allPeople.stream()
				.filter(p -> p instanceof Dependent)
				.map (p -> (Dependent) p)
			    .collect(Collectors.toList());
	}

	/**
	 * get all people
	 * @return
	 */
	public List<Person> getAllPeople() {
		return allPeople;
	}

	/**
	 * delete a person from three lists
	 * @param person
	 */
	public void delete(Person person) {
		this.getAllPeople().remove(person);
		this.getAllAdults().remove(person);
		this.getAllDependents().remove(person);
	}
}
