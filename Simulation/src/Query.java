import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Query {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter number of nodes for partition");
		String numOfNodesAsString = scan.nextLine();
		int numOfNodes = Integer.parseInt(numOfNodesAsString);

		System.out.println("Enter number of records for partition");
		String numOfRecordsAsString = scan.nextLine();
		int numOfRecords = Integer.parseInt(numOfRecordsAsString);

		PrimaryIndex primary = new PrimaryIndex(numOfNodes, numOfRecords);
		SecondaryIndex secondary = new SecondaryIndex(numOfNodes, numOfRecords);

		// int SSN;
		// String Name;
		Random rnd = new Random();

		String addS = "ab";
		int SSN = 100;
		int namecount = 0;
		int index = 0;
		Record[] temp = new Record[numOfNodes * numOfRecords];
		for (int i = 0; i < numOfNodes; i++) {

			for (int j = 0; j < numOfRecords; j++) {

				/*
				 * System.out.println("Enter Record " + (++records));
				 * 
				 * System.out.println("Enter SSN:\n"); String stringSSN = scan.nextLine(); int
				 * SSN = Integer.parseInt(stringSSN);
				 * 
				 * System.out.println("Enter Name:\n"); String Name = scan.nextLine();
				 */
				namecount = namecount + 1;

				;
				char a = (char) (rnd.nextInt(25) + 1 + 'a');
				String address = "abc";
				String phone = "123";
				temp[index] = new Record(SSN, addS, address, phone);
				index++;
				primary.getNodes()[i].addRecord(new Record(SSN, addS, address, phone));
				secondary.getNodes()[i].addRecord(new Record(SSN, addS, address, phone));
				SSN = SSN + 100;
				addS = addS + a;

			}
		}
		Arrays.sort(temp, (Record n1, Record n2) -> n1.getName().compareTo(n2.getName()));
		System.out.println("Database before partition: ");
		System.out.println(primary.beforePartition());

		System.out.println("\nDatabase after partition:");
		System.out.println(primary.toString());
		primary.setGlobalIndex();
		secondary.setGlobalIndex();

		// System.out.println("List of Global Indices");
		// System.out.println(Arrays.toString(primary.getGlobalIndex()));
		// System.out.println(Arrays.toString(secondary.getGlobalIndex()));

		System.out.println("\n");

		boolean cont = true;
		do {
			System.out.println("Choose one of the following options:");
			System.out.println("1. Search by SSN");
			System.out.println("2. Search  by Name");
			String option = scan.nextLine();
			switch (option) {
			case "1":
				System.out.println("Enter SSN to search:");
				String search = scan.nextLine();
				int searchSSN = Integer.parseInt(search);
				String val = primary.globalSearch(searchSSN);

				System.out.println("\n Global primary index vector\n");
				System.out.println(Arrays.toString(primary.getGlobalIndex()));

				System.out.println("\n");
				System.out.println(val);
				break;
			case "2":
				System.out.println("Enter Name to search:");
				String name = scan.nextLine();
				String found = secondary.globalSearch(name);

				System.out.println("\n Global secondary index vector\n");
				System.out.println(Arrays.toString(secondary.getGlobalIndex()));

				System.out.println("\n");
				System.out.println(found);
				break;
			}

			System.out.println("Would you like to continue? Type yes or no ");
			if (scan.nextLine().equalsIgnoreCase("no")) {
				cont = false;
			}
		} while (cont);

		scan.close();
	}
}
