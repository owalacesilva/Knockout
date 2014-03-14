/**
 * 
 */
package com.gamepackage.managers;

import org.andengine.engine.Engine;

import android.app.Activity;
import android.content.Context;

/**
 * @author Walace
 *
 */
public class GameManager {
	
	/**
	 * Está variavel de instancia representa o unico objeto
	 * que está classe terá na aplicação, além disso ela é
	 * uma instancia "volatile" tendo a caracteristica de que
	 * será acessada e modificada apenas por uma thread.
	 */
	private static volatile GameManager mInstance;
	
	private static Activity mActivityContext;
	private static Engine mEngine;
	
	private static boolean gameInitialized = false;
	
	private GameManager() {
		// private default
	}
	
	public static GameManager getInstance() {
		/**
		 * Padrão: Double-check Locking
		 * A pratica aplicada abaixo, evita que uma thread
		 * crie uma nova instance em cima de uma já criada
		 * quando duas thread acessam esse block de codigo
		 * a memos tempo sempre a segunda thread verifica
		 * se a primeira conseguiu criar a instance.
		 * caso contrario a instance é criada.
		 */
		if(mInstance == null) {
			synchronized (GameManager.class) {
				// Double check
				if(mInstance == null) {
					mInstance = new GameManager();
				}
			}
		}
		return mInstance;
	}
	
	public void _init(Activity mActivityContext, Engine mEngine) {
		if(gameInitialized) { 
			return; 
		}
		
		GameManager.mActivityContext = mActivityContext;
		GameManager.mEngine = mEngine;
		
		gameInitialized = !gameInitialized;
	}
	
	public Engine getEngine() {
		return mEngine;
	}

	public Context getContext() {
		return mActivityContext.getBaseContext();
	}
	
}
