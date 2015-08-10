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
		for(int i = 0; i < totalFrames; i++) {
			theFrames.add(createGraphics(width, height));	
			fillFrame(i, int(random(255)));
		}
	}

	void display() {
		pushMatrix();
		scale(1);
		image(theFrames.get(currentFrame),0,0);
		popMatrix();
		updateFrame();
		int theTime = second();
		text(currentFrame+" // "+theTime, 25,25);
	}

	void updateFrame() {
		if (frameCount % theSpeed == 0){
			currentFrame++;
			if (currentFrame >= frameNumber)
			currentFrame = 0;
		}
	}

	void copyFrom(PImage content, int index){
		PGraphics f = theFrames.get(index);		
		f.beginDraw();
		f.image(content,0,0);
		f.endDraw();
	}

	void fillFrame(int index, int theColor) {
		PGraphics f = theFrames.get(index);
		f.beginDraw();
		f.background(theColor);
		f.endDraw();
	}

	void keyPressed() {
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

	void addFrame() {
		theFrames.add(frameNumber+1, createGraphics(width, height));
		fillFrame(frameNumber+1, 0);
		frameNumber++;
	}	

	void removeFrame() {
		if (frameNumber > 1) {
		theFrames.remove(frameNumber);
		theFrames.trimToSize();
		frameNumber--;
		}
	}


}