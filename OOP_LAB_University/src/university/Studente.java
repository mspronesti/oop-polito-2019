package university;

public class Studente {
	public static final int MAX_CORSI_SEGUITI = 25;
	
	private String first, last;
	private int matricola, num_corsi = 0;
	
	private Corso[] corsi_seguiti; 
	
	
	public Studente(String first, String last, int matricola) {
		this.first = first;
		this.last = last;
		this.matricola = matricola;
		corsi_seguiti = new Corso[MAX_CORSI_SEGUITI];
	}

	public String toString() {
		return matricola + " "+ first+ " " + last; 
	}
	
	public void aggiungiCorsoFrequentato(Corso c) {
		corsi_seguiti[num_corsi] = c;
		num_corsi++;
	}
	
	public String corsiFrequentati() {
		String freq = "";
		for(Corso c: corsi_seguiti) {
			if(c == null)
				break;
			freq += (c + "\n");
		}
		return freq;
	}
	
}
