import java.util.List;

public class HardCodedDataLoader implements DataLoader{

	/**
	 * This is a hard coded data loader, which provides some example data.
	 * I could also create other kind of loaders by implementes Dataloader interface.
	 */
	@Override
	public void loadDataToArrayList(List<Person> list) {
		Adult ned = new Adult("ned", 47);
		Adult catelyn = new Adult("catelyn", 43);
		ned.getMarryWith(catelyn);

		Dependent bran = new Dependent("bran", 13, ned, catelyn);
		Dependent robb = new Dependent("robb", 15, ned, catelyn);
		Dependent arya = new Dependent("arya", 10, ned, catelyn);
		Dependent sansa = new Dependent("sansa", 7, ned, catelyn);
		Dependent rickon = new Dependent("rickon", 4, ned, catelyn);

		Adult cersei = new Adult("cersei", 40);
		Adult jaime = new Adult("jaime", 43);

		Dependent joffrey = new Dependent("joffrey", 4, cersei, jaime);
		Dependent tommen = new Dependent("tommen", 14, cersei, jaime);
		Dependent myrcella = new Dependent("myrcella", 15, cersei, jaime);


		list.add(ned);
		list.add(catelyn);
		list.add(bran);
		list.add(robb);
		list.add(arya);
		list.add(sansa);
		list.add(rickon);

		list.add(cersei);
		list.add(jaime);
		list.add(joffrey);
		list.add(tommen);
		list.add(myrcella);

	}

}
