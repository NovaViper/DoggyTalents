package doggytalents.client.renderer.entity;

import doggytalents.ModItems;
import doggytalents.client.model.ModelDog;
import doggytalents.entity.EntityDog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerBone implements LayerRenderer<EntityDog> {
	
    protected final RenderDog renderDog;

    public LayerBone(RenderDog renderDog) {
        this.renderDog = renderDog;
    }

    @Override
    public void doRenderLayer(EntityDog dog, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	if(dog.hasBone()) {
	    	
	        GlStateManager.pushMatrix();
	
	        if (this.renderDog.getMainModel().isChild) {
	            float f = 0.5F;
	            GlStateManager.translate(0.0F, 0.75F, 0.0F);
	            GlStateManager.scale(0.5F, 0.5F, 0.5F);
	        }
	
	        this.renderHeldItem(dog, new ItemStack(Items.BONE), ItemCameraTransforms.TransformType.NONE, EnumHandSide.RIGHT);
	        GlStateManager.popMatrix();
    	}
    }

    private void renderHeldItem(EntityLivingBase entitylivingbaseIn, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide handSide) {
 
    	if(stack != null) {
            GlStateManager.pushMatrix();

            if(entitylivingbaseIn.isSneaking())
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
         

            this.translateToHand(handSide);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(45.0F, 0.0F, 0.0F, 1.0F);

            GlStateManager.translate(0.20, -0.10, -0.10);
            
           // GlStateManager.rotate(90F, 0.0F, 0F, 1F);
            boolean flag = handSide == EnumHandSide.LEFT;

           // GlStateManager.scale(0.8F, 0.8F, 0.8F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entitylivingbaseIn, stack, transform, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide p_191361_1_) {
        ((ModelDog)this.renderDog.getMainModel()).wolfHeadMain.postRender(0.0625F);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}