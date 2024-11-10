package com.mrcrayfish.vehicle.client.render.vehicle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrcrayfish.vehicle.client.model.VehicleModels;
import com.mrcrayfish.vehicle.client.raytrace.MatrixTransform;
import com.mrcrayfish.vehicle.client.raytrace.RayTraceTransforms;
import com.mrcrayfish.vehicle.client.raytrace.TransformHelper;
import com.mrcrayfish.vehicle.client.render.AbstractLandVehicleRenderer;
import com.mrcrayfish.vehicle.client.render.Axis;
import com.mrcrayfish.vehicle.entity.properties.VehicleProperties;
import com.mrcrayfish.vehicle.entity.vehicle.DuneBuggyEntity;
import com.mrcrayfish.vehicle.init.ModEntities;
import com.mrcrayfish.vehicle.item.IDyeable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.entity.EntityType;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class DuneBuggyRenderer extends AbstractLandVehicleRenderer<DuneBuggyEntity>
{
    public DuneBuggyRenderer(EntityType<DuneBuggyEntity> type)
    {
        super(type, () -> VehicleProperties.get(type));
    }

    @Override
    protected void render(@Nullable DuneBuggyEntity vehicle, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, float partialTicks, int light)
    {
        this.renderDamagedPart(vehicle, VehicleModels.DUNE_BUGGY_BODY, matrixStack, renderTypeBuffer, light, partialTicks);
        this.renderSteeringWheel(vehicle, VehicleModels.DUNE_BUGGY_HANDLES, -5.0, 4.9039, 15.6378, 0.7F, -67.5F, matrixStack, renderTypeBuffer, light, partialTicks);

//        // Render the handles bars
//        matrixStack.pushPose();
//        matrixStack.translate(0.0, 0.0, 3.125 * 0.0625);
//        matrixStack.mulPose(Axis.XP.rotationDegrees(-22.5F));
//
////        if (vehicle != null)
////        {
////            float wheelAngle = Mth.lerp(partialTicks, vehicle.prevWheelAngle, vehicle.wheelAngle);
////            float wheelAngleNormal = wheelAngle / 45F;
////            float turnRotation = wheelAngleNormal * 15F;
////            matrixStack.mulPose(Axis.YP.rotationDegrees(turnRotation));
////        }
//
//        matrixStack.mulPose(Axis.XP.rotationDegrees(22.5F));
//        matrixStack.translate(0.0, 0.0, -0.2);
  //      this.renderDamagedPart(vehicle, VehicleModels.DUNE_BUGGY_HANDLES, matrixStack, renderTypeBuffer, light, partialTicks);

//        ItemStack wheelStack = this.wheelStackProperty.get(vehicle);
//        if (!wheelStack.isEmpty())
//        {
//            VehicleProperties properties = this.vehiclePropertiesProperty.get(vehicle);
//            Wheel wheel = properties.getFirstFrontWheel();
//            if (wheel != null)
//            {
//                matrixStack.pushPose();
//                matrixStack.translate(0.0, -0.355, 0.33);
//
//                if (vehicle != null)
//                {
//                    float frontWheelSpin = Mth.lerp(partialTicks, vehicle.prevFrontWheelRotation, vehicle.frontWheelRotation);
//                    if (vehicle.isMoving())
//                    {
//                        matrixStack.mulPose(Axis.XP.rotationDegrees(-frontWheelSpin));
//                    }
//                }
//
//                matrixStack.scale(wheel.getScaleX(), wheel.getScaleY(), wheel.getScaleZ());
//                matrixStack.mulPose(Axis.YP.rotationDegrees(180F));
//                int wheelColor = IDyeable.getColorFromStack(wheelStack);
//                RenderUtil.renderColoredModel(RenderUtil.getModel(wheelStack), ItemDisplayContext.NONE, false, matrixStack, renderTypeBuffer, wheelColor, light, OverlayTexture.NO_OVERLAY);
//                matrixStack.popPose();
//            }
//        }

        matrixStack.popPose();
    }

    @Override
    public void applyPlayerModel(DuneBuggyEntity entity, Player player, PlayerModel<?> model, float partialTicks)
    {
        float wheelAngle = this.wheelAngleProperty.get(entity, partialTicks);
        float wheelAngleNormal = wheelAngle / 45F;
        float turnRotation = wheelAngleNormal * 8F;
        model.rightArm.xRot = (float) Math.toRadians(-50F - turnRotation);
        model.leftArm.xRot = (float) Math.toRadians(-50F + turnRotation);
        model.rightLeg.xRot = (float) Math.toRadians(-65F);
        model.rightLeg.yRot = (float) Math.toRadians(30F);
        model.leftLeg.xRot = (float) Math.toRadians(-65F);
        model.leftLeg.yRot = (float) Math.toRadians(-30F);
    }

    @Nullable
    @Override
    public RayTraceTransforms getRayTraceTransforms()
    {
        return (tracer, transforms, parts) ->
        {
            TransformHelper.createTransformListForPart(VehicleModels.DUNE_BUGGY_BODY, parts, transforms);
            TransformHelper.createTransformListForPart(VehicleModels.DUNE_BUGGY_HANDLES, parts, transforms,
                    MatrixTransform.translate(0.0F, 0.0F, -0.0046875F));
            //TransformHelper.createFuelFillerTransforms(ModEntities.DUNE_BUGGY.get(), VehicleModels.FUEL_DOOR_CLOSED, parts, transforms);
        };
    }
}
