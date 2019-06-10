package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	private TreeMap<String, Patient> patients = new TreeMap<>();
	private TreeMap< Integer, Doctor>  doctors = new TreeMap<>();
	private TreeMap<String, Integer> assignements = new TreeMap<>();
	
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		Patient p = new Patient(first, last, ssn);
		patients.put(ssn , p);
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		if(!patients.containsKey(ssn)) {
			throw new NoSuchPatient();
		}
		
		return patients.get(ssn).getPatient(); 
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Doctor d = new Doctor(first, last, ssn, docID, specialization);
		doctors.put(Integer.valueOf(docID) , d);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		return doctors.get(Integer.valueOf(docID)).getDoctor();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		if(!patients.containsKey(ssn)) {
			throw new NoSuchPatient();
		}else if( !doctors.containsKey(Integer.valueOf(docID)) ) {
			throw new NoSuchDoctor();
		}
		
		assignements.put(patients.get(ssn).getSSN(), doctors.get(Integer.valueOf(docID)).getDocID());
		doctors.get(Integer.valueOf(docID)).incrementPatients();
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
 
	    if(!patients.containsKey(ssn)) {
	    	throw new NoSuchPatient();
	    }
	    
	    if(!assignements.containsKey(ssn)) {
	    	throw new NoSuchDoctor();
	    }
	    
		return assignements.get(ssn);
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		if(!assignements.containsValue(id)) {
			throw new NoSuchDoctor();
		}
		
		return assignements
				.entrySet()
                .stream()
                .filter(p->p.getValue().equals(id))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public void loadData(Reader reader) throws IOException {
		BufferedReader bf = new BufferedReader(reader);
		bf.lines().forEach(line->{
			String[] s = line.split(";");
			
			if(s.length>1) {
				if(s[0].equals("P")) {
					if(s.length > 3) {
						addPatient(s[1], s[2], s[3]);
					}// else the line is skipped
					
				}else if(s[0].equals("M")) {
					if(s.length > 6) {
						addDoctor( s[2], s[3], s[4], Integer.parseInt(s[1]),s[5]);
					} // else the line is skipped
				}
				
			}
					
		});
		
	}




	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		return doctors
				.entrySet()
				.stream()
				.filter(p->{
                	return !assignements.containsValue(p.getKey());
                })
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		Double media = doctors.values().stream().collect(Collectors.averagingDouble(d->d.getNumPatients()));
		return doctors
				.entrySet()
				.stream()
				.filter(p->	p.getValue().getNumPatients() > media)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
				
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		return doctors
				.values()
				.stream()
				.sorted((v,w)-> v.getNumPatients() - w.getNumPatients())
				.map( p -> String.format("%3d : %d %s %s", 
						p.getNumPatients(),
						p.getDocID(), 
						p.getFirst(), 
						p.getFirst()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		// TODO Complete method
		return null;
	}
	
}
