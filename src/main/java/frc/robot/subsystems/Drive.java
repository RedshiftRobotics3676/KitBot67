// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.CANConstants;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private final TalonFX frontLeftMotor;
  private final TalonFX backLeftMotor;
  private final TalonFX frontRightMotor;
  private final TalonFX backRightMotor;
  private final DifferentialDrive differentialDriveController;

  public Drive() {
    // Create motors with appropriate CAN IDs
    frontLeftMotor = new TalonFX(CANConstants.FRONT_LEFT_MOTOR_ID);
    backLeftMotor = new TalonFX(CANConstants.BACK_LEFT_MOTOR_ID);
    frontRightMotor = new TalonFX(CANConstants.FRONT_RIGHT_MOTOR_ID);
    backRightMotor = new TalonFX(CANConstants.BACK_RIGHT_MOTOR_ID);
    
    // Configure motor reversals
    TalonFXConfiguration InvertConfig = new TalonFXConfiguration();
    TalonFXConfiguration NormalConfig = new TalonFXConfiguration();
    InvertConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    NormalConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    frontLeftMotor.getConfigurator().apply(NormalConfig);
    frontRightMotor.getConfigurator().apply(InvertConfig);
    
    // Make back motors follow the front motors
    backLeftMotor.setControl(new Follower(CANConstants.FRONT_LEFT_MOTOR_ID, MotorAlignmentValue.Aligned));
    backRightMotor.setControl(new Follower(CANConstants.FRONT_RIGHT_MOTOR_ID, MotorAlignmentValue.Aligned));

    differentialDriveController = new DifferentialDrive(frontLeftMotor::set, frontRightMotor::set);
    differentialDriveController.setDeadband(0.1);
  }
  
  public void teleopPeriodic() {}
  
  public void tankMovementInput(double leftSpeed, double rightSpeed) {
    differentialDriveController.tankDrive(leftSpeed, rightSpeed);
  }
  
  @Override
  public void periodic() {}
}
