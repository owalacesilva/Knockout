/**
 * 
 */
package com.gamepackage.managers;

import com.gamepackage.scenes.GameScene;
import com.gamepackage.scenes.MenuMainScene;
import com.gamepackage.utils.SceneType;

/**
 * @author Walace
 *
 *	Está classe gerencia os Scenes que compoem o jogo,
 *	definindo os tipos de scene disponiveis, qual scene
 *	está ativo, o proximo scene que será ativo, o scene anterior
 *	do atual, e carrega todos os recursos necessarios para
 *	montagem de cada scene.
 */
public class SceneManager {
	
	private static final SceneManager INSTANCE = new SceneManager();
	private GameScene currentScene;
	private SceneType currentSceneType;
	
	public SceneManager() {
		
	}
	
	public static SceneManager gerSceneManager() {
		return INSTANCE;
	}

	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public SceneType getCurrentSceneType() {
		return currentSceneType;
	}	

	public void setCurrentScene(GameScene currentScene) {
		GameManager.getInstance().getEngine().setScene(currentScene);
		this.currentScene = currentScene;
		this.currentSceneType = currentScene.getSceneType();
	}
	
	public void setCurrentScene(SceneType type) {
		switch (type) {
		case MENU_MAIN:
			setCurrentScene(new MenuMainScene());
			break;
		case GAME_OVER:
			setCurrentScene(new MenuMainScene());
			break;
		case BOXING_RING:
			setCurrentScene(new MenuMainScene());
			break;			
		default:
			setCurrentScene(new MenuMainScene());
			break;
		}
	}
	
}
