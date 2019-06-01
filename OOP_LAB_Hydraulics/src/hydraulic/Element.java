package hydraulic;


/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public class Element {

	private String name;
	private Element output;
	protected Element[] outputs; //attribute for complex elements only
	
	protected double inFlow;
	protected double outFlow;
	
	/**
	 * Constructor (for simple elements)
	 * @param name the name of the element
	 */
	public Element(String name){
		this.name = name;
	}
	
	/**
	 * Constructor for complex elements added by me 
	 * @param name
	 * @param nout number of terminals
	 */
	public Element(String name, int nout) {
		this.name = name;
		outputs = new Element[nout];
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		this.output = elem;
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		// TODO: to be implemented
		return output;
	}

	public void Simulate(double inFlow) {
		System.out.println("This is just a virtual method"
				+ "to be overridden");
	}
	
	
	public double getInFlow() {
		return inFlow;
	}

	public double getOutFlow() {
		return outFlow;
	}
	
	
}

