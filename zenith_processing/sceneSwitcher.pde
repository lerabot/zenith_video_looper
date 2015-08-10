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

	void run() {
		//println(activeScene);
	}

	//visual shit
	void display() {
		scenes.get(activeScene).display();
	}

	void keyPressed() {
		for(int i = 0; i < sceneNumber; i++){
			if(key == i + 48) {
				activeScene = i;
			}
		}
	}


}