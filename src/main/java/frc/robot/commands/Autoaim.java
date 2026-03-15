// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Vision;
import frc.robot.Constants.VisionConstants;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Autoaim extends Command {

  private final Drive m_driveTrain;
  private final Vision m_vision;

  /** Creates a new Autoaim. */
  public Autoaim(Drive driveTrain, Vision visions){
    this.m_driveTrain = driveTrain;
    this.m_vision = visions;
    addRequirements(m_driveTrain, m_vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //HARD CODED ID, NEED TO IMPLEMENT DIFFERING FUNCTIONALITY
    int idToFind = 9;
    
    if (m_vision.hasTarget(idToFind)) {
        double yaw = m_vision.getTargetYaw(idToFind);
        
        double rotationSpeed = yaw * VisionConstants.VISION_TURN_KP; 
        if(Math.abs(yaw) > 0.3)
          m_driveTrain.tankMovementInput(rotationSpeed, -rotationSpeed);
        else
          m_driveTrain.tankMovementInput(0,0);
    }
    else{
      m_driveTrain.tankMovementInput(0,0 );
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}