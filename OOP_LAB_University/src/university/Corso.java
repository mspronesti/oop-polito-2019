package university;

public class Corso {
	public static final int MAX_ISCRITTI = 100;
	
	private String course, teacher;
	private int code, stud_iscritti = 0;
	
	private Studente[] iscritti;
	
	public Corso(String course, String teacher, int code) {
		this.course = course;
		this.teacher = teacher;
		this.code = code;
		iscritti = new Studente[MAX_ISCRITTI]; 
	}
	
	public String toString() {
		return code + ", " + course + ", "+ teacher; 
	}

	public void aggiungiStudenteFrequentante(Studente s) {
		iscritti[stud_iscritti] = s;
		stud_iscritti++;
	}
	
	public String attendees(){
		String att="";
		for (Studente s : iscritti) {
			if(s == null)
				break;
			
			att += (s + "\n"); 
		}
		return att;
	}
	
}
