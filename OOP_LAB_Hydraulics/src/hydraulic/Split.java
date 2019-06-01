package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2); //calling second Constructor

	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
        return outputs;

    }
    

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		if(noutput == 0 || noutput == 1)
			outputs[noutput] = elem;
		else {
			System.out.println("Complex elements only have two terminals");
		}
	}
	
	@Override
	public void Simulate(double inFlow) {
		this.inFlow = inFlow;
		this.outFlow = inFlow/2;
		getOutputs()[0].Simulate(this.outFlow);
		getOutputs()[1].Simulate(this.outFlow);
	}
	
}

