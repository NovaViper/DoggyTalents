package doggytalents.network.packet.client;

import doggytalents.entity.EntityDog;
import doggytalents.network.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

public class DogTalentMessage extends AbstractServerMessage {
	
	public int entityId;
	public String talentId;
	
	public DogTalentMessage() {}
    public DogTalentMessage(int entityId, String talentId) {
        this.entityId = entityId;
        this.talentId = talentId;
    }
    
	@Override
	public void read(PacketBuffer buffer) {
		this.entityId = buffer.readInt();
		this.talentId = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void write(PacketBuffer buffer) {
		buffer.writeInt(this.entityId);
		ByteBufUtils.writeUTF8String(buffer, this.talentId);
	}
	
	@Override
	public void process(EntityPlayer player, Side side) {
		Entity target = player.worldObj.getEntityByID(this.entityId);
        
        if(!(target instanceof EntityDog))
        	return;
        
		EntityDog dog = (EntityDog)target;
        
		dog.talents.setLevel(this.talentId, dog.talents.getLevel(this.talentId) + 1);
	}
}
