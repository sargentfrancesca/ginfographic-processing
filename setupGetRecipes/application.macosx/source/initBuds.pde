TasteLocation bitter;
TasteLocation leftSalty;
TasteLocation rightSalty;
TasteLocation midSalty;
TasteLocation sweet;
TasteLocation leftSour;
TasteLocation rightSour;
TasteLocation umami;

void initBudDist() {
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

void drawTasteBuds() {
   bitter.draw();
  leftSalty.draw();
  rightSalty.draw();
  midSalty.draw();
  sweet.draw();
  leftSour.draw();
  rightSour.draw();
  umami.draw();
}