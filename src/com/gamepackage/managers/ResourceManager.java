/**
 * 
 */
package com.gamepackage.managers;

import java.util.HashMap;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import android.content.Context;

/**
 * @author Walace
 *
 */
public class ResourceManager {
	
	private static final ResourceManager INSTANCE = new ResourceManager();
	
	private static HashMap<String, BitmapTextureAtlas> mapTexturesAtlas;
	private static HashMap<String, ITextureRegion> mapTexturesRegion;
	private static HashMap<String, ITiledTextureRegion> mapTiledTexturesRegion;

	public static ResourceManager getInstance() {
		return INSTANCE;
	}
	
	public static void preparedResources() {		
		mapTexturesAtlas = new HashMap<String, BitmapTextureAtlas>();
		mapTexturesRegion = new HashMap<String, ITextureRegion>();
		mapTiledTexturesRegion = new HashMap<String, ITiledTextureRegion>();
	}
	
	public void createTextureRegion() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		/**
		 * Recupeara as instancias de Engine e Context
		 */
		Engine engine = GameManager.getInstance().getEngine();
		Context context = GameManager.getInstance().getContext();
		
		/**
		 * Carregamento dos Atlas do jogo.
		 */
		mapTexturesAtlas.put("players", 
				new BitmapTextureAtlas(engine.getTextureManager(), 1024, 1024, 
						TextureOptions.BILINEAR_PREMULTIPLYALPHA));
		mapTexturesAtlas.put("background", 
				new BitmapTextureAtlas(engine.getTextureManager(), 1024, 1024, 
						BitmapTextureFormat.RGBA_8888, TextureOptions.NEAREST));
		mapTexturesRegion.put("background", 
				BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mapTexturesAtlas.get("background"), context, "boxing-ring.jpg", 0, 0));		
		
		/**
		 * Carregamento das texturas das partes
		 * que compoem o oponente
		 */
		mapTiledTexturesRegion.put("bodyOpponent", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "body.png", 0, 0, 1, 1));
		mapTiledTexturesRegion.put("brainOpponent", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "brain.png", 562, 0, 1, 1));
		mapTiledTexturesRegion.put("handLeftOpponent", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "head-left.png", 321, 0, 1, 1));
		mapTiledTexturesRegion.put("handRightOpponent", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "head-right.png", 463, 0, 1, 1));
		/*
		mapTexturesRegion.put("punching", 
				BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						mapTexturesAtlas.get("players"), context, "punching.png", 792, 372));		
		
		
		/**
		 * Carregamento das texturas das partes
		 * que compoem o player
		 */		
		mapTiledTexturesRegion.put("gloveLeftPlayer", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "branch-left.png", 0, 372, 2, 1));
		mapTiledTexturesRegion.put("gloveRightPlayer", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "branch-right.png", 499, 372, 2, 1));
		/*
		mapTiledTexturesRegion.put("gloveLeftPlayerPunch", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "left-head-punch.png", 338, 372, 1, 1));
		mapTiledTexturesRegion.put("gloveRightPlayerPunch", 
				BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
						mapTexturesAtlas.get("players"), context, "right-head-punch.png", 587, 372, 1, 1));
						*/
	}
	
	
	public void loadTexture() {
		mapTexturesAtlas.get("players").load();
		mapTexturesAtlas.get("background").load();
	}
	
	public void unloadTexture() {
		mapTexturesAtlas.get("players").unload();
		mapTexturesAtlas.get("background").unload();
	}

	public ITextureRegion getTexture(String source) {
		return mapTexturesRegion.get(source);
	}
	
	public ITiledTextureRegion getTileTexture(String source) {
		return mapTiledTexturesRegion.get(source);
	}
	
	public BitmapTextureAtlas getBitmapTextureAtlas(String source) {
		return mapTexturesAtlas.get(source);
	}
	
}
