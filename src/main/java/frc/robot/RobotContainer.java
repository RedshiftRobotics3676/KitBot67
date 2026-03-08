// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  private final CommandPS4Controller controller;
  private final Drive drivetrain;
  private final Shooter shooter;

  public RobotContainer() {
    drivetrain = new Drive();  
    shooter = new Shooter();
    controller = new CommandPS4Controller(0); 
    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new InstantCommand(() -> {
      drivetrain.tankMovementInput(-controller.getLeftY(), -controller.getRightY());
    }, drivetrain));
    controller.R2().whileTrue(shooter.shoot());
    controller.L2().whileTrue(shooter.intake());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
