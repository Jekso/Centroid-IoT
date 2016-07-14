#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
EthernetClient client;
char server[] = "www.gelvano.esy.es";

unsigned long lastConnectionTime = 0;             
const unsigned long postingInterval = 10L * 1000L;

const String system_hash = "dd44r" ;
const int AC1 = 3 ;
const int AC2 = 5 ;
const int AC3 = 6 ;
const int AC4 = 8 ;
const int door = 2 ;
const int temp_sensor_pin = A0 ;

int door_state , flag, hour ;
float temp_sensor_val , resistance;
String request_parameters,http_response = "" ;
boolean mode,ac1,ac2,ac3,ac4 ;

void setup()
{
  Serial.begin(9600);
  while (!Serial) {;}
  delay(1000);
  if(Ethernet.begin(mac) == 0)
    Serial.print("Error Configured Ethernet");;
  Serial.print("My IP address: ");
  Serial.println(Ethernet.localIP());
  pinMode(AC1,OUTPUT);
  pinMode(AC2,OUTPUT);
  pinMode(AC3,OUTPUT);
  pinMode(AC4,OUTPUT);
  pinMode(door,INPUT);
}

void loop()
{
  if (client.available())
  {
    char c = client.read();
    Serial.write(c);
    if(http_response.length() < 250)
    {
      http_response += c ;
    }
    
    if(c == '\n')
    {
      flag  = http_response.indexOf("mode=")+5 ;
      mode  = (http_response[flag] == '1') ? 1 : 0 ;
      if(mode == 1)
      {
        ac1   = (http_response[flag+2] == '1') ? HIGH : LOW ;
        ac2   = (http_response[flag+4] == '1') ? HIGH : LOW ;
        ac3   = (http_response[flag+6] == '1') ? HIGH : LOW ;
        ac4   = (http_response[flag+8] == '1') ? HIGH : LOW ;
        operateACs(ac1,ac2,ac3,ac4) ;
      }
      else
      {
        hour = http_response.substring(flag+2,flag+4).toInt() ;
        if(hour >= 0 && hour < 12)
           operateACs(1,1,0,0) ;
        else
          operateACs(0,0,1,1) ;
      }
      
    }
  }



  if (millis() - lastConnectionTime > postingInterval)
  {
    door_state      = (int) digitalRead(door) ;
    temp_sensor_val = analogRead(temp_sensor_pin);
    resistance      = (temp_sensor_val*95)/(1024-temp_sensor_val);  
    request_parameters = "system_hash="+system_hash+"&current_temp_resistance="+resistance+"&door_state="+door_state ;
    httpRequest(request_parameters);
  }

}



void httpRequest(String request_parameters)
{
  client.stop();
  if (client.connect(server, 80))
  {
    http_response = "" ;
    Serial.println("connecting...");
    client.println("POST /itroom/arduino-ethernet-module/handler.php HTTP/1.1");
    client.println("Host: www.gelvano.esy.es");
    client.println("User-Agent: IoT-IT-RooM-Arduino");
    client.println("Content-Type: application/x-www-form-urlencoded");
    client.print("Content-Length: ");
    client.println(request_parameters.length());
    client.println();
    client.print(request_parameters); 
    client.println("Connection: close");
    client.println();
    lastConnectionTime = millis();
  }
  else
  {
    Serial.println("connection failed");
  }
}



void operateACs(boolean ac1,boolean ac2,boolean ac3,boolean ac4)
{
  digitalWrite(AC1,ac1) ;
  digitalWrite(AC2,ac2) ;
  digitalWrite(AC3,ac3) ;
  digitalWrite(AC4,ac4) ;
}



