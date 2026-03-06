// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private final WPI_TalonSRX frontLeftMotor;
  private final WPI_TalonSRX backLeftMotor;
  private final WPI_TalonSRX frontRightMotor;
  private final WPI_TalonSRX backRightMotor;
  private final DifferentialDrive differentialDriveController;

  /** Creates a new Drive. */
  public Drive() {
    // Create motors with appropriate CAN IDs
    // TODO: Move to constants file
    frontLeftMotor = new WPI_TalonSRX(0);
    backLeftMotor = new WPI_TalonSRX(1);
    frontRightMotor = new WPI_TalonSRX(2);
    backRightMotor = new WPI_TalonSRX(3);
    
    // Configure motor reversals
    frontLeftMotor.setInverted(false);
    backLeftMotor.setInverted(false);
    frontRightMotor.setInverted(true);
    backRightMotor.setInverted(true);
    
    // Make back motors follow the front motors
    backLeftMotor.follow(frontLeftMotor);
    backRightMotor.follow(frontRightMotor);

    differentialDriveController = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    differentialDriveController.setDeadband(0.1);
  }
  
  public void teleopPeriodic() {}
  
  public void tankMovementInput(double leftSpeed, double rightSpeed) {
    differentialDriveController.tankDrive(leftSpeed, rightSpeed);
  }
  
  @Override
  public void periodic() {}
}
