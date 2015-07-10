import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gifAnimation.*; 
import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class zenith_processing extends PApplet {




Capture webcam;
Looper loop1;


public void setup() {
	size(640,480);
	String[] cameras = Capture.list();
	println(cameras);
	webcam = new Capture (this, width/2, height/2, 30);
	webcam.start();
	loop1 = new Looper();
}

public void draw() {
	loop1.display();
	if (webcam.available())
		webcam.read();
	//image(webcam,0,0);
}

public void keyPressed() {
	loop1.keyPressed();
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
			theFrames.add(createGraphics(width/2, height/2));	
			fillFrame(i, PApplet.parseInt(random(255)));
		}
	}

	public void display() {
		pushMatrix();
		scale(2);
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
		if (key == 'r');
			copyFrom(webcam, currentFrame);
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "zenith_processing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
