package clinic;

public class Patient {
	private String first;
	private String last;
	private String SSN;
	
	
	public Patient(String first, String last, String SSN) {
		this.first = first;
		this.last = last;
		this.SSN = SSN;
	
	}
	
	public String getFirst() {
		return first;
	}
	
	public String getLast() {
		return last;
	}
	
	public String getSSN() {
		return SSN;
	}
	
	public String getPatient() {
		return  last+  " " + first +  " (" + SSN + ")";
	}

	

}
