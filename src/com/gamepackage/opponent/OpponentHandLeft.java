/**
 * 
 */
package com.gamepackage.opponent;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import android.opengl.GLES20;

import com.gamepackage.managers.GameManager;

/**
 * @author Walace
 *
 */
public class OpponentHandLeft extends AnimatedSprite implements IOpponentParts {

	private PhysicsHandler physicsHandler;
	
	/**
	 * @param pX
	 * @param pY
	 * @param pTiledTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public OpponentHandLeft(float pX, float pY,	ITiledTextureRegion tiledTexture) {
		super(pX, pY, tiledTexture, GameManager.getInstance().getEngine().getVertexBufferObjectManager());
		
		physicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(physicsHandler);
		
		
		preparedAnimation();
		setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}

	/* (non-Javadoc)
	 * @see com.gamepackage.IOpponentParts#preparedAnimation()
	 */
	@Override
	public void preparedAnimation() {
		IEntityModifier entityModifier = new LoopEntityModifier(
				new SequenceEntityModifier(
						new JumpModifier(1.9f, getX(), getX(), getY(), getY(), -15.5f)
				)
		);
		registerEntityModifier(entityModifier.deepCopy());
	}
	
	public void animatedPunch() {
		IEntityModifier entityModifier = new SequenceEntityModifier(
			new ParallelEntityModifier(
				new AlphaModifier(0.1f, 0f, 1f),
				new ScaleModifier(0.7f, 1.7f, 1f),
				new RotationByModifier(0.7f, -15f)
			)
		);
		registerEntityModifier(entityModifier.deepCopy());
	}

}
