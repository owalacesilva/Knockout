/**
 * 
 */
package com.gamepackage.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.util.color.Color;

import com.gamepackage.utils.SceneType;

/**
 * @author Walace
 *
 */
public class MenuMainScene extends GameScene {

	/**
	 * @param childCount
	 */
	public MenuMainScene() {
		super();
		createBackground();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.MENU_MAIN;
	}

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void createBackground() {
		setBackground(new Background(Color.YELLOW));		
	}	

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}

}
