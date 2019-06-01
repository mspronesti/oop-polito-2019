package hydraulic;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;




/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	private List <Element> elementi;
	
	public HSystem(){
		elementi = new ArrayList<>();
	}
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elementi.add(elem);
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to the number of added elements
	 */
	public Element[] getElements(){
		Element[] arrElem = new Element[elementi.size()]; //contenitore
		return  elementi.toArray(arrElem);
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		String layout = "";
		boolean first_time = true;
		int len = 0;
		
		for(Element e : elementi) 
			if(e instanceof Sink) {
				if(first_time) {
					len = layout.length();
					layout += " +->[" +e.getName() + "]"+ e.getClass().getSimpleName() + " |\n" 
								+  String.format("%"+ len +"s", "") ;
					first_time = false;
				}else
					layout +=  " |\n" +  String.format("%"+ len + "s", "") + 
					" +->[" +e.getName() + "]"+ e.getClass().getSimpleName() + " |\n" 
					+  String.format("%"+ len +"s", "") ;
					
			}else
				layout += "[" +e.getName() + "]" 
					+ e.getClass().getSimpleName()
					+ " ->";
			
		
		
		return layout;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		for(Element e : elementi) 
			if(e instanceof Source) {
				e.Simulate(e.getInFlow()); //the source initial flow is outerly initialized
				observer.notifyFlow(e.getClass().getSimpleName(), e.getName(), e.getInFlow(), e.getOutFlow());
			}else 
				observer.notifyFlow(e.getClass().getSimpleName(), e.getName(), e.getInFlow(), e.getOutFlow());	
	}
	
}


