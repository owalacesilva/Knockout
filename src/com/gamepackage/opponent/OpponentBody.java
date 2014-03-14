/**
 * 
 */
package com.gamepackage.opponent;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import android.opengl.GLES20;

import com.gamepackage.managers.GameManager;

/**
 * @author Walace
 *
 */
public class OpponentBody extends AnimatedSprite implements IOpponentParts {

	private PhysicsHandler physicsHandler; 
	
	/**
	 * @param pX
	 * @param pY
	 * @param pTiledTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public OpponentBody(float pX, float pY,	ITiledTextureRegion tiledTexture) {
		super(pX, pY, tiledTexture, GameManager.getInstance().getEngine().getVertexBufferObjectManager());
		
		physicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(physicsHandler);
		
		preparedAnimation();
	}

	/* (non-Javadoc)
	 * @see com.gamepackage.opponent.IOpponentParts#preparedAnimation()
	 */
	@Override
	public void preparedAnimation() {
		IEntityModifier entityModifier = new LoopEntityModifier(
				new SequenceEntityModifier(
						new JumpModifier(1.9f, getX(), getX(), getY(), getY(), -15.5f)
				)
		);
		setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		registerEntityModifier(entityModifier.deepCopy());
	}

}
