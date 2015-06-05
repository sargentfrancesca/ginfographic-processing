import java.util.Map;
class Recipe {
  String name;
  String url;
  String jsonUrl;
  int recipeLength;
  Flavour flavours;
  ArrayList<Ingredient> ingredients;
  
  Recipe(String JSONUrl, String recipeName, int recipeCountLength, String urlAd) {
    jsonUrl = JSONUrl;
    name = recipeName;
    recipeLength = recipeCountLength;
    url = urlAd;  
  }
  
  void display() {
    println(jsonUrl);
    println(name);
    println(url);
    println(recipeLength);
  }
  
  void ingredients() {
    getIngredients(jsonUrl);
  }
  
  void flavours() {
    getFlavours(jsonUrl);
  }
  
  void displayFlavours() {
    flavours.display();
  }
  
  void displayIngredients() {
    for (Ingredient ingredient : ingredients) {
      ingredient.display();
    }
  }
  
  void saveImage() {
    save("images/" + name + ".jpg");
    background(200);
  }
  
  void saveTasteImage() {
    save("taste/" + name + ".jpg");
    background(200);
  }
  
  void addTitle() {
    myFont = createFont("Arial", 12, true);
    textFont(myFont);
    fill(149, 165, 166);
    text(recipe.name, 10, 20);
    smooth();
  }
  

}

class Flavour {
  float bitter;
  float salty;
  float sour;
  float sweet;
  float umami;
  
  Flavour(float recipeBitter, float recipeSalty, float recipeSour, float recipeSweet, float recipeUmami) {
    bitter = recipeBitter;
    salty = recipeSalty;
    sour = recipeSour;
    sweet = recipeSweet;
    umami = recipeUmami;
  }
  
  void display() {
    println("----- Flavours -----");
    println("Bitter: " + bitter);
    println("Salty: " + salty);
    println("Sour: " + sour);
    println("Sweet: " + sweet);
    println("Umami: " + umami);
  }
  
  void highest() {
    StringDict flavourInventory;
    flavourInventory = new StringDict();
    flavourInventory.set ("Bitter", str(bitter));
    flavourInventory.set ("Salty", str(salty));
    flavourInventory.set ("Sour", str(sour));
    flavourInventory.set ("Sweet", str(sweet));
    flavourInventory.set ("Umami", str(umami));
    
    flavourInventory.sortValuesReverse();
    println(flavourInventory);
    String highest = flavourInventory.valueArray()[0];
    println(highest);
  }
}

class Ingredient {
  String name;
  String quantity;
  String unit;
  
  Ingredient(String ingredName, String ingredQuantity, String ingredUnit) {
    name = ingredName;
    quantity = ingredQuantity;
    unit = ingredUnit;
  }
  
  void display() {
    println(quantity + " | " + unit + " | " + name);
  }
}
