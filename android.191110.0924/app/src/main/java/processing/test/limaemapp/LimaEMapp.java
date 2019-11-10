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
 background(fondo2);
  //start listening for BT connections
 bt.start();
 //at app start select device…
 isConfiguring = true;
 bigLetters = (h+w)/20;
 smallLetters = (h+w)/60;
}


// Wie loop() beim Arduino wird draw() immer wieder aufgerufen, solange das Programm ausgeführt wird.
public void draw() {
   stroke(letraSecundaria);
 fill(letraSecundaria);
 rect(0, 0, w, h/10);
 fill(black);
 line(w/32,h/40,w/32+2*w/32,h/40);
 fill(red);
 int R = w/3;
 int r = w/20;
 
 fill(letraSecundaria);
 stroke(letraSecundaria);
 arc(w/2, h/2, 2*R+5, 2*R+r/3, 0, 2*PI, PIE);
 fill(fondo2);
 stroke(fondo2);
 arc(w/2, h/2, 2*R-5, 2*R-r/3, 0, 2*PI, PIE);
 
 float aux=93*2; 
 for(float i=0;i<3*PI/2;i=i+(PI/360)){
   ellipse(w/2-R*cos(-(i+PI/2)),h/2+R*sin(-(i+PI/2)),r,r);
   aux=aux+0.4f;
   fill(223,PApplet.parseInt(aux/2),20);
   stroke(223,PApplet.parseInt(aux/2),20); 
 }
 
 stroke(white);
 fill(white);
 textSize(bigLetters);
 text("8",w/2-w/40,h/2);
 stroke(letraSecundaria);
 fill(letraSecundaria);
 textSize(smallLetters);
 text("REMAINING",w/2-2.2f*smallLetters,h/2+2.2f*smallLetters);
 home(w/6,5*h/6,6);
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

public void home(int x, int y , int size){
  int a=8,b=8;
  fill(naranja);
  stroke(naranja);
  triangle(x+a*size,y,x,y+b*size,x+2*a*size,y+b*size);
}
  public void settings() {  size(displayWidth, displayHeight); }
}
