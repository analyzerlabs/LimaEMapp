void button(int x, int y, int r, color cbutton){
    fill(cbutton);
    stroke(cbutton);
    ellipse(x,y,r,r);
}

void mousePressed() {
  if (overCircle(w/3,w/3,w/10)) {
    i = 0;
  }
  
}

boolean overCircle(int x, int y, int diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}
