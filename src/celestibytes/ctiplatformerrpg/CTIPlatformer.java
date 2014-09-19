package celestibytes.ctiplatformerrpg;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL15;

import okkapel.kkplglutil.rendering.GLHandler;
import okkapel.kkplglutil.rendering.GLRenderObjPointer;
import okkapel.kkplglutil.rendering.RenderBufferGenerator;
import okkapel.kkplglutil.util.KeyBind;
import okkapel.kkplglutil.util.KeyBindHandler;
import celestibytes.ctie.core.Game;
import celestibytes.ctie.entity.EntityPlayer;
import celestibytes.ctie.input.BasicGameInput;
import celestibytes.ctie.util.Direction;
import celestibytes.ctie.util.TextureLoader;

public class CTIPlatformer extends Game {
	
	public static Game theGame;
	
	private static int guiTex = -1;
	
	private EntityPlayer testPlr;
	
	private GLRenderObjPointer plrRender;
	
	public static void main(String[] args) {
		theGame = new CTIPlatformer();
		theGame.start();
	}

	public CTIPlatformer() {
		super("CTIPlatformer", 60, 960, 720);
	}

	@Override
	protected void preLoad() {
		guiTex = TextureLoader.loadTexture("res/textures/gui/gui_decoration.png");
		setGuiDecorTex(guiTex);
	}

	@Override
	protected void init() {
		GLHandler.init();
		testPlr = new EntityPlayer();
		
		RenderBufferGenerator rbg = RenderBufferGenerator.INSTANCE;
		rbg.addVertexWColorWUV(-20f, -20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(-20f, 20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(20f, 20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		
		rbg.addVertexWColorWUV(20f, 20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(20f, -20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(-20f, -20f, 0f, 1f, 0f, 0f, 1f, 0f, 0f);
		plrRender = GLHandler.createROBJ(rbg.createBuffer(), GL15.GL_DYNAMIC_DRAW, null, 6);
		
		KeyBindHandler.addKeyBind(new KeyBind(false, Keyboard.KEY_A) {
			public void onKeyUp() {}
			public void onKeyPushedDown() {}
			public void onKeyHeldDown() {BasicGameInput.setPlayerMovingX(Direction.LEFT);}
		});
		KeyBindHandler.addKeyBind(new KeyBind(false, Keyboard.KEY_D) {
			public void onKeyUp() {}
			public void onKeyPushedDown() {}
			public void onKeyHeldDown() {BasicGameInput.setPlayerMovingX(Direction.RIGHT);}
		});
		KeyBindHandler.addKeyBind(new KeyBind(false, Keyboard.KEY_W) {
			public void onKeyUp() {}
			public void onKeyPushedDown() {}
			public void onKeyHeldDown() {BasicGameInput.setPlayerMovingY(Direction.UP);}
		});
		KeyBindHandler.addKeyBind(new KeyBind(false, Keyboard.KEY_S) {
			public void onKeyUp() {}
			public void onKeyPushedDown() {}
			public void onKeyHeldDown() {BasicGameInput.setPlayerMovingY(Direction.DOWN);}
		});
	}

	@Override
	protected void deInit() {
		GLHandler.deinit();
		glDeleteTextures(guiTex);
	}

	@Override
	protected void gameLoop() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		testPlr.positionUpdate(getDelta());
		
//		System.out.println(BasicGameInput.getPlayerMovingX().name() + " " + BasicGameInput.getPlayerMovingY().name());
		
		glTranslatef(testPlr.getX(), testPlr.getY(), 0f);
		
		GLHandler.renderRendPtr(plrRender);
	}
	
}
