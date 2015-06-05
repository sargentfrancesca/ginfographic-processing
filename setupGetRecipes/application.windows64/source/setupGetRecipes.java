import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import java.util.Collections; 
import java.util.Map; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class setupGetRecipes extends PApplet {


ControlP5 cp5;

PImage saltyPic;
PImage midSaltyPic;
PImage rightSaltyPic;
PImage sweetPic;
PImage sourPic;
PImage leftSourPic;
PImage bitterPic;
PImage umamiPic;

MyControlListener myListener;
FlavourControlListener flavListener;
Knob bitterKnob;
Knob saltyKnob;
Knob sourKnob;
Knob sweetKnob;
Knob umamiKnob;
Textlabel drinkName;
Textarea myConsole;

Println console;


Recipe recipe;
Flavour flavours;
Ingredient[] myIngredients;
JSONObject data;
JSONObject ingredientData;
JSONObject flavourData;
JSONObject singleRecipe;

PImage bg;
int y;

ArrayList<Particle> _bitter;
ArrayList<Particle> _sour;
ArrayList<Particle> _sweet;
ArrayList<Particle> _salty;
ArrayList<Particle> _umami;
ArrayList<Particle> _leftSalty;
ArrayList<Particle> _rightSalty;
ArrayList<Particle> _midSalty;
ArrayList<Particle> _leftSour;
ArrayList<Particle> _rightSour;

int bitterColour = color(210,210,93);
int bitterColourComplement = color(168, 75, 128);
int saltyColour = color(244,206,39);
int saltyColourComplement = color(111, 34, 164);
int sweetColour = color(243,159,44);
int sweetColourComplement = color(37, 103, 157);
int sourColour = color(235,169,55);
int sourColourComplement = color(35, 150, 127);
int umamiColour = color(241,241,63);
int umamiColourComplement = color(170, 225, 59);

public void setup() {
  size(1024, 497, P2D);
//  bg = loadImage("background.png"); 
  cp5 = new ControlP5(this);
  background(10);
  saltyPic = loadImage("salty.png");
  image(saltyPic, (width/3 - 100), (height - height/3) - 150);
  midSaltyPic = loadImage("salty.png");
  image(midSaltyPic, (width/2 - 150), ((height - height/5) -150));
   rightSaltyPic = loadImage("salty.png");
  image(rightSaltyPic, ((width - width/3) -150), ((height - height/3) -150));
  sweetPic = loadImage("sweet.png");
  image(sweetPic, (width/2 - 100), (height - height/5) - 150);
  umamiPic = loadImage("umami.png");
  image(sweetPic, (width/2 - 100), (height/2) - 150);
  sourPic = loadImage("sour.png");
  image(sourPic, ((width - width/4)-100), ((height/3) - 150));
  leftSourPic = loadImage("sour.png");
  image(leftSourPic, width/4 - 100, height/3 - 150);
  bitterPic = loadImage("bitter.png");
  image(bitterPic,  (width/2 - 100), (height/6 - 150));
 frameRate(50); 
  cp5.enableShortcuts();
  noStroke();
  
  myConsole = cp5.addTextarea("txt")
                  .setPosition(width - width/5, height - height/7)
                  .setSize(200, 50)
                  .setFont(createFont("", 10))
                  .setLineHeight(14)
                  .setColor(color(200))
                  .setColorBackground(color(0, 100))
                  .setColorForeground(color(255, 100));
  ;

  console = cp5.addConsole(myConsole);//
  
     
  bitterKnob = cp5.addKnob("abitter")
                 .setRange(0,1)
                 .setValue(0)
                 .setPosition(20,75)
                 .setRadius(20)
                 .setNumberOfTickMarks(10)
                 .setTickMarkLength(2)
                 .snapToTickMarks(true)
                 .setColorForeground(color(210,210,93))
                 .setColorBackground(color(168, 75, 128))
                 .setColorActive(color(255))
                 .setDragDirection(Knob.HORIZONTAL)
                 ;
                 
     saltyKnob = cp5.addKnob("asalty")
                 .setRange(0,1)
                 .setValue(0)
                 .setPosition(20, 150)
                 .setRadius(20)
                 .setNumberOfTickMarks(10)
                 .setTickMarkLength(2)
                 .snapToTickMarks(true)
                 .setColorForeground(color(244,206,39))
                 .setColorBackground(color(111, 34, 164))
                 .setColorActive(color(255))
                 .setDragDirection(Knob.HORIZONTAL)
                 ;
  sourKnob = cp5.addKnob("asour")
                   .setRange(0,1)
                   .setValue(0)
                   .setPosition(20,225)
                   .setRadius(20)
                   .setNumberOfTickMarks(10)
                   .setTickMarkLength(2)
                   .snapToTickMarks(true)
                   .setColorForeground(color(235,169,55))
                   .setColorBackground(color(35, 150, 127))
                   .setColorActive(color(255))
                   .setDragDirection(Knob.HORIZONTAL)
                   ;
     sweetKnob = cp5.addKnob("asweet")
                 .setRange(0,1)
                 .setValue(0)
                 .setPosition(20,300)
                 .setRadius(20)
                 .setNumberOfTickMarks(10)
                 .setTickMarkLength(2)
                 .snapToTickMarks(true)
                 .setColorForeground(color(243,159,44))
                 .setColorBackground(color(37, 103, 157))
                 .setColorActive(color(255))
                 .setDragDirection(Knob.HORIZONTAL)
                 ;
      
      umamiKnob = cp5.addKnob("aumami")
                 .setRange(0,1)
                 .setValue(0)
                 .setPosition(20,375)
                 .setRadius(20)
                 .setNumberOfTickMarks(10)
                 .setTickMarkLength(2)
                 .snapToTickMarks(true)
                 .setColorForeground(color(241,241,63))
                 .setColorBackground(color(170, 225, 59))
                 .setColorActive(color(255))
                 .setDragDirection(Knob.HORIZONTAL)
                 ;
                 
    cp5.addSlider("initiateBudFlock")
           .setRange(1,46)
           .setValue(1)
           .setPosition(20,20)
           .setSize(200,20)
           .setNumberOfTickMarks(10)
           .setSliderMode(Slider.FLEXIBLE);
  
    drinkName = new Textlabel(cp5,recipe.name,20,450,500,200);
    drinkName.setFont(createFont("", 20, true));
    
    bitterKnob.setValue(flavours.bitter);
    saltyKnob.setValue(flavours.salty);
    sourKnob.setValue(flavours.sour);
    umamiKnob.setValue(flavours.umami);
    sweetKnob.setValue(flavours.sweet);
     
   myListener = new MyControlListener();
   flavListener = new FlavourControlListener();
     
   cp5.getController("initiateBudFlock").addListener(myListener);
   cp5.getController("abitter").addListener(flavListener);
   cp5.getController("asalty").addListener(flavListener);
   cp5.getController("asour").addListener(flavListener);
   cp5.getController("asweet").addListener(flavListener);
   cp5.getController("aumami").addListener(flavListener);
     
//  initiateBudFlock(45);
//  budFlock();
  
  
}

public void draw() {

  drinkName.draw(this); 

  budFlock();
//  ingredientDistGraph();
//  noLoop();
//  tasteBudFlock(1);
}

public void tasteBudDistGraph() {
//  for (int l = 1; l < 48; l++) {
//    getRecipe(t);
    initBudDist();
    drawTasteBuds();
//  }
}

public void initiateBudFlock(int i) {
  getRecipe(i);
  flavours = recipe.flavours;
  
  flavours.display();
  flavours.highest();
  
  _bitter = flavourFlockInit(flavours.bitter, width/2, height/6);
  _sweet = flavourFlockInit(flavours.sweet, width/2, height - height/5);
  _leftSalty = flavourFlockInit(flavours.salty, width/3, height - height/3);
  _rightSalty = flavourFlockInit(flavours.salty, width - width/3, height - height/3);
  _midSalty = flavourFlockInit(flavours.salty, width/2, height - height/5);
  _umami = flavourFlockInit(flavours.umami, width/2, height/2);
  _leftSour = flavourFlockInit(flavours.sour, width/4, height/3);
  _rightSour = flavourFlockInit(flavours.sour, width - width/4, height/3);
}

public void budFlock() {
  fill(100, 55);
//  image(bg, 0, 0);
  rect(0, 0, width, height);
  if (keyPressed) {
    filter(ERODE);
  } else {

  }
//  fill(204, 120);
//  rect(0, 0, width, height);
  tasteBudDistGraph();
 
  for (Particle b : _bitter) {
    b.colour = bitterColour;
    b.gravity = flavours.bitter * 10;
    b.mass = flavours.bitter * 10;
    b.run(b.x, b.y, flavours.bitter * 10);
  }
  
  for (Particle s : _sweet) {
    s.colour = sweetColour;
    s.gravity = flavours.sweet * 10;
    s.mass = flavours.sweet * 10;
    s.run(s.x, s.y, flavours.sweet * 10);
  }
  
  for (Particle u : _umami) {
    u.colour = umamiColour;
    u.gravity = flavours.umami * 10;
    u.mass = flavours.umami * 10;
    u.run(u.x, u.y, flavours.umami * 10);
  }
  
  for (Particle lsa : _leftSalty) {
    lsa.colour = saltyColour;
    lsa.gravity = flavours.salty * 10;
    lsa.mass = flavours.salty * 10;
    lsa.run(lsa.x, lsa.y, flavours.salty * 10);
  }
  
  for (Particle rsa : _rightSalty) {
    rsa.gravity = flavours.salty * 10;
    rsa.mass = flavours.salty * 10;
    rsa.colour = saltyColour;
    rsa.run(rsa.x, rsa.y, flavours.salty * 10);
  }
  
  for (Particle msa : _midSalty) {
    msa.gravity = flavours.salty * 10;
    msa.mass = flavours.salty * 10;
    msa.colour = saltyColour;
    msa.run(msa.x, msa.y, flavours.salty * 10);
  }
  
  
  for (Particle ls : _leftSour) {
    ls.colour = sourColour;
    ls.gravity = flavours.sour * 10;
    ls.mass = flavours.sour * 10;
    ls.run(ls.x, ls.y, flavours.sour * 10);
  }
  
  for (Particle rs : _rightSour) {
    rs.colour = sourColour;
    rs.gravity = flavours.sour * 10;
    rs.mass = flavours.sour * 10;
    rs.run(rs.x, rs.y, flavours.sour * 10);
  }
  
}

public void getRecipe(int id) {
  
  String[] url = new String[2];
  url[0] = "http://ginfographic.chez.io/api/v1.0/recipes/";
  url[1] = str(id);
  
  String jsonUrl = join(url, "");
  
  data = loadJSONObject(jsonUrl);
  singleRecipe = data.getJSONObject("recipe");
  
  String recipeName =  singleRecipe.getString("name");
  String recipeCountLengthString = singleRecipe.getString("length");
  int recipeCountLength = PApplet.parseInt(recipeCountLengthString);
  String urlAd = singleRecipe.getString("url");
  
  recipe = new Recipe(jsonUrl, recipeName, recipeCountLength, urlAd);
  recipe.ingredients();
  recipe.displayIngredients();
  recipe.flavours();
//  recipe.displayFlavours();
}

public void saveIt() {
  recipe.saveImage();
}


public void getIngredients(String url) {
  String urls[] = new String[2];
  urls[0] = url;
  urls[1] = "/ingredients/";
  
  String ingredientUrl = join(urls, "");
  ingredientData = loadJSONObject(ingredientUrl);
  
  ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
  JSONArray ingreds = ingredientData.getJSONArray("ingredients");
  
  Ingredient[] myIngredients = new Ingredient[ingreds.size()];
  
  for (int i = 0; i < ingreds.size(); i++) {
    JSONObject ingred = ingreds.getJSONObject(i);
    
    String ingredName = ingred.getString("name");
    String ingredQuantity = ingred.getString("quantity");
    String ingredUnit = ingred.getString("unit");
    
    allIngredients.add(new Ingredient(ingredName, ingredQuantity, ingredUnit));
  }
  
  recipe.ingredients = allIngredients;
}

public void getFlavours(String url) {
  
  String urls[] = new String[2];
  urls[0] = url;
  urls[1] = "/flavours/";
  
  String flavourUrl = join(urls, "");
  flavourData = loadJSONObject(flavourUrl); 
  JSONArray flavs = flavourData.getJSONArray("flavours"); 
  
//  println(flavs.size());
 
  for (int x = 0; x < flavs.size(); x++) {
    JSONObject flav = flavs.getJSONObject(x);
    
    float recipeBitter = flav.getFloat("bitter");
    float recipeSalty = flav.getFloat("salty");
    float recipeSour = flav.getFloat("sour");
    float recipeSweet = flav.getFloat("sweet");
    float recipeUmami = flav.getFloat("umami");
    
    flavours = new Flavour(recipeBitter, recipeSalty, recipeSour, recipeSweet, recipeUmami);
  }
   
    recipe.flavours = flavours;

}
ArrayList flav;

public ArrayList flavourFlockInit(float prevalence, float x, float y) {

  flav = new ArrayList<Particle>();
  
  for (int i = 0; i < prevalence*50; i++) {
    flav.add(new Particle(new PVector(random(0, width), random(0, height)), prevalence*5, 10, 10, x, y));
  }
  
  return flav;

}


class MyControlListener implements ControlListener {
  int col;
  public void controlEvent(ControlEvent theEvent) {
    println("i got an event from mySlider, " +
            "changing background color to "+
            theEvent.getController().getValue());
    col = (int)theEvent.getController().getValue();
    bitterKnob.setValue(flavours.bitter);
    saltyKnob.setValue(flavours.salty);
    sourKnob.setValue(flavours.sour);
    umamiKnob.setValue(flavours.umami);
    sweetKnob.setValue(flavours.sweet);
    drinkName = new Textlabel(cp5,recipe.name,20,450,500,200);
    drinkName.setFont(createFont("", 20, true));
    background(10);
  }

}

class FlavourControlListener implements ControlListener {
  float col;
  String name;
  public void controlEvent(ControlEvent theEvent) {
    name = (String)theEvent.getController().getName();
    String stripName = name.substring(1);
    col = (float)theEvent.getController().getValue();
    
    flavours.display();
    
    if (stripName.equals("bitter")) {
     flavours.bitter = col;    
    _bitter = flavourFlockInit(flavours.bitter, width/2, height/6);
    } else if (stripName.equals("salty")) {
    flavours.salty = col; 
    _leftSalty = flavourFlockInit(flavours.salty, width/3, height - height/3);
    _rightSalty = flavourFlockInit(flavours.salty, width - width/3, height - height/3);
    _midSalty = flavourFlockInit(flavours.salty, width/2, height - height/5);
    } else if (stripName.equals("sour")) {
    flavours.sour = col;    
    _leftSour = flavourFlockInit(flavours.sour, width/4, height/3);
    } else if (stripName.equals("sweet")) {
    flavours.sweet = col;    
    _sweet = flavourFlockInit(flavours.sweet, width/2, height - height/5);
    } else {
      flavours.umami = col;    
      _umami = flavourFlockInit(flavours.umami, width/2, height/2);
    }
    
  }

}



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
  
  public void populate() {
    
    ArrayList<Float> quants = new ArrayList<Float>();
    withQuantityUnit = new ArrayList<Ingredient>();
    withQuantity = new ArrayList<Ingredient>();
    noQuantityUnit = new ArrayList<Ingredient>();
    
    for (Ingredient ingredient : ingredients) {
      quants.add(PApplet.parseFloat(ingredient.quantity));
      
      minQuantity = Collections.min(quants);
      maxQuantity = Collections.max(quants);
      
      Float quantity = PApplet.parseFloat(ingredient.quantity);
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
  public void print() {
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
  int colour;
  
  ChartItem(float xPos, float yPos, float itemSize, String itemName, int itemColour, Boolean newItemUnit) {
    x = PApplet.parseInt(xPos);
    y = PApplet.parseInt(yPos);
    size = itemSize;
    name = itemName;
    colour = itemColour;
    itemUnit = newItemUnit;
  }
  
  public void draw() {
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
  int colour;
  
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
  public void display(){
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
  public void forces(float tx, float ty, float d){
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
  public void applyForce(PVector force){
    force.div(mass);
    acc.add(force);
  }
   
  //method to update the location of the particle, and keep its velocity within a set limit
  public void update(){
    vel.add(acc);
    vel.limit(velocityLimit);
    loc.add(vel);
    acc.mult(0);
  }
   
  //method to bounce particles of canvas edges
  public void bounds(){
    if(loc.y > height || loc.y < 0){
      vel.y *= -1;
    }
    if(loc.x > width || loc.x < 0){
      vel.x *= -1;
    }
  }
   

  //main method that combines all previous methods, and takes two arguments
  //tx and ty are inherited from forces(), and set the attractive/repulsive co-ords
  public void run(float tx, float ty, float d){
    forces(tx, ty, d);
    display();
    bounds();
    update();
  }
  
}

TasteLocation bitter;
TasteLocation leftSalty;
TasteLocation rightSalty;
TasteLocation midSalty;
TasteLocation sweet;
TasteLocation leftSour;
TasteLocation rightSour;
TasteLocation umami;

public void initBudDist() {
  flavours = recipe.flavours; 
  
  bitter = new TasteLocation(bitterColourComplement, 100, width/2, height/6, flavours.bitter * 100, flavours.bitter * 100);
  leftSour = new TasteLocation(sourColourComplement, 100, width/4, height/3, flavours.sour * 100, flavours.sour * 100);
  rightSour = new TasteLocation(sourColourComplement, 100, width - width/4, height/3, flavours.sour * 100, flavours.sour * 100);
  leftSalty = new TasteLocation(saltyColourComplement, 100, width/3, height - height/3, flavours.salty * 100, flavours.salty * 100);
  rightSalty = new TasteLocation(saltyColourComplement, 100, width - width/3, height - height/3, flavours.salty * 100, flavours.salty * 100);
  sweet = new TasteLocation(sweetColourComplement, 100, width/2, height - height/5, flavours.sweet * 100, flavours.sweet * 100);
  midSalty = new TasteLocation(saltyColourComplement, 100, width/2, height - height/5, flavours.salty * 100, flavours.salty * 100);  
  umami = new TasteLocation(umamiColourComplement, 100, width/2, height/2, flavours.umami * 100, flavours.umami * 100);
  
}

public void drawTasteBuds() {
   bitter.draw();
  leftSalty.draw();
  rightSalty.draw();
  midSalty.draw();
  sweet.draw();
  leftSour.draw();
  rightSour.draw();
  umami.draw();
}
IngredientChart chart;
ChartItem chartItem;

public void initIngredients() {  
  ArrayList<Ingredient> recipeIngredients = recipe.ingredients;
  
  chart = new IngredientChart(recipe, recipeIngredients);
  chart.populate();  
}

public void drawGraph() {
  int neitherCount = 0;
  for (Ingredient ingredient : chart.noQuantityUnit) {
    neitherCount++;
    float neitherCountxPos = random(0, 500);
    float neitherCountyPos = random(0, 300);
    float neitherCountSize = 30;
    String neitherCountName = ingredient.name;
    int neitherCountColour = color(231, 76, (60 + (neitherCount * 10)));
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
    int quantityCountColour = color(142, 68, (173 + (quantityCount * 20)));
    Boolean quantityCountItemUnit = false;
    
    for (int a = 0; a < PApplet.parseInt(ingredient.quantity); a++) {
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
    float bothCountSize = PApplet.parseFloat(ingredient.quantity) * 20;
    String bothCountName = ingredient.name;
    int bothCountColour = color(39, 174, (96 + (bothCount * 10)));
    Boolean bothCountItemUnit = true;
        
    chartItem = new ChartItem(bothCountxPos, bothCountyPos, bothCountSize, bothCountName, bothCountColour, bothCountItemUnit);
    chartItem.draw();
    
  }
}

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
  
  public void display() {
    println(jsonUrl);
    println(name);
    println(url);
    println(recipeLength);
  }
  
  public void ingredients() {
    getIngredients(jsonUrl);
  }
  
  public void flavours() {
    getFlavours(jsonUrl);
  }
  
  public void displayFlavours() {
    flavours.display();
  }
  
  public void displayIngredients() {
    for (Ingredient ingredient : ingredients) {
      ingredient.display();
    }
  }
  
  public void saveImage() {
    save("images/" + name + ".jpg");
    background(200);
  }
  
  public void saveTasteImage() {
    save("taste/" + name + ".jpg");
    background(200);
  }
  
  public void addTitle() {
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
  
  public void display() {
    println("----- Flavours -----");
    println("Bitter: " + bitter);
    println("Salty: " + salty);
    println("Sour: " + sour);
    println("Sweet: " + sweet);
    println("Umami: " + umami);
  }
  
  public void highest() {
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
  
  public void display() {
    println(quantity + " | " + unit + " | " + name);
  }
}
class TasteLocation {
  int tasteColour;
  float tasteOpacity;
  float tastexPos;
  float tasteyPos;
  float tasteWidth;
  float tasteHeight;
  
  TasteLocation(int colour, float opacity, float x, float y, float tWidth, float tHeight) {
    tasteColour = colour;
    tasteOpacity = opacity;
    tastexPos = x;
    tasteyPos = y;
    tasteWidth = tWidth;
    tasteHeight = tHeight;
  }
  
  public void print() {
    println("---Taste Locations---");
    println("Colour :" + tasteColour );
    println("Opacity :" + tasteOpacity );
    println("xPos :" + tastexPos );
    println("yPos :" + tasteyPos );
    println("Width :" + tasteWidth );
    println("Height :" + tasteHeight );
  }
  
  public void draw() {
    fill(tasteColour, 0);
    stroke(tasteColour, tasteOpacity);
    ellipse(tastexPos, tasteyPos, tasteWidth, tasteHeight);
    noStroke();
  }
}

class tasteTooltip {
  Flavour flavour;
  float x;
  float y;
  float distance;
  
  tasteTooltip(Flavour _flavour, float _x, float _y, float _distance) {
    flavour = _flavour;
    x = _x;
    y = _y;
    distance = _distance;
  }
  
  public void display() {
    println(flavour);
    println(x, y);
    println(distance);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "setupGetRecipes" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
