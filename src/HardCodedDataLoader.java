import java.util.List;

public class HardCodedDataLoader implements DataLoader{

	@Override
	public void loadDataToArrayList(List<Person> list) {
		Adult ned = new Adult("Ned", 47);
		Adult catelyn = new Adult("Catelyn", 43);
		ned.getMarryWith(catelyn);

		Dependent bran = new Dependent("Bran", 13, ned, catelyn);
		Dependent robb = new Dependent("Robb", 15, ned, catelyn);

		list.add(ned);
		list.add(catelyn);
		list.add(bran);
		list.add(robb);

	}

}
