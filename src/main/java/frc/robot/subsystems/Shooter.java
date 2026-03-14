// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
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

    // Create the configuration object
    TalonFXConfiguration shooterConfigs = new TalonFXConfiguration();
    TalonFXConfiguration indexerConfigs = new TalonFXConfiguration();

    shooterConfigs.Voltage.PeakForwardVoltage = 11.5;
    shooterConfigs.Voltage.PeakReverseVoltage = -11.5;

    indexerConfigs.Voltage.PeakForwardVoltage = 11.5;
    indexerConfigs.Voltage.PeakReverseVoltage = -11.5;

    // Set PID gains (Example values - these MUST be tuned for your shooter)
    var shooterSlot0 = shooterConfigs.Slot0;
    shooterSlot0.kP = 0.0; // An error of 1 rps results in 0.12 duty cycle output
    shooterSlot0.kI = 0.0;
    shooterSlot0.kD = 0.0;
    shooterSlot0.kV = 0.11; // Theoretical: 1 / Max Velocity (in rotations per second)
    shooterSlot0.kS = 0.01; // Voltage/Output needed to overcome static friction

    var indexerSlot0 = indexerConfigs.Slot0;
    indexerSlot0.kP = 0.0; // An error of 1 rps results in 0.12 duty cycle output
    indexerSlot0.kI = 0.0;
    indexerSlot0.kD = 0.0;
    indexerSlot0.kV = 0.11; // Theoretical: 1 / Max Velocity (in rotations per second)
    indexerSlot0.kS = 0.01; // Voltage/Output needed to overcome static friction

    shooterConfigs.MotionMagic.MotionMagicCruiseVelocity = 10; 
    shooterConfigs.MotionMagic.MotionMagicAcceleration = 150;

    // Indexer: Snappy movements to feed the note quickly
    indexerConfigs.MotionMagic.MotionMagicCruiseVelocity = 20;
    indexerConfigs.MotionMagic.MotionMagicAcceleration = 100;

    // Apply to your TalonFX motor instance
    shooterMotor.getConfigurator().apply(shooterConfigs);
    indexerMotor.getConfigurator().apply(indexerConfigs);
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
        shooterMotor.setControl(ShooterConstants.ACTIVE_SHOOT_DUTY_CYCLE); 
        indexerMotor.setControl(ShooterConstants.INDEXER_INTAKE_DUTY_CYCLE);
      }, 
      () -> {
        shooterMotor.setControl(ShooterConstants.INACTIVE_DUTY_CYCLE);
        indexerMotor.setControl(ShooterConstants.INACTIVE_DUTY_CYCLE);
      });    
  }

  @Override
  public void periodic() {}
}
