package com.gamepackage.player;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import android.opengl.GLES20;

import com.gamepackage.managers.GameManager;

public class GloveRight extends AnimatedSprite {

	public static int TAG = 102;
	
	private PhysicsHandler handler;
	private IEntityModifier animatePositionOfAttack;
	private IEntityModifier animatePunchDown;
	private boolean attackOn;
	
	public GloveRight(float pX, float pY, ITiledTextureRegion tiledTexture) {
		super(pX, pY, tiledTexture, GameManager.getInstance().getEngine().getVertexBufferObjectManager());
		
		handler = new PhysicsHandler(this);
		attackOn = false;
		animatePositionOfAttack = new LoopEntityModifier(
			new SequenceEntityModifier(
				new JumpModifier(.9f, getX(), getX(), getY(), getY(), -10.5f)
			)
		);
		animatePunchDown = new SequenceEntityModifier(
			new ParallelEntityModifier(
				new RotationByModifier(.2f, -45f),
				new MoveByModifier(.2f, -50f, 40f)
			),
			new DelayModifier(.1f),
			new ParallelEntityModifier(
				new RotationByModifier(.2f, 45f),
				new MoveByModifier(.2f, 50f, -40f)
			)
		);
		animatePunchDown.setAutoUnregisterWhenFinished(true);
		
		setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		setTag(TAG);
		
		registerEntityModifier(animatePositionOfAttack.deepCopy());
		registerUpdateHandler(handler);
	}
	
	public void doActionPunchUp() {
		if(attackOn) {
			return;
		}
		animate(new long[] {10, 500, 50}, new int[] {0, 1, 0}, false, new IAnimationListener() {
			
			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				attackOn = true;
				pAnimatedSprite.setPosition(pAnimatedSprite.getX(), pAnimatedSprite.getY() - 50f);
				clearEntityModifiers();
			}
			
			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				registerEntityModifier(animatePositionOfAttack.deepCopy());
				attackOn = false;
			}
		});
	}	
	
	public void doActionPunchDown() {
		if(attackOn) {
			return;
		}		
		animate(new long[] {100, 500, 100}, new int[] {0, 0, 0}, false, new IAnimationListener() {
			
			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				attackOn = true;
				clearEntityModifiers();
				registerEntityModifier(animatePunchDown.deepCopy());
			}
			
			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				registerEntityModifier(animatePositionOfAttack.deepCopy());
				attackOn = false;
			}
			
		});
	}	
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
	}
}
