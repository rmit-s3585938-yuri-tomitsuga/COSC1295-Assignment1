
public class Dependent extends Person {

	public Dependent(String name, int age, Adult p1, Adult p2) {
		super(name, age);
		if (age >= 16) {
			throw new IllegalArgumentException("Too old to be a dependent.");
		}
		parent1 = p1;
		parent2 = p2;
		p1.addChildren(this);
		p2.addChildren(this);

	}

	private Adult parent1;

	private Adult parent2;

	@Override
	public String toString() {
		return getName() + " " + getAge() + " years old. \nParents: " + parent1.getName() + " and " + parent2.getName() + "\nStatus: " + getStatus();
	}

	@Override
	public boolean delete() {
		if (this.parent1 != null) {
			System.out.println(getName() + " cannot be deleted, he/shw still has parents, deletion will break data integrity.");
			return false;
		}

		return false;
	}

}
