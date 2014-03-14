package com.gamepackage;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.DisplayMetrics;

import com.gamepackage.managers.GameManager;
import com.gamepackage.managers.ResourceManager;
import com.gamepackage.scenes.BoxingRingScene;

public class GameActivity extends BaseGameActivity {

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		SCREEN_WIDTH = displayMetrics.widthPixels;
		SCREEN_HEIGHT = displayMetrics.heightPixels;
		Camera camera = new Camera(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		return 
		new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, 
		new RatioResolutionPolicy(SCREEN_WIDTH, SCREEN_HEIGHT), camera);
	}
	
	@Override
	protected synchronized void onCreateGame() {
		getEngine().registerUpdateHandler(new FPSLogger());
		GameManager.getInstance()._init(this, getEngine());
		super.onCreateGame();
	}
	
	@Override
	public void onCreateResources(OnCreateResourcesCallback resourcesCallback) throws Exception {
		ResourceManager.preparedResources();
		ResourceManager.getInstance().createTextureRegion();
		ResourceManager.getInstance().loadTexture();
		
		resourcesCallback.onCreateResourcesFinished();
	}
	
	@Override
	public void onCreateScene(OnCreateSceneCallback sceneCallback) throws Exception {
		// MenuMainScene menuMainScene = new MenuMainScene();
		BoxingRingScene boxingRingScene = new BoxingRingScene(); 
				
		sceneCallback.onCreateSceneFinished(boxingRingScene);
	}
	
	@Override
	public void onPopulateScene(Scene scene, OnPopulateSceneCallback populateSceneCallback) throws Exception {
		populateSceneCallback.onPopulateSceneFinished();
	}

}
