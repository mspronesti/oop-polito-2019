package mountainhuts;

public class Municipality {
	private String name;
	private String province;
	private Integer altitude;

	
	public Municipality(String name, String province, Integer altitude) {
		this.name = name;
		this.province = province;
		this.altitude = altitude;
	}
	
	public String getName() {
		return name;
	}

	public String getProvince() {
		return province;
	}

	public Integer getAltitude() {
		return altitude;
	}

}
