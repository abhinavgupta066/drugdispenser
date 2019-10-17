#include <Servo.h>
#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>
#define FIREBASE_HOST "https://drugaid-130e6.firebaseio.com/"
#define FIREBASE_AUTH "yb3ooB1Mb947goEd5wQcup92SAbEyXBCum6X14dI"
#define WIFI_SSID "AndroidAPbdf7"
#define WIFI_PASSWORD "nsxx0591"
int servo1_start = 120;
int servo1_stop = 90;
int servo2_start = 0;
int servo2_stop = 70;
Servo servob;
Servo servot;
int pills = 0;
int dispense = 0;
int time_interval = 0;
FirebaseData firebaseData;
String path = "/patients";
void printJsonObjectContent(FirebaseData &data);

void setup() {
    Serial.begin(9600);
    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("connecting");
    while (WiFi.status() != WL_CONNECTED) {
      Serial.print(".");
      delay(500);
  }
    Serial.println();
    Serial.print("connected: ");
    Serial.println(WiFi.localIP());
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
    delay(500);
    servob.attach(D5);
    servot.attach(D6);
    servot.write(servo2_start);
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
    Firebase.reconnectWiFi(true);
    Firebase.setReadTimeout(firebaseData, 1000 * 60);
    Firebase.setwriteSizeLimit(firebaseData, "tiny");
  }

void servo_bottom(){
    servob.write(servo1_start);
    delay(100);
    servob.write(servo1_stop);
    delay(100);
}

void servo_top(){
    servot.write(servo2_stop);
    delay(100);
    servot.write(servo2_start);
    delay(100);
}

void loop() {
  data();
  if(dispense == 1){
    for(int n = 0; n < 1; n++){
      servo_top();
      delay(50);
      servo_top();
      delay(1000);
      servo_bottom();
      delay(50);
      servo_bottom();
      delay(50);
    }
    dispense = 0;
    pills = 0;
    clear_dispense();
    Serial.println("Dispense Cleared");
  }
  delay(1000);
}


void clear_dispense(){
  FirebaseJson json;
  json.clear().addDouble("dispenser" + String(1), 0.0);
  Firebase.updateNode(firebaseData, path + "/patient1", json);
  delay(1000);
}


void data(){
  
    Firebase.getInt(firebaseData, path + "/patient1/dispenser" + 1);
    dispense = firebaseData.intData();
    
    Firebase.getInt(firebaseData, path + "/patient1/patientTimes" + 1);
    pills = firebaseData.intData();

    Firebase.getInt(firebaseData, path + "/patient1/timeInterval" + 1);
    time_interval = firebaseData.intData();
}
