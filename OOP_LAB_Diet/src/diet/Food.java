package diet;

import java.util.Collection;
import java.util.TreeMap;



/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {
	//Tree Map per aver già l'ordine garantito
	private TreeMap<String, NutritionalElement> rawMat; 
	private TreeMap<String, NutritionalElement> prod;
	private TreeMap<String, NutritionalElement> rec;
	
	public Food() {
		rawMat = new TreeMap<String, NutritionalElement>();
		prod = new TreeMap<String, NutritionalElement>();
		rec = new TreeMap<String, NutritionalElement>();
	}
	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */
	public void defineRawMaterial(String name,
									  double calories,
									  double proteins,
									  double carbs,
									  double fat){
		RawMaterial r = new RawMaterial(name, calories, proteins, carbs, fat);
		rawMat.put(name, r); //posso aggiungere r perchè RawMaterial implementa NutritionalElement

	}
	
	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials(){
		return rawMat.values(); //posso farlo perchè rawMaterial implementa NutritionalElement
	}
	
	/**
	 * Retrieves a specific raw material, given its name
	 * @param name  name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public  NutritionalElement getRawMaterial(String name){
		return (NutritionalElement)rawMat.get(name); //cast richiesto dal tipo, ma non necessario per compilare
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	public void defineProduct(String name,
								  double calories,
								  double proteins,
								  double carbs,
								  double fat){
		Product newprod = new Product(name, calories, proteins, carbs, fat);
		prod.put(name, newprod);
	}
	
	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products(){
		return prod.values();
	}
	
	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name){
		return (NutritionalElement)prod.get(name);
	}
	
	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe r = new Recipe(name, this); //passo questo oggetto per permettere di usare i dizionari 
		rec.put(name, r);
		return r;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes(){
		return rec.values();
	}
	
	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name){		
		return (NutritionalElement)rec.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m = new Menu(name, this);
		return m;
	}
	
}
