import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gifAnimation.*; 
import codeanticode.gsvideo.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class zenith_processing extends PApplet {


//import processing.video.*;


//Capture webcam;
//Capture pipeline;
Looper loop1;
GSPipeline pipeline;

SceneSwitcher scenes;

public void setup() {
	size(640,480);
	//webcam = new Capture (this, width/2, height/2, 30);
	pipeline = new GSPipeline(this, "dv1394src ! video/x-dv ! dvdemux ! dvdec ! ffmpegcolorspace ! video/x-raw-rgb, width=720, height=480");
	pipeline.play();
	//webcam.start();
	loop1 = new Looper();
	scenes = new SceneSwitcher(5);
	println("Num of Scene : "+ scenes.sceneNumber);
}

public void draw() {
	//loop1.display();
	scenes.run();
	scenes.display();
	// if (webcam.available())
	// 	webcam.read();
	//firewireVideo();
}

public void keyPressed() {
		scenes.keyPressed();
		loop1.keyPressed();

}

public void firewireVideo() {
	if (pipeline.available()) {
		text("Deck Connected", 25, 50);
		pipeline.read();
	} else {
		text("Deck N/A", 25, 50);
	}
}
class Looper {
	//holds all the images of the loop;
	ArrayList<PImage> theFramesI = new ArrayList<PImage>();
	ArrayList<PGraphics> theFrames = new ArrayList<PGraphics>();
	//number of frame du loop
	int frameNumber = 5;
	//frame total
	int totalFrames = 100;
	//la frame displyed a l'ecran
	int currentFrame = 0;
	//vitesse du looper
	int theSpeed = 5;

	Looper() {
		for(int i = 0; i < totalFrames; i++) {
			theFrames.add(createGraphics(width, height));	
			fillFrame(i, PApplet.parseInt(random(255)));
		}
	}

	public void display() {
		pushMatrix();
		scale(1);
		image(theFrames.get(currentFrame),0,0);
		popMatrix();
		updateFrame();
		int theTime = second();
		text(currentFrame+" // "+theTime, 25,25);
	}

	public void updateFrame() {
		if (frameCount % theSpeed == 0){
			currentFrame++;
			if (currentFrame >= frameNumber)
			currentFrame = 0;
		}
	}

	public void copyFrom(PImage content, int index){
		PGraphics f = theFrames.get(index);		
		f.beginDraw();
		f.image(content,0,0);
		f.endDraw();
	}

	public void fillFrame(int index, int theColor) {
		PGraphics f = theFrames.get(index);
		f.beginDraw();
		f.background(theColor);
		f.endDraw();
	}

	public void keyPressed() {
		////////SPEED
		if (key == 'q')
			theSpeed--;
		if (key == 'a') 
			theSpeed++;
		theSpeed = constrain(theSpeed, 1, 10);
		//////////FRAMES
		if (key == 'w')
			addFrame();
		if (key == 's')
			removeFrame();
		if (key == 'r')
			copyFrom(pipeline, currentFrame);
	}

	public void addFrame() {
		theFrames.add(frameNumber+1, createGraphics(width, height));
		fillFrame(frameNumber+1, 0);
		frameNumber++;
	}	

	public void removeFrame() {
		if (frameNumber > 1) {
		theFrames.remove(frameNumber);
		theFrames.trimToSize();
		frameNumber--;
		}
	}


}
class Scene {
	int sceneNum; 
	
	PImage bg;
	PVector bgPos = new PVector();
	PImage[] img;

	PGraphics main;
	
	Gif testAnim;

	int bgColor;

	Scene(int index){
		sceneNum = index;
		//bg = loadImage("scene"+sceneNum+"/move.gif");
		//testAnim = new Gif(antoje.this, "scene"+sceneNum+"/move.gif");
		//testAnim.play();
		main = createGraphics(width, height);
		bgColor = getRandomColor();
		main.beginDraw();
		main.background(bgColor);
		main.endDraw();
	}

	public void display() {
		drawBG();
		drawID();
	}

	public void drawBG() {
		//imageMode(CENTER);
		//image(testAnim,width/2,height/2);
		image(main,0,0);
	}

	public void drawID() {
		text(sceneNum, width - 25, 25);
	}

	public PVector getCenterPosition(PImage theImage) {
		PVector size = new PVector(theImage.width, theImage.height);
		size.sub(height, width, 0);
		return size;
	}
}
class SceneSwitcher {
	//holds all the scenes
	ArrayList<Scene> scenes = new ArrayList<Scene>();
	Table settings;

	int sceneNumber;
	int activeScene;

	SceneSwitcher(int numOfScene) {
		sceneNumber = numOfScene;
		//settings = loadTable("setting.csv");
		for (int i = 0; i < numOfScene; i++){
			scenes.add(new Scene(i));
		}
	}

	public void run() {
		//println(activeScene);
	}

	//visual shit
	public void display() {
		scenes.get(activeScene).display();
	}

	public void keyPressed() {
		for(int i = 0; i < sceneNumber; i++){
			if(key == i + 48) {
				activeScene = i;
			}
		}
	}


}
public int getRandomColor() {
	return color(random(PApplet.parseInt(255)),random(PApplet.parseInt(255)),random(PApplet.parseInt(255)));
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "zenith_processing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
