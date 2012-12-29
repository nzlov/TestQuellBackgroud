package ekaiser.nzlov.quell;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import ekaiser.nzlov.data.Assets;
import ekaiser.nzlov.particle.ParticleTouch;

public class MyGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Vector3 v;
	private Vector3 z;
	

	private boolean isMove = true;
	private boolean isScale = true;
		
	@Override
	public void create() {		
				
		Assets.createTexture();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(256,256);
		batch = new SpriteBatch();

		v = new Vector3(128, 128, 1);
		z = new Vector3(0,0, 1);

		Assets.s1.setPosition(64, 64);
		Assets.s2.setPosition(320, 64);
		Assets.s3.setPosition(576, 64);
		Assets.s3.setScale(0.1f);
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		ParticleTouch.InitParticleTouch(batch);

		if(Gdx.input.justTouched()){

			Gdx.app.log("TestQuellReflect", "x:" + Gdx.input.getX()+"y:"+Gdx.input.getY());
			Vector3 bv = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(bv);
			Gdx.app.log("TestQuellReflect-batch：", "x:" + bv.x+"y:"+bv.y);
			if(Gdx.input.getX()<Gdx.graphics.getWidth()/2){
				if(isMove){
					v.add(256, 0, 0);
				}else{
					v.add(-256, 0, 0);
				}
				if(v.x<128){
					v.x = 128;
					v.add(256, 0, 0);
					isMove = !isMove;
				}
				if(v.x > 640){
					v.x = 640;
					v.add(-256, 0, 0);
					isMove = !isMove;
				}
			}else{
				if(isScale){
					v.add(0, 0, -0.9f);
				}else{
					v.add(0, 0, 0.9f);
				}
				isScale = !isScale;
			}
		}
		
		camera.position.lerp(v, 5f * Math.min(0.06f, Gdx.graphics.getDeltaTime()));
		z.lerp(v, 5f * Math.min(0.06f, Gdx.graphics.getDeltaTime()));
		camera.zoom = z.z;
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Assets.bg.draw(batch);
		
		if(v.x == 128){
			Assets.s1.draw(batch);
		}
		if(v.x == 384){
			Assets.s2.draw(batch);
		}
		if(v.x == 640){
			Assets.s3.draw(batch);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
