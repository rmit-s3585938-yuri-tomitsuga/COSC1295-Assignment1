import java.util.ArrayList;
import java.util.List;

public class Adult extends Person{

	private Adult spouse;

	private List<Dependent> children;

	public Adult(String name, int age) {
		super(name, age);
		if (age < 16) {
			throw new IllegalArgumentException("Too young to be an adult.");
		}

	}

	/**
	 * @return the spouse
	 */
	public Adult getSpouse() {
		return spouse;
	}

	/**
	 * @param spouse the spouse to set
	 */
	public void getMarryWith(Adult spouse) {
		if (this.spouse != null) {
			throw new IllegalArgumentException(this.getName()
								+ " is married with " + this.getSpouse().getName());
		} else if (this.spouse.getSpouse() != null) {
			throw new IllegalArgumentException(this.getSpouse().getName()
								+ " is married with "
								+ this.getSpouse().getSpouse().getName());
		}

		this.spouse = spouse;
		spouse.getMarryWith(this);
	}

	/**
	 * @return the children
	 */
	public List<Dependent> getChildren() {
		if (children == null) {
			children = new ArrayList<Dependent>();
		}
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void addChildren(Dependent child) {
		getChildren().add(child);
	}


}
