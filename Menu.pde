void menu(){
    stroke(fondoMenu);
    fill(fondoMenu);
    rect(0, 0, w, h/10);
    line(w/32,h/40,w/32+2*w/32,h/40);
    stroke(white);
    fill(white);
    textSize(bigLetters);
    textAlign(CENTER,CENTER);
    text("LimaEM",w/2,h/20);
    arc(w/20,h/20,50,70, PI-PI/2, PI+PI/4, CHORD);
    image(icon, 0, 0,h/10,h/10);
}
