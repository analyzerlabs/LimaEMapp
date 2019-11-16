
void setup() {
  // put your setup code here, to run once:
  pinMode(53,OUTPUT);
  pinMode(51,OUTPUT);
}


void loop() {
  // put your main code here, to run repeatedly:
  peristaltica(1,5);
  delay(500);
  peristaltica(0,5);  
  delay(500);
}

void peristaltica(int dir,int v){
  for(int i=0;i<200*v;i++){
    digitalWrite(51,dir);
    digitalWrite(53,HIGH);
    delayMicroseconds(750);
    digitalWrite(53,LOW);
    delayMicroseconds(750);
  }
}
