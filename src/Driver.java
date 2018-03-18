import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {

	private static Set<String> adultFriends = new HashSet<>();
	private static Set<String> dependentFriends = new HashSet<>();
	private static List<Person> allPeople = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Start..");
	}

	public static void init() {
		Adult ned = new Adult("Ned", 47);
		Adult catelyn = new Adult("Catelyn", 43);
		ned.getMarryWith(catelyn);

		Dependent bran = new Dependent("Bran", 13, ned, catelyn);
		Dependent robb = new Dependent("Robb", 15, ned, catelyn);

		allPeople.add(ned);
		allPeople.add(catelyn);
		allPeople.add(bran);
		allPeople.add(robb);
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


	public void makeAdultFriends(Adult a1, Adult a2) {
		adultFriends.add(a1.getName() + "-" + a2.getName());
	}

	public void makeDependentFriends(Dependent d1, Dependent d2) {
		if (d1.getAge() < 3) {
			throw new IllegalArgumentException("Dependent " + d1.getName() + " is too young to have a friend.");
		} else if (d2.getAge() < 3) {
			throw new IllegalArgumentException("Dependent " + d2.getName() + " is too young to have a friend.");
		} else if (Math.abs(d2.getAge() - d1.getAge()) > 3) {
			throw new IllegalArgumentException("The age difference between these two young friends cannot be more than 3 years.");
		}

		dependentFriends.add(d1.getName() + "-" + d2.getName());
	}
}
