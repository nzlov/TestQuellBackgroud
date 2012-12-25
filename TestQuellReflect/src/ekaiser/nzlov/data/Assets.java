package ekaiser.nzlov.data;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	private static TextureAtlas texturePack = null;

	private static BitmapFont baseFont = null;

	private static HashMap<String, Sound> sounds = null;

	public static Music screenMusic = null;
	
	//图形纹理
	public static Sprite bg = null;
	public static Sprite s1 = null;
	public static Sprite s2 = null;
	public static Sprite s3 = null;
	
	
	public static boolean createTexture() {
		texturePack = new TextureAtlas(Gdx.files.internal(StaticData.RESOURCE_GRAPHICS
					+ "quell.pack"));
		
		bg = texturePack.createSprite("back");
		s1 = texturePack.createSprite("1");
		s2 = texturePack.createSprite("2");
		s3 = texturePack.createSprite("3");
		return true;
	}

	public static boolean disStartBg() {
		if (texturePack != null) {
			texturePack.dispose();
			texturePack = null;
			return true;
		}
		return false;
	}

	public static BitmapFont createBaseFont() {
		if (baseFont == null) {
			baseFont = new BitmapFont(
					Gdx.files.internal(StaticData.RESOURCE_FONT
							+ "base.fnt"), false);
		}
		return baseFont;
	}

	public static void loadSound(String name) {
		if (sounds == null) {
			sounds = new HashMap<String, Sound>();
		}
		if (sounds != null) {
			sounds.put(name, Gdx.audio.newSound(Gdx.files
					.internal(StaticData.RESOURCE_AUDIO_SE + name)));
		}
	}
}
