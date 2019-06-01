package diet;


/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	  String name;
	  double calories;
	  double proteins;
	  double carbs;
	  double fat;
	  Food dict_food; 
	
	  
	public Menu(String name, Food f) {
		this.name = name;
		dict_food = f;
	}
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
		
	public Menu addRecipe(String recipe, double quantity) {
		this.calories += dict_food.getRecipe(recipe).getCalories() * quantity/100;
		this.carbs += dict_food.getRecipe(recipe).getCarbs() * quantity/100;
		this.proteins += dict_food.getRecipe(recipe).getProteins() * quantity/100;
		this.fat += dict_food.getRecipe(recipe).getFat() * quantity/100;
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		this.calories += dict_food.getProduct(product).getCalories();
		this.carbs += dict_food.getProduct(product).getCarbs();
		this.proteins += dict_food.getProduct(product).getProteins();
		this.fat += dict_food.getProduct(product).getFat();
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
	
	//inutile ai fini delle specifiche, solo per provare
	public String toString() {
		
		String ris = "Menu: "+ name;
		ris += "\n\n"+"cal" + "\t" + getCalories() + "\n"
				+"prot" + "\t" + getProteins() + "\n" 
				+ "carbo" + "\t" + getCarbs() + "\n"
				+ "fat"	  + "\t" + getFat() + "\n";
		return ris;
	}

}
