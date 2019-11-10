package processing.test.limaemapp;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.content.Intent; 
import android.os.Bundle; 
import ketai.net.bluetooth.*; 
import ketai.ui.*; 
import ketai.net.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LimaEMapp extends PApplet {







 
KetaiBluetooth bt;
boolean isConfiguring = true;


String info = "";
KetaiList klist;
ArrayList devicesDiscovered = new ArrayList();
  
// States of the two sensors
int B1in = 0;
int B2in = 0;

//********************************************************************
// The following code is required to enable bluetooth at startup.
//********************************************************************


public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  bt = new KetaiBluetooth(this);
}
int h,w;
public void onActivityResult(int requestCode, int resultCode, Intent data) {
  bt.onActivityResult(requestCode, resultCode, data);
}
 
// setup() wird einmal zu Beginn dea Programms ausgeführt
public void setup() {
     
     w=displayWidth;
     h=displayHeight;
     frameRate(60);
     orientation(PORTRAIT);
     background(fondoPrincipal);
      //start listening for BT connections
     bt.start();
     //at app start select device…
     isConfiguring = true;
     bigLetters = (h+w)/20;
     smallLetters = (h+w)/60;
     init = millis();
     
}

int init,i=0;
// Wie loop() beim Arduino wird draw() immer wieder aufgerufen, solange das Programm ausgeführt wird.
public void draw() {
    loadImages();
    if(millis()-init>=1){
       menu();
       home(w/6,5*h/6,8);
       println(init);
       init = millis();
       knobe(i,100);       
       if(i!=72)i++;
       button(w/3,w/3,w/10,blue);
   }
}

public void onKetaiListSelection(KetaiList klist)
{
    String selection = klist.getSelection();
    bt.connectToDeviceByName("Analyzerlabs");
  
    //dispose of list for now
    klist = null;
}

//Call back method to manage data received
public void onBluetoothDataEvent(String who, byte[] data) {
   if(isConfiguring)
       return;
   //received
   info = new String(data);
}
public void knobe(int value,int max){
     int R = w/3;
     int r = w/20;
     stroke(fondoPrincipal);
     fill(fondoPrincipal);
     arc(w/2, h/2, 2*R+r+2, 2*R+r+2, 0, 2*PI, PIE);
     stroke(letraSecundaria);
     fill(letraSecundaria);

     arc(w/2, h/2, 2*R+5, 2*R+r/3, 0, 2*PI, PIE);
     fill(fondoPrincipal);
     stroke(fondoPrincipal);
     arc(w/2, h/2, 2*R-5, 2*R-r/3, 0, 2*PI, PIE);
     
     float aux=93*2; 
     for(float i=0;i<value*2*PI/100;i=i+(PI/360)){
         ellipse(w/2-R*cos(-(i+PI/2)),h/2+R*sin(-(i+PI/2)),r,r);
         aux=aux+0.4f;
         fill(223,PApplet.parseInt(aux/2),20,255);
         stroke(223,PApplet.parseInt(aux/2),20,255); 
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
PImage icon;
public void loadImages(){
    icon = loadImage("analyzer-icon2.png");
}
public void menu(){
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
public void button(int x, int y, int r, int cbutton){
    fill(cbutton);
    stroke(cbutton);
    ellipse(x,y,r,r);
}

public void mousePressed() {
  if (overCircle(w/3,w/3,w/10)) {
    i = 0;
  }
  
}

public boolean overCircle(int x, int y, int diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}
int black = color(0,0,0);
int blue = color(10, 180, 255,255);
int red = color(240, 0, 0);
int white = color(255, 255, 255);
int c1 = color(204, 102, 0);
int c2 = color(0, 102, 153);
int letraSecundaria = color(51,57,71);
int fondoPrincipal = color(24,31,44);
int fondoMenu= color(76,69,77);
int naranja = color(255,107,38,255);
int icono = color(67,74,89);

int bigLetters;
int smallLetters;
public void home(int x, int y , int size){
    int a=3,b=2;
    fill(naranja);
    stroke(naranja);
    triangle(x+a*size,y,x,y+b*size,x+2*a*size,y+b*size);
    rect(x,y+(b+1)*size,2*a*size,2*a*size);
    fill(fondoPrincipal);
    stroke(fondoPrincipal);
    rect(x+2*a*size/3,y+(b+1)*size+a*size,2*a*size/3,2*a*size);
}
  public void settings() {  size(displayWidth, displayHeight); }
}
