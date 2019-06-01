package mountainhuts;

import java.util.Optional;

public class MountainHut {
	private String name;
	private String category;
	private Integer bedsNumber;
	private Integer altitude;
	private Municipality municipality;
	
	
	public MountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		this.name = name;
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
		this.altitude = -1; //default if altitude is not specified. Range will be set to [0-INF]
	}
	
	public MountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		this.name = name;
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
		this.altitude = altitude;
	}
	
	public String getName() {
		return name;
	}

	public Optional<Integer> getAltitude() {
		return altitude == null ? Optional.empty() : Optional.of(altitude);
	}
	
	public Integer getAltitudeAsInteger() {
		return altitude;
	}

	public String getCategory() {
		return category;
	}

	public Integer getBedsNumber() {
		return bedsNumber;
	}

	public Municipality getMunicipality() {
		return municipality;
	}
	
}
