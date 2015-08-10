import gifAnimation.*;
//import processing.video.*;
import codeanticode.gsvideo.*;

// Capture webcam;
Looper loop1;
GSPipeline pipeline;

void setup() {
	size(640,480);
	// String[] cameras = Capture.list();
	// println(cameras);
	//webcam = new Capture (this, width/2, height/2, 30);
	// webcam = new Capture (this, "/dev/video1");
	pipeline = new GSPipeline(this, "dv1394src port=0 ! queue ! dvdemux ! ffdec_dvvideo ! ffmpegcolorspace ! xvimagesink, width=720");
	// webcam.start();
	loop1 = new Looper();
}

void draw() {
	loop1.display();
	if (pipeline.available())
		pipeline.read();
	//image(webcam,0,0);
}

void keyPressed() {
	loop1.keyPressed();
}
