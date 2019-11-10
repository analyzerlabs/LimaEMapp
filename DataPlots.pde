void knobe(int value,int max){
     stroke(letraSecundaria);
     fill(letraSecundaria);
     
     int R = w/3;
     int r = w/20;
     arc(w/2, h/2, 2*R+5, 2*R+r/3, 0, 2*PI, PIE);
     fill(fondoPrincipal);
     stroke(fondoPrincipal);
     arc(w/2, h/2, 2*R-5, 2*R-r/3, 0, 2*PI, PIE);
     
     float aux=93*2; 
     for(float i=0;i<value*2*PI/100;i=i+(PI/360)){
         ellipse(w/2-R*cos(-(i+PI/2)),h/2+R*sin(-(i+PI/2)),r,r);
         aux=aux+0.4;
         fill(223,int(aux/2),20,255);
         stroke(223,int(aux/2),20,255); 
     }
     
     stroke(white);
     fill(white);
     textAlign(CENTER,CENTER);
     textSize(bigLetters);
     text(str(value),w/2,h/2-smallLetters);
     stroke(letraSecundaria);
     fill(letraSecundaria);
     textSize(smallLetters);
     text("REMAINING",w/2,h/2+3*smallLetters);
 }
