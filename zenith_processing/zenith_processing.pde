import gifAnimation.*;
//import processing.video.*;
import codeanticode.gsvideo.*;

//Capture webcam;
//Capture pipeline;
Looper loop1;
GSPipeline pipeline;

SceneSwitcher scenes;

void setup() {
	size(640,480);
	//webcam = new Capture (this, width/2, height/2, 30);
	pipeline = new GSPipeline(this, "dv1394src ! video/x-dv ! dvdemux ! dvdec ! ffmpegcolorspace ! video/x-raw-rgb, width=720, height=480");
	pipeline.play();
	//webcam.start();
	loop1 = new Looper();
	scenes = new SceneSwitcher(5);
	println("Num of Scene : "+ scenes.sceneNumber);
}

void draw() {
	//loop1.display();
	scenes.run();
	scenes.display();
	// if (webcam.available())
	// 	webcam.read();
	//firewireVideo();
}

void keyPressed() {
		scenes.keyPressed();
		loop1.keyPressed();

}

void firewireVideo() {
	if (pipeline.available()) {
		text("Deck Connected", 25, 50);
		pipeline.read();
	} else {
		text("Deck N/A", 25, 50);
	}
}