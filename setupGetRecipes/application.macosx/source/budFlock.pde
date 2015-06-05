ArrayList flav;

ArrayList flavourFlockInit(float prevalence, float x, float y) {

  flav = new ArrayList<Particle>();
  
  for (int i = 0; i < prevalence*50; i++) {
    flav.add(new Particle(new PVector(random(0, width), random(0, height)), prevalence*5, 10, 10, x, y));
  }
  
  return flav;

}