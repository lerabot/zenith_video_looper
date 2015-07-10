import gifAnimation.*;
import processing.video.*;

Capture webcam;
Looper loop1;


void setup() {
	size(640,480);
	String[] cameras = Capture.list();
	println(cameras);
	webcam = new Capture (this, width/2, height/2, 30);
	webcam.start();
	loop1 = new Looper();
}

void draw() {
	loop1.display();
	if (webcam.available())
		webcam.read();
	//image(webcam,0,0);
}

void keyPressed() {
	loop1.keyPressed();
}