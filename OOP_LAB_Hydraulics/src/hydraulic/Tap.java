package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {
	boolean open;
	
	

	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		this.open = open;
	}
	
	@Override
	public void Simulate(double inFlow) {
		this.inFlow = inFlow;
		this.outFlow = open? inFlow:0;
		getOutput().Simulate(this.outFlow);
	}

}

