
public class Dependent extends Person {

	public Dependent(String name, int age, Adult p1, Adult p2) {
		super(name, age);
		if (age >= 16) {
			throw new IllegalArgumentException("Too old to be a dependent.");
		}
		parent1 = p1;
		parent2 = p2;

	}

	private Adult parent1;

	private Adult parent2;


}
