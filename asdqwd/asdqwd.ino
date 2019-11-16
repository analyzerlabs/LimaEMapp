void setup() {
    pinMode(13,OUTPUT);
    pinMode(12,OUTPUT);
}

void loop() {
    digitalWrite(12,HIGH);
    delay(50);
    digitalWrite(12,LOW);
    delay(50);

}
