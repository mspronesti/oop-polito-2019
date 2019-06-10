package clinic;


public class Doctor {
	private String first;
	private String last;
	private String ssn;
	private int  docID;
	private String specialization;
	private int numpatients;
	
	public Doctor(String first,
	String last,
	String ssn,
	int  docID,
	String specialization) {
		this.first = first;
		this.last = last;
		this.ssn = ssn;
		this.docID = docID;
		this.specialization = specialization;
		this.numpatients = 0;
	}
	
	public String getDoctor() {
		return last + " " + first + "(" + ssn + ")" + "[" + docID + "]" + specialization;
	}
	
	public int getDocID() {
		return docID;
	}
	
	public String getFirst() {
		return first;
	}
	
	
	public int getNumPatients() {
		return numpatients;
	}
	
	public void incrementPatients() {
		numpatients++;
	}
}
