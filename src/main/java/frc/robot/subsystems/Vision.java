// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;


public class Vision extends SubsystemBase {
  private final PhotonCamera CameraOne;

  public Vision() {
    CameraOne = new PhotonCamera(VisionConstants.CAMERA_ONE_NAME);
  }

  public double getTargetYaw(int targetID) {
    var result = CameraOne.getLatestResult();
    if (result.hasTargets()) {
        for (var target : result.getTargets()) {
            if (target.getFiducialId() == targetID) {
                return target.getYaw();
            }
        }
    }
    return 0; // Return 0 if target not found (or a special value like -999)
  }

  public boolean hasTarget(int targetID) {
    var result = CameraOne.getLatestResult();
    if (result.hasTargets()) {
        for (var target : result.getTargets()) {
            if (target.getFiducialId() == targetID) return true;
        }
    }
    return false;
  }

  @Override
  public void periodic() {
  }
}
