import controlP5.*;
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

color bitterColour = color(210,210,93);
color bitterColourComplement = color(168, 75, 128);
color saltyColour = color(244,206,39);
color saltyColourComplement = color(111, 34, 164);
color sweetColour = color(243,159,44);
color sweetColourComplement = color(37, 103, 157);
color sourColour = color(235,169,55);
color sourColourComplement = color(35, 150, 127);
color umamiColour = color(241,241,63);
color umamiColourComplement = color(170, 225, 59);

void setup() {
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

void draw() {

  drinkName.draw(this); 

  budFlock();
//  ingredientDistGraph();
//  noLoop();
//  tasteBudFlock(1);
}

void tasteBudDistGraph() {
//  for (int l = 1; l < 48; l++) {
//    getRecipe(t);
    initBudDist();
    drawTasteBuds();
//  }
}

void initiateBudFlock(int i) {
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

void budFlock() {
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

void getRecipe(int id) {
  
  String[] url = new String[2];
  url[0] = "http://ginfographic.chez.io/api/v1.0/recipes/";
  url[1] = str(id);
  
  String jsonUrl = join(url, "");
  
  data = loadJSONObject(jsonUrl);
  singleRecipe = data.getJSONObject("recipe");
  
  String recipeName =  singleRecipe.getString("name");
  String recipeCountLengthString = singleRecipe.getString("length");
  int recipeCountLength = int(recipeCountLengthString);
  String urlAd = singleRecipe.getString("url");
  
  recipe = new Recipe(jsonUrl, recipeName, recipeCountLength, urlAd);
  recipe.ingredients();
  recipe.displayIngredients();
  recipe.flavours();
//  recipe.displayFlavours();
}

void saveIt() {
  recipe.saveImage();
}


void getIngredients(String url) {
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

void getFlavours(String url) {
  
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