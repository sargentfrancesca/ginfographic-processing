import java.util.Collections;
PFont myFont;

class IngredientChart {
  String name;
  ArrayList<Ingredient> ingredients;
  int ingredientsCount;
  float maxQuantity;
  float minQuantity;
  ArrayList<Ingredient> withQuantityUnit;
  ArrayList<Ingredient> withQuantity;
  ArrayList<Ingredient> noQuantityUnit;
  
  IngredientChart(Recipe recipe, ArrayList<Ingredient> recipeIngredients) {
    name = recipe.name;
    ingredients = recipeIngredients;
    ingredientsCount = ingredients.size();
  }
  
  void populate() {
    
    ArrayList<Float> quants = new ArrayList<Float>();
    withQuantityUnit = new ArrayList<Ingredient>();
    withQuantity = new ArrayList<Ingredient>();
    noQuantityUnit = new ArrayList<Ingredient>();
    
    for (Ingredient ingredient : ingredients) {
      quants.add(float(ingredient.quantity));
      
      minQuantity = Collections.min(quants);
      maxQuantity = Collections.max(quants);
      
      Float quantity = float(ingredient.quantity);
      String unit = ingredient.unit;     
    
      if ((quantity == 0) && (unit.equals("None"))) {
//        println("No Quantity or Unit");
        noQuantityUnit.add(ingredient);        
      } else if ((quantity > 0) && (unit.equals("None"))) {
//        println("Only Quantity Exists");
        withQuantity.add(ingredient);
      } else if (((quantity > 0) && (unit.equals("None") == false ))) {
//        println("Quantity and Unit both exist");
        withQuantityUnit.add(ingredient);
      } else {
        println("fuck knows");
      }      
    
    }
  
  }  
  void print() {
    println("Name: " + name);
    println("Ingredients Count: " + ingredientsCount);
    println("Minimum Quantity: " + minQuantity);
    println("Maximum Quantity: " + maxQuantity);
  }
}

class ChartItem {
  int x;
  int y;
  float size;
  String name;
  Boolean itemUnit;
  color colour;
  
  ChartItem(float xPos, float yPos, float itemSize, String itemName, color itemColour, Boolean newItemUnit) {
    x = int(xPos);
    y = int(yPos);
    size = itemSize;
    name = itemName;
    colour = itemColour;
    itemUnit = newItemUnit;
  }
  
  void draw() {
    fill(colour);
    stroke(255);
    smooth();
    
    if (itemUnit == true) {
      rect(x, y, size, size);
    } else {
      ellipse(x, y, size, size); 
    }
    
    fill(0);
    myFont = createFont("Arial", 12, true);
    textFont(myFont);
    text(name, x, y);
    smooth();
    
  }
}

class Particle{
  /*
    PVector is a class in Processing that makes it easier to store
    values, and make calculations based on these values. It can make
    applying forces to objects much easier and more efficient!
  */
  PVector loc; //location vector
  PVector vel; //velocity vector
  PVector acc; //acceleration vector
  float sz;  //size of particle
  float gravity;  //gravity variable
  float mass;  //mass variable
  int velocityLimit = 5;  //the maximum velocity a particle can travel at
  float d;  //distance variable between particle and the target co-ordinates
  float x;
  float y;
  color colour;
  
  //CONSTRUCTOR
  Particle(PVector _loc, float _sz, float _gravity, float _mass, float _x, float _y){
    loc = _loc.get();  //when calling loc, return current location of the selected particle
    vel = new PVector(0, 0);  //set vel and acc vectors to 0 as default
    acc = new PVector(0, 0);
    sz = _sz;
    gravity = _gravity;
    mass = _mass;
    x = _x;
    y = _y;
  }
   
   
  //method to render the particle. control how it looks here!
  void display(){
    float theta = vel.heading2D() + radians(90);
//    fill(colour);
//    stroke(colour);
//    pushMatrix();
//    translate(loc.x, loc.y);
//    rotate(theta);
//    beginShape(TRIANGLES);
//    vertex(0, -sz*2);
//    vertex(-sz, sz*2);
//    vertex(sz, sz*2);
//    endShape();
//    popMatrix();
    
    ellipseMode(CENTER);
    fill(colour);
    ellipse(loc.x, loc.y, sz, sz);
  }
   
  //math for attraction and repulsion forces
  //tx and ty are the co-ordinates attraction/repulsion will be applied to
  void forces(float tx, float ty, float d){
    PVector targetLoc = new PVector(tx, ty);  //creating new vector for attractive/repulsive x and y values
    PVector dir = PVector.sub(loc, targetLoc);  //calculate the direction between a particle and targetLoc
    d = dir.mag();  //calculate how far away the particle is from targetLoc
    dir.normalize();  //convert the measurement to a unit vector
     
    //calculate the strength of the force by factoring in a gravitational constant and the mass of a particle
    //multiply by distance^2
    float force = (gravity*mass) / (d*d);
     
    //if the mouse is pressed, turn on repulsion by multiplying direction by 1
    if(mousePressed){
      dir.mult(1);
    }
    //else multiply the direction by -1 to switch the direction the particle travels in (attraction)
    else{
      dir.mult(-1);
    }
    
    if (key == 'o') {
      dir.mult(-1);
    } else {
      dir.mult(1);
    }
    
    
    //apply directional vector
    applyForce(dir);
  }
   
  //method to apply a force vector to the particle
  void applyForce(PVector force){
    force.div(mass);
    acc.add(force);
  }
   
  //method to update the location of the particle, and keep its velocity within a set limit
  void update(){
    vel.add(acc);
    vel.limit(velocityLimit);
    loc.add(vel);
    acc.mult(0);
  }
   
  //method to bounce particles of canvas edges
  void bounds(){
    if(loc.y > height || loc.y < 0){
      vel.y *= -1;
    }
    if(loc.x > width || loc.x < 0){
      vel.x *= -1;
    }
  }
   

  //main method that combines all previous methods, and takes two arguments
  //tx and ty are inherited from forces(), and set the attractive/repulsive co-ords
  void run(float tx, float ty, float d){
    forces(tx, ty, d);
    display();
    bounds();
    update();
  }
  
}