import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniNet {

	private static final String MENU = "\nMiniNet Menu\n ===================================\n"
			+ "1. Add a person into the network\n"
			+ "2. Select a person by name and display his/her profile\n"
			+ "3. Show all people\n"
			+ "4. Show all adults\n"
			+ "5. show all dependents\n"
			+ "6. Update the profile information of the selected person\n"
			+ "7. Delete the selected person\n"
			+ "8. Connect two persons\n"
			+ "9. Find out whether a person is a direct friend of another person\n"
			+ "10. Exit";

	private static final String ENTER_HINT = "\n(enter two numbers separated by space e.g. \"2 14\")";

	public static void main(String[] args) {
		Driver driver = new Driver();
		String input = "";
		while (true) {
			System.out.println(MENU);
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextLine().trim();
			switch (input) {
			case "1":
				System.out.println("Enter in the following format: name, age");
				input = scanner.nextLine().trim();
				Pattern p = Pattern.compile("\\w+,\\d+");
				Matcher m = p.matcher(input.replace(" ", ""));
				if(!m.matches()) {
					System.out.println("Invalid input!");
				}
				String name = input.replace(" ", "").split(",")[0];
				int age = Integer.parseInt(input.replace(" ", "").split(",")[1]);
				if (age >= 16) {
					driver.addPersonToNetwork(new Adult(name, age));
				} else {
					System.out.println("\nChoose two adults as parents" + ENTER_HINT);
					for (int i = 0; i < driver.getAllAdults().size(); i++) {
						System.out.println(i + ". " + driver.getAllAdults().get(i).getName());
					}
					input = scanner.nextLine().trim();
					p = Pattern.compile("\\d \\d");
					m = p.matcher(input);
					if(!m.matches()) {

					}
					int num1 = Integer.parseInt(input.split(" ")[0]);
					int num2 = Integer.parseInt(input.split(" ")[1]);
					if (num1 < 0 || num1 > driver.getAllAdults().size()-1) {
						System.out.println("Invalid input!");
					} else if (num2 < 0 || num2 > driver.getAllAdults().size()-1) {
						System.out.println("Invalid input!");
					} else {
						driver.addPersonToNetwork(new Dependent(name, age,driver.getAllAdults().get(num1),
								driver.getAllAdults().get(num2)));
					}

				}
				break;
			case "2":
				System.out.println("Enter name:");
				input = scanner.nextLine().trim();
				Person person = driver.selectPersonByName(input);
				if (person != null) {
					if (person instanceof Adult) {
						System.out.println(person);
					} else {
						System.out.println(person);
					}
				} else {
					System.err.println("Cannot find " + input);
				}
				break;
			case "3":
				for (int i = 0; i < driver.getAllPeople().size(); i++) {
					System.out.println(i + ". " + driver.getAllPeople().get(i).getName());
				}
				break;
			case "4":
				for (int i = 0; i < driver.getAllAdults().size(); i++) {
					System.out.println(i + ". " + driver.getAllAdults().get(i).getName());
				}
				break;
			case "5":
				for (int i = 0; i < driver.getAllDependents().size(); i++) {
					System.out.println(i + ". " + driver.getAllDependents().get(i).getName());
				}
				break;
			case "6":
				for (int i = 0; i < driver.getAllPeople().size(); i++) {
					System.out.println(i + ". " + driver.getAllPeople().get(i).getName());
				}
				System.out.println("Select the person by id number");
				input = scanner.nextLine().trim();
				int num1 = Integer.parseInt(input);
				if (num1 < 0 || num1 > driver.getAllAdults().size()-1) {
					System.out.println("Invalid input!");
				} else {
					System.out.println(driver.getAllAdults().get(num1));
					System.out.println("Enter the new status");
					input = scanner.nextLine().trim();
					driver.getAllAdults().get(num1).setStatus(input);
					System.out.println(driver.getAllAdults().get(num1));
				}
				break;
			case "7":
				System.out.println("Enter name:");
				input = scanner.nextLine().trim();
				person = driver.selectPersonByName(input);
				if (person != null) {
					if (person instanceof Adult) {
						if (((Adult)person).delete()) {
							driver.delete(person);
						}
					} else {
						System.out.println(person);
					}
				} else {
					System.err.println("Cannot find " + input);
				}
				break;
			case "8": break;
			case "9": break;
			case "10":
				System.exit(0);
			default:
				System.err.println("Invalid input, please enter 1-7.");
			}
		}

	}
}
