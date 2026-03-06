// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;

public class RobotContainer {
  // private final CommandXboxController controller;
  private final CommandGenericHID controller;
  private final Drive drivetrain;

  public RobotContainer() {
    drivetrain = new Drive();  
    controller = new CommandXboxController(0); 
    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(new InstantCommand(() -> {
      // drivetrain.tankMovementInput(-controller.getLeftY(), -controller.getRightY());
      drivetrain.tankMovementInput(-controller.getRawAxis(2), -controller.getRawAxis(3));
    }));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
