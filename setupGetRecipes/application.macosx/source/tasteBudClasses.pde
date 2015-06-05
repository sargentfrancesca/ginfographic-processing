class TasteLocation {
  color tasteColour;
  float tasteOpacity;
  float tastexPos;
  float tasteyPos;
  float tasteWidth;
  float tasteHeight;
  
  TasteLocation(color colour, float opacity, float x, float y, float tWidth, float tHeight) {
    tasteColour = colour;
    tasteOpacity = opacity;
    tastexPos = x;
    tasteyPos = y;
    tasteWidth = tWidth;
    tasteHeight = tHeight;
  }
  
  void print() {
    println("---Taste Locations---");
    println("Colour :" + tasteColour );
    println("Opacity :" + tasteOpacity );
    println("xPos :" + tastexPos );
    println("yPos :" + tasteyPos );
    println("Width :" + tasteWidth );
    println("Height :" + tasteHeight );
  }
  
  void draw() {
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
  
  void display() {
    println(flavour);
    println(x, y);
    println(distance);
  }
}