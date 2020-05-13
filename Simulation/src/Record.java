
public class Record {

	private Integer SSN;
	private String Name;
	private String address;
	private String phone;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Record(int SSN, String Name, String address, String phone) {

		this.SSN = SSN;
		this.Name = Name;
		this.address = address;
		this.phone = phone;
	}

	public String getRecord(String SSN) {
		return this.Name;
	}

	public Integer getSSN() {
		return SSN;
	}

	public void setSSN(Integer sSN) {
		SSN = sSN;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {

		return "SSN: " + SSN + "\t Name: " + Name + "\t phone: " + phone + "\t address: " + address;

	}

}
