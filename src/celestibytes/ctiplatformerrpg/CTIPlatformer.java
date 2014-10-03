package celestibytes.ctiplatformerrpg;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL15;

import okkapel.kkplglutil.rendering.GLHandler;
import okkapel.kkplglutil.rendering.GLRenderMethod;
import okkapel.kkplglutil.rendering.GLRenderObjPointer;
import okkapel.kkplglutil.rendering.RenderBufferGenerator;
import okkapel.kkplglutil.rendering.font.FontFileSource;
import okkapel.kkplglutil.rendering.font.FontRenderer;
import okkapel.kkplglutil.util.Colour;
import okkapel.kkplglutil.util.KeyBind;
import okkapel.kkplglutil.util.KeyBindHandler;
import okkapel.kkplglutil.util.TextureLoader;
import celestibytes.ctie.core.Game;
import celestibytes.ctie.d2.World;
import celestibytes.ctie.entity.EntityPlayer;
import celestibytes.ctie.input.BasicGameInput;
import celestibytes.ctie.util.Direction;
import celestibytes.lib.almightytext.Advstr;
import celestibytes.lib.almightytext.TranslationHandler;

public class CTIPlatformer extends Game {
	
	public static Game theGame;
	
	private static int guiTex = -1;
	
	private EntityPlayer testPlr;
	
	private GLRenderObjPointer plrRender;
	private GLRenderObjPointer rtxtp;
	
	private World testWorld;
	
	public static float RENDER_SCALE = 1f;
	
	public static void main(String[] args) {
		theGame = new CTIPlatformer();
		TranslationHandler.init(new File("res/languages"));
		theGame.start();
		
//		System.out.println("CharWidth: " + ffs.getCharWidth(96));
	}

	public CTIPlatformer() {
		super(Version.getTitle(), 60, 480, 360);
	}

	@Override
	protected void preLoad() {
		guiTex = TextureLoader.loadTexture("res/textures/gui/gui_decoration.png", false).getTextureId();
		setGuiDecorTex(guiTex);
	}

	@Override
	protected void init() {
		GLHandler.init(); // move to engine?
		FontRenderer.init(new File("res/textures/font/font.png"), new File("testData.dat")); // move to engine?
		testPlr = new EntityPlayer();
		
		RenderBufferGenerator rbg = RenderBufferGenerator.INSTANCE;
		rbg.addVertexWColorWUV(-20f, -20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(-20f, 20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(20f, 20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		
		rbg.addVertexWColorWUV(20f, 20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(20f, -20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		rbg.addVertexWColorWUV(-20f, -20f, 0f, 1f, 0f, 1f, 1f, 0f, 0f);
		plrRender = GLHandler.createROBJ(rbg.createBuffer(), GL15.GL_DYNAMIC_DRAW, null, 6, GLRenderMethod.VERTEX_BUFFER_OBJECT);
		rtxtp = FontRenderer.createTextRobj(50f, 50f, 20f, "HELLO WORLD".toCharArray(), Colour.GREEN);
		
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
		KeyBindHandler.addKeyBind(new KeyBind(false, Keyboard.KEY_SPACE) {
			public void onKeyUp() {}
			public void onKeyPushedDown() {testPlr.setMotionY(-40f);}
			public void onKeyHeldDown() {}
		});
		
		testWorld = new World();
	}

	@Override
	protected void deInit() {
		GLHandler.deinit();
		glDeleteTextures(guiTex);
		FontRenderer.deleteTextures();
	}

	@Override
	protected void gameLoop() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		testPlr.positionUpdate(getDelta());
		
//		System.out.println(BasicGameInput.getPlayerMovingX().name() + " " + BasicGameInput.getPlayerMovingY().name());
		
		
		glTranslatef(-testPlr.getX()*World.tilesize, -testPlr.getY()*World.tilesize, 0f);
		testWorld.renderWorld();
		glTranslatef(testPlr.getX()*World.tilesize, testPlr.getY()*World.tilesize, 0f);
		
//		GLHandler.renderRendPtr(testPlr.getRPTR());
		
		glLoadIdentity();
		
//		FontRenderer.renderStr("@PIZZANA; @LE683".toCharArray(), 0f, 0f);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		GLHandler.renderRendPtr(rtxtp);
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
		
//		plrRender.setVertPositions(testPlr.getX(), testPlr.getY(), 0, 6);
		plrRender.setVertPositionsSquare(-testPlr.getX()*World.tilesize, -testPlr.getY()*World.tilesize, 40f, 40f, 0, 6);
		GLHandler.renderRendPtr(plrRender);
	}
	
}
