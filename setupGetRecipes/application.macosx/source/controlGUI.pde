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