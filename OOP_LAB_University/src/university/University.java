package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	
	public static final int MAX_STUDENTI = 1000;
	public static final int FIRST_MATRICOLA = 10000;
	public static final int MAX_INSEGNAMENTI = 50;
	public static final int FIRST_CODICE = 10;
	
	private String name, rett;
	private int currMatricola = 10000;
	private int currCodice = 10;
	
	private Studente[] stud;
	private Corso[] cors;

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name = name;
		stud = new Studente[MAX_STUDENTI];
		cors = new Corso[MAX_INSEGNAMENTI];
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		rett = first + " " + last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		return rett;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		Studente s = new Studente(first,last,currMatricola); //creo studente
		stud[currMatricola - FIRST_MATRICOLA] = s;  		//inserisco nell'array
		currMatricola++;
		return currMatricola - 1 ;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		//andrebbe controllato che l'indice non sfori e che quello studente esista (non punti a null)
		return stud[id - FIRST_MATRICOLA].toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		Corso c = new Corso(title, teacher, FIRST_CODICE); //creo corso
		cors[currCodice - FIRST_CODICE] = c;				//inserisco nell'array di tipo Corso
		currCodice++;
		return currCodice - 1;
	}
	
	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		//andrebbe controllato che l'indice non sfori e che quel corso esista (non punti a null)
		return cors[code-FIRST_CODICE].toString(); 
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Studente s = stud[studentID - FIRST_MATRICOLA];
		Corso c = cors[courseCode - FIRST_CODICE];
		s.aggiungiCorsoFrequentato(c);
		c.aggiungiStudenteFrequentante(s);
		
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		
		return cors[courseCode - FIRST_CODICE].attendees();
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		return stud[studentID - FIRST_MATRICOLA].corsiFrequentati();
		}
}
