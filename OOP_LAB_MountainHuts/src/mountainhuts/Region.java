package mountainhuts;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.List;

public class Region {
	private String name;
	private Collection<String> altitudeRanges;
	private Collection<Municipality> municipalities;
	private Collection<MountainHut> mountainHuts;

	
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
		altitudeRanges = new LinkedList<>();
		municipalities = new LinkedList<>();
		mountainHuts = new LinkedList<>();
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		for(String rang: ranges) {
			altitudeRanges.add(rang);
		}
	}
	

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		String interval=
		altitudeRanges.stream().filter(p->Integer.parseInt(p.split("-")[0]) <= altitude 
				&& Integer.parseInt(p.split("-")[1])>=altitude).findFirst().orElse("0-INF");
		
		return interval;
	}
	

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities;
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainHuts;
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {	 
		return municipalities.stream().filter(p->p.getName().equals(name))
				 .findFirst().orElseGet(()->{
					 Municipality m = new Municipality(name, province, altitude);
					 municipalities.add(m);
					 return m;
					 });
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
			
		return 	 mountainHuts.stream().filter(p->p.getName().equals(name)).
				findFirst().orElseGet(()->{
					MountainHut mH =  new MountainHut(name, category, bedsNumber, municipality);
					mountainHuts.add(mH);
					return mH;	
				});
		
	
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		return 	 mountainHuts.stream().filter(p->p.getName().equals(name)).
				findFirst().orElseGet(()->{
					MountainHut mH =  new MountainHut(name, altitude, category, bedsNumber, municipality);
					mountainHuts.add(mH);
					return mH;	
				});
	}

	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		r.readData(file);
		return r;
	}

	
	private void readData(String file) {
		List<String> lines = null;

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			lines = in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		if (lines == null) return;
		
		String[] intestaz = lines.remove(0).split(";");
		Map<String, Integer> intestazIndex= new HashMap<>();
		
		for(int i=0;i<intestaz.length;++i)
			intestazIndex.put(intestaz[i], i);
		
		lines.forEach(l->{
			String[] riga = l.split(";");
			
			String province = riga[intestazIndex.get("Province")];
			String municipality = riga[intestazIndex.get("Municipality")];
			Integer munAltit = Integer.parseInt(riga[intestazIndex.get("MunicipalityAltitude")]);
			
			String name = riga[intestazIndex.get("Name")];
			String category = riga[intestazIndex.get("Category")];
			Integer bedsNumber = Integer.parseInt(riga[intestazIndex.get("BedsNumber")]);
			String altitude = riga[intestazIndex.get("Altitude")];
			
			
			Municipality mun = createOrGetMunicipality(municipality, province, munAltit);			
			if(altitude.equals("")) {
				createOrGetMountainHut(name, category, bedsNumber, mun);
			}else {
				
				createOrGetMountainHut(name, Integer.parseInt(altitude),category, bedsNumber, mun);
			}
			
		});
		
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * Usage of collect + groupingBy + counting
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		return municipalities
				.stream()
				.collect(Collectors.groupingBy(Municipality::getProvince, 
											     Collectors.counting()));
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		return mountainHuts
				.stream()
				.collect(Collectors.groupingBy( p -> p.getMunicipality().getProvince(),
												Collectors.groupingBy(  p->p.getMunicipality().getName(),
														Collectors.counting() )						)
							);
	}
	
	/**
	 * Function useful for next two methods
	 */
	Function<MountainHut,String> altitudeRange= p->{
		if(p.getAltitudeAsInteger()==-1) {
			return getAltitudeRange(p.getMunicipality().getAltitude());
		}else {
			return getAltitudeRange(p.getAltitudeAsInteger());
		}
		};

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * The stream's pattern is the same of the former methods 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {	
		return mountainHuts.stream().collect(Collectors.groupingBy(
				altitudeRange ,Collectors.counting() 
				));
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * 
	 * !!!!!!!!!!NOTE : Still looking for a stream way to to this!!!!!!!!!!
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return mountainHuts.
				stream().
				collect(Collectors.groupingBy(p->p.getMunicipality().getProvince(), 
				Collectors.summingInt(MountainHut::getBedsNumber)));
	}
	

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		return mountainHuts
				.stream()
				.collect(Collectors.groupingBy(altitudeRange,
						Collectors.mapping(MountainHut::getBedsNumber,
								Collectors.maxBy(Comparator.naturalOrder())
								)
						)
						);
				
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 *         
	 *         
	 * NOTE: This is actually a very hard method to implement
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {	
		return mountainHuts
				.stream()
				.map(p->p.getMunicipality().getName())
				.collect(Collectors.groupingBy(p->p, TreeMap::new, Collectors.counting()))
				.entrySet()
				.stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue,
											   Collectors.mapping(Map.Entry::getKey, 
													              Collectors.toList())));
	}

}
