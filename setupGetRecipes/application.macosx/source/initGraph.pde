IngredientChart chart;
ChartItem chartItem;

void initIngredients() {  
  ArrayList<Ingredient> recipeIngredients = recipe.ingredients;
  
  chart = new IngredientChart(recipe, recipeIngredients);
  chart.populate();  
}

void drawGraph() {
  int neitherCount = 0;
  for (Ingredient ingredient : chart.noQuantityUnit) {
    neitherCount++;
    float neitherCountxPos = random(0, 500);
    float neitherCountyPos = random(0, 300);
    float neitherCountSize = 30;
    String neitherCountName = ingredient.name;
    color neitherCountColour = color(231, 76, (60 + (neitherCount * 10)));
    Boolean neitherCountItemUnit = false;
    
    chartItem = new ChartItem(neitherCountxPos, neitherCountyPos, neitherCountSize, neitherCountName, neitherCountColour, neitherCountItemUnit);
    chartItem.draw();
  }
  
  int quantityCount = 0;
  for (Ingredient ingredient : chart.withQuantity) {
    quantityCount++;
    
    float quantityCountxPos;    
    float x = random(0, 500);
    float quantityCountyPos = random(0, 300);
    float quantityCountSize = 30;
    String quantityCountName = "";
    color quantityCountColour = color(142, 68, (173 + (quantityCount * 20)));
    Boolean quantityCountItemUnit = false;
    
    for (int a = 0; a < int(ingredient.quantity); a++) {
      quantityCountxPos = x + (a*10);    
      chartItem = new ChartItem(quantityCountxPos, quantityCountyPos, quantityCountSize, quantityCountName, quantityCountColour, quantityCountItemUnit);
      chartItem.draw();
    }
    
    myFont = createFont("Arial", 12, true);
    textFont(myFont);
    text(ingredient.name, x, quantityCountyPos);
  }
  
  int bothCount = 0;
  for (Ingredient ingredient : chart.withQuantityUnit) {
    bothCount++;
    
    float bothCountxPos = random(0, 500);
    float bothCountyPos = random(0, 300);
    float bothCountSize = float(ingredient.quantity) * 20;
    String bothCountName = ingredient.name;
    color bothCountColour = color(39, 174, (96 + (bothCount * 10)));
    Boolean bothCountItemUnit = true;
        
    chartItem = new ChartItem(bothCountxPos, bothCountyPos, bothCountSize, bothCountName, bothCountColour, bothCountItemUnit);
    chartItem.draw();
    
  }
}