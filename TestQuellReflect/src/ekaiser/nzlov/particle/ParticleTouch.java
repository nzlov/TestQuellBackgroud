package ekaiser.nzlov.particle;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ekaiser.nzlov.data.StaticData;

public class ParticleTouch {

	private static ParticleTouch def = null;
	private static boolean isDraw;

	private ParticleEffect particle;
	private ParticleEffect tem;
	private ParticleEffectPool particlepool;
	private ArrayList<ParticleEffect> particlelist;

	public static void InitParticleTouch(SpriteBatch batch) {
		if (def == null && isDraw) {
			def = new ParticleTouch();
		}
		if (def != null && isDraw) {
			def.draw(batch);
		}
	}
	
	public static void Open(){
		isDraw = true;
	}
	public static void Close(){
		isDraw = false;
	}

	private ParticleTouch() {

		particle = new ParticleEffect();
		particle.load(
				Gdx.files.internal(StaticData.RESOURCE_PARTICLE
						+ "particle.p"),
				Gdx.files.internal(StaticData.RESOURCE_PARTICLE));
		particlepool = new ParticleEffectPool(particle, 5, 10);
		particlelist = new ArrayList<ParticleEffect>();
	}

	public void draw(SpriteBatch batch) {
		if (Gdx.input.isTouched()) {
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();

			float sw = 640 / w;
			float sh = 480 / h;
			tem = particlepool.obtain();
			tem.setPosition(Gdx.input.getX()*sw, (Gdx.graphics.getHeight()
					- Gdx.input.getY())*sh);
			particlelist.add(tem);
		}
		batch.begin();
		for (int i = 0; i < particlelist.size(); i++) {
			particlelist.get(i).draw(batch, Gdx.graphics.getDeltaTime());
		}
		batch.end();

		ParticleEffect temparticle;
		for (int i = 0; i < particlelist.size(); i++) {
			temparticle = particlelist.get(i);
			if (temparticle.isComplete()) {
				particlelist.remove(i);
			}
		}
	}
}
