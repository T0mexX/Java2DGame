package background;

import background.BgConstants.BgEntityEnum;

public class BackgroundEntity {
	public int xPos;
	public int yPos;
	public int xSize;
	public int ySize;
	public BgEntityEnum entityId;
	public float offsetPercentage;
	
	public BackgroundEntity(int xPos, int yPos, int xSize, int ySize, BgEntityEnum entityId, float offsetPercentage) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSize = xSize;
		this.ySize = ySize;
		this.entityId = entityId;
		this.offsetPercentage = offsetPercentage;
	}
}
