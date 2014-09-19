package celestibytes.ctiplatformerrpg.world;

import org.lwjgl.opengl.GL15;

import celestibytes.ctiplatformerrpg.tile.Tile;
import okkapel.kkplglutil.rendering.GLHandler;
import okkapel.kkplglutil.rendering.GLRenderObjPointer;
import okkapel.kkplglutil.rendering.RenderBufferGenerator;

public class World {
	public int[] tiles;
	
	private GLRenderObjPointer rptr;
	
	public World() {
		tiles = new int[8*8];
		
		RenderBufferGenerator rbg = RenderBufferGenerator.INSTANCE;
		
		float tilesize = 60f;
		
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				rbg.addVertexWColorWUV(x*tilesize, y*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
				rbg.addVertexWColorWUV(x*tilesize, (y+1)*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
				rbg.addVertexWColorWUV((x+1)*tilesize, (y+1)*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
				
				rbg.addVertexWColorWUV((x+1)*tilesize, (y+1)*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
				rbg.addVertexWColorWUV((x+1)*tilesize, y*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
				rbg.addVertexWColorWUV(x*tilesize, y*tilesize, 0f, Tile.BLOCKCLR[(y*8+x)%5], 0f, 0f);
			}
		}
		
		rptr = GLHandler.createROBJ(rbg.createBuffer(), GL15.GL_STATIC_DRAW, null, 8*8*6);
		
	}
	
	public void renderWorld() {
		GLHandler.renderRendPtr(rptr);
	}
	
	
}
