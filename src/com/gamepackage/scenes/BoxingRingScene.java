/**
 * 
 */
package com.gamepackage.scenes;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.batch.SpriteGroup;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.color.Color;

import com.gamepackage.GameActivity;
import com.gamepackage.managers.GameManager;
import com.gamepackage.managers.ResourceManager;
import com.gamepackage.opponent.OpponentBody;
import com.gamepackage.opponent.OpponentBrain;
import com.gamepackage.opponent.OpponentHandLeft;
import com.gamepackage.opponent.OpponentHandRight;
import com.gamepackage.player.GloveLeft;
import com.gamepackage.player.GloveRight;
import com.gamepackage.utils.SceneType;

/**
 * @author Walace
 *
 */
public class BoxingRingScene extends GameScene implements IOnSceneTouchListener {

	/**
	 * 
	 */
	public BoxingRingScene() {
		super();
		
		createBackground();
		createScene();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.BOXING_RING;
	}

	@Override
	public void createScene() {
		BitmapTextureAtlas playersTextureAtlas = ResourceManager.getInstance().getBitmapTextureAtlas("players");
		
		ITiledTextureRegion gloveLeftTiledTexture = ResourceManager.getInstance().getTileTexture("gloveLeftPlayer");
		ITiledTextureRegion gloveRightTiledTexture = ResourceManager.getInstance().getTileTexture("gloveRightPlayer");
		ITiledTextureRegion oppBodyTiledTexture = ResourceManager.getInstance().getTileTexture("bodyOpponent");
		ITiledTextureRegion oppBrainTiledTexture = ResourceManager.getInstance().getTileTexture("brainOpponent");
		ITiledTextureRegion oppHandLeftTiledTexture = ResourceManager.getInstance().getTileTexture("handLeftOpponent");
		ITiledTextureRegion oppHandRightTiledTexture = ResourceManager.getInstance().getTileTexture("handRightOpponent");
		
		// --------------------------------------------------------------------------------------------------------
		float reutilizeX = 0;
		float reutilizeY = 0;
		OpponentBody oppBody = new OpponentBody(reutilizeX, reutilizeY, oppBodyTiledTexture);
		
		reutilizeX = (oppBody.getWidth() / 2) - (oppBrainTiledTexture.getWidth() / 2);
		reutilizeY = ((oppBrainTiledTexture.getHeight() / 2) + (oppBrainTiledTexture.getHeight() / 4)) * -1;
		OpponentBrain oppBrain = new OpponentBrain(reutilizeX, reutilizeY, oppBrainTiledTexture);
		
		reutilizeX = 0;
		reutilizeY = 10;
		OpponentHandLeft oppHandLeft = new OpponentHandLeft(reutilizeX, reutilizeY, oppHandLeftTiledTexture);
		
		reutilizeX = (oppBody.getWidth() / 2);
		reutilizeY = 10;
		OpponentHandRight oppHandRight = new OpponentHandRight(reutilizeX, reutilizeY, oppHandRightTiledTexture);
		
		SpriteGroup opponentParts = 
				new SpriteGroup(playersTextureAtlas, 4, GameManager.getInstance().getEngine().getVertexBufferObjectManager());
		opponentParts.setPosition(
				((GameActivity.SCREEN_WIDTH - oppBodyTiledTexture.getWidth()) / 2 ), 
				((GameActivity.SCREEN_HEIGHT - oppBodyTiledTexture.getHeight()))
		);
		
		opponentParts.attachChild(oppBody);
		opponentParts.attachChild(oppBrain);
		opponentParts.attachChild(oppHandLeft);
		opponentParts.attachChild(oppHandRight);
		attachChild(opponentParts);
		// --------------------------------------------------------------------------------------------------------		
		float gloveLeftX = (GameActivity.SCREEN_WIDTH / 2) - gloveLeftTiledTexture.getWidth();
		float gloveLeftY = (GameActivity.SCREEN_HEIGHT - gloveLeftTiledTexture.getHeight());
		GloveLeft gloveLeft = new GloveLeft(gloveLeftX, gloveLeftY, gloveLeftTiledTexture);
		
		float gloveRightX = (GameActivity.SCREEN_WIDTH / 2);
		float gloveRightY = (GameActivity.SCREEN_HEIGHT - gloveRightTiledTexture.getHeight());		
		GloveRight gloveRight = new GloveRight(gloveRightX, gloveRightY, gloveRightTiledTexture);
		
		setOnSceneTouchListener(this);
		
		attachChild(gloveLeft);
		attachChild(gloveRight);
	}
	
	@Override
	public void createBackground() {
		Sprite spriteEntity = new Sprite(0, 0, 
				ResourceManager.getInstance().getTexture("background"),
				GameManager.getInstance().getEngine().getVertexBufferObjectManager());
		SpriteBackground eBackground = new SpriteBackground(spriteEntity);
		
		setBackground(eBackground);
		setBackgroundEnabled(true);
	}	

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent touchEvent) {
		if(touchEvent.isActionDown()) {
			if(touchEvent.getX() >= (GameActivity.SCREEN_WIDTH / 2)) {
				((GloveRight) pScene.getChildByTag(GloveRight.TAG)).doActionPunchUp();
				((GloveLeft) pScene.getChildByTag(GloveLeft.TAG)).doActionPunchDown();
			} else if(touchEvent.getX() < (GameActivity.SCREEN_WIDTH / 2)) {
				((GloveLeft) pScene.getChildByTag(GloveLeft.TAG)).doActionPunchUp();
				((GloveRight) pScene.getChildByTag(GloveRight.TAG)).doActionPunchDown();				
			}
			return true;
		}
		return false;
	}

}
