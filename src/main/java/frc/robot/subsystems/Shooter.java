// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final TalonFX shooterMotor;
  private final TalonFX indexerMotor;

  public Shooter() {
    shooterMotor = new TalonFX(CANConstants.SHOOTER_MOTOR_ID);
    indexerMotor = new TalonFX(CANConstants.INDEXER_MOTOR_ID);
  }
  
  /**
   * When called, set the shooter to run continuously at a fixed speed until it is no longer being called, stopping the motors
   */
  public Command shoot() {
    return runEnd(
      () -> { 
        shooterMotor.setControl(ShooterConstants.ACTIVE_SHOOT_DUTY_CYCLE);
        indexerMotor.setControl(ShooterConstants.INDEXER_SHOOT_DUTY_CYCLE);
      }, 
      () -> {
        shooterMotor.setControl(ShooterConstants.INACTIVE_DUTY_CYCLE);
        indexerMotor.setControl(ShooterConstants.INACTIVE_DUTY_CYCLE);
      });    
  }

  public Command intake() {
    return runEnd(
      () -> { 
        indexerMotor.setControl(ShooterConstants.INDEXER_INTAKE_DUTY_CYCLE);
      }, 
      () -> {
        indexerMotor.setControl(ShooterConstants.INACTIVE_DUTY_CYCLE);
      });    
  }

  @Override
  public void periodic() {}
}
