/**
 * 
 */
package com.gamepackage.scenes;

import org.andengine.entity.scene.Scene;

import com.gamepackage.utils.SceneType;

/**
 *	@author Walace
 *	
 *	"Classe Abstrat"
 *
 *	Está classe representa o Scene base do jogo
 *	todas as scenes devem ser herdadas dessa classe.
 */
public abstract class GameScene extends Scene {
	
	public GameScene() {
		super();
	}

	public abstract SceneType getSceneType();
	
	public abstract void createScene();
	
	public abstract void createBackground();
	
	public abstract void disposeScene();
	
	public abstract void onBackPressed();
	
}
