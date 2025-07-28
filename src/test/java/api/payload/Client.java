package api.payload;

public class Client {
	String id;
	String name;
	String contactPerson;
	String phone;
	String email;
	String address;
	String gstNumber;
	CompanyType companyType = CompanyType.corporate;
	public enum CompanyType  {corporate,individual};
	String notes;
	Status status = Status.active;
	public enum Status {active,inactive}
	PrefferedContactMethod prefferedContactMethod = PrefferedContactMethod.email;
	public enum PrefferedContactMethod {email,phone};
	String [] tags;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public CompanyType getCompanyType() {
		return companyType;
	}
	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PrefferedContactMethod getPrefferedContactMethod() {
		return prefferedContactMethod;
	}
	public void setPrefferedContactMethod(PrefferedContactMethod prefferedContactMethod) {
		this.prefferedContactMethod = prefferedContactMethod;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
}
