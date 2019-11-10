import android.content.Intent;
import android.os.Bundle;

import ketai.net.bluetooth.*;
import ketai.ui.*;
import ketai.net.*;
 
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


void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  bt = new KetaiBluetooth(this);
}
int h,w;
void onActivityResult(int requestCode, int resultCode, Intent data) {
  bt.onActivityResult(requestCode, resultCode, data);
}
 
// setup() wird einmal zu Beginn dea Programms ausgeführt
void setup() {
     size(displayWidth, displayHeight);
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
     loadImages();
}

int init,i=0;
// Wie loop() beim Arduino wird draw() immer wieder aufgerufen, solange das Programm ausgeführt wird.
void draw() {
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

void onKetaiListSelection(KetaiList klist)
{
    String selection = klist.getSelection();
    bt.connectToDeviceByName("Analyzerlabs");
  
    //dispose of list for now
    klist = null;
}

//Call back method to manage data received
void onBluetoothDataEvent(String who, byte[] data) {
   if(isConfiguring)
       return;
   //received
   info = new String(data);
}
