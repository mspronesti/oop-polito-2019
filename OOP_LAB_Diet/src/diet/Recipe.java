package diet;

import java.util.List;
import java.util.ArrayList;


/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	  String name;
	  double calories;
	  double proteins;
	  double carbs;
	  double fat;
	  
	  Food dict_food;
	  
	  List<Ingrediente> ingredienti; 
	  double _tot_qty;
	
	  public Recipe(String name, Food f) {
		  
		  this.name = name;
		  
		  //inizializzazioni ridontanti, solo per chiarezza
		  this.calories = 0;
		  this.proteins = 0;
		  this.carbs = 0;
		  this.fat = 0;
		  this.dict_food = f;
		  
		  ingredienti = new ArrayList<>();
		  _tot_qty = 0;
	  }
	  
	  
	  //inner class 
	  //ogni ingrediente ha i suoi valori nutriz. che poi andranno sommati 
	  //con le relative quantità ai valori nutriz. della ricetta
	  //(tbh più utile per schematizzare che per necessità di implementazione)
	  private class Ingrediente{
		String name;
		double quantity;
		double calories;
		double proteins;
		double carbs;
		double fat;
		
		
		public Ingrediente(String name, double quantity) { 
			this.name = name;
			this.quantity = quantity;
			this.calories = dict_food.getRawMaterial(name).getCalories(); 
			this.proteins = dict_food.getRawMaterial(name).getProteins();
			this.carbs = dict_food.getRawMaterial(name).getCarbs();
			this.fat = dict_food.getRawMaterial(name).getFat();
		}
	
		
		//inutile ai fini delle specifiche, solo per provare
		public String toString() {
			return name + "\t" + quantity + "g";
		}
		
	  }
	  
	  
	 /**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		Ingrediente ingr = new Ingrediente(material, quantity);
		
		this.ingredienti.add(ingr);

		this._tot_qty += ingr.quantity;
		this.proteins += ingr.proteins * quantity/100;
		this.carbs += ingr.carbs * quantity/100;
		this.fat += ingr.fat * quantity/100 ;
		this.calories += ingr.calories * quantity/100;
		
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		return _tot_qty > 100?  calories/_tot_qty * 100 : calories;
	}

	@Override
	public double getProteins() {
		return _tot_qty > 100?  proteins/_tot_qty * 100 : proteins;
	}

	@Override
	public double getCarbs() {
		return _tot_qty > 100?  carbs/_tot_qty * 100 : carbs;
	}

	@Override
	public double getFat() {
		return _tot_qty > 100?  fat/_tot_qty * 100 : fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expressed nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
	    // a recipe expressed nutritional values per 100g
		return true;
	}
	
	//inutile ai fini delle specifiche, solo per provare
	public String toString() {
		String ris = name;
		for(Ingrediente ing : ingredienti) {
			ris += "\n" + ing.toString();
		}
		ris += "\n\n"+"cal" + "\t" + getCalories() + "\n"
				+"prot" + "\t" + getProteins() + "\n" 
				+ "carbo" + "\t" + getCarbs() + "\n"
				+ "fat"	  + "\t" + getFat() + "\n" + _tot_qty;
		return ris;
	}

}
