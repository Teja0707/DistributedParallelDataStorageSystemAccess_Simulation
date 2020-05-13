
public class Node {

	private Record records[];

	public Record[] getRecords() {
		return records;
	}

	public void setRecords(Record[] records) {
		this.records = records;
	}

	Node(int numOfRecords) {
		records = new Record[numOfRecords];

	}

	public void addRecord(Record add) {
		boolean added = false;
		for (int i = 0; i < records.length; i++) {
			if (records[i] == null) {
				records[i] = add;
				added = true;
				break;
			}
		}

		if (!added) {
			System.out.println("Can't add more records: Storage is full");
		}

	}

	public String searchLocal(Integer ssn) {

		String name = "Record not found";

		if (ssn == null) {
			return name;
		} else {
			for (int i = 0; i < records.length; i++) {

				if (ssn.equals(records[i].getSSN())) {

					name = "Local Primary index: " + records[i].getSSN() + "\nName: " + records[i].getName();
				}

			}
			return name;
		}
	}

	public String searchLocal(String name) {

		String returnName = "Record not found";

		if (name == null) {
			return returnName;
		} else {
			for (int i = 0; i < records.length; i++) {

				if (name.equals(records[i].getName())) {

					returnName = "Local Primary index: " + records[i].getSSN() + "\nName: " + records[i].getName();
				}

			}
			return returnName;
		}
	}
	@Override
	public String toString() {

		String toReturn = "";
		for (int i = 0; i < records.length; i++) {

			toReturn += records[i].toString() + "\n";
		}
		return toReturn;

	}
	
	public String beforePartition() {

		String toReturn = "";
		for (int i = 0; i < records.length; i++) {

			toReturn += records[i].toString();
			if(i!=records.length-1)
			{
				toReturn += "\n";
			}
		}
		return toReturn;

	}
}
