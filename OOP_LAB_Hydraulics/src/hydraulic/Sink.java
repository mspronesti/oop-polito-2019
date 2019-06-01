package hydraulic;


/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {
	
	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);	
	}
	

	@Override
	public void connect (Element elem) {
		System.out.println("Error : you can't connect any element to Sink");
	}
	
	@Override
	/**
	 * whatever amount of flow comes in the Sink will come out
	 */
	public void Simulate(double inFlow) {
		this.inFlow = inFlow;
		this.outFlow = inFlow;
	}
	
}
