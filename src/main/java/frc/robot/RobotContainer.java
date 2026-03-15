// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.commands.Autoaim;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class RobotContainer {
  private final CommandPS4Controller controller;
  private final CommandPS4Controller controller2;
  private final Drive drivetrain;
  private final Shooter shooter;
  private final Vision vision;
  private final Autoaim autoaim;

  public RobotContainer() {
    drivetrain = new Drive();  
    shooter = new Shooter();
    vision = new Vision();
    autoaim = new Autoaim(drivetrain, vision);
    controller = new CommandPS4Controller(0);
    controller2 = new CommandPS4Controller(1);
    configureBindings();
  }

  private void configureBindings() {

    drivetrain.setDefaultCommand(new InstantCommand(() -> {
      drivetrain.tankMovementInput(-controller.getLeftY(), -controller.getRightY());
    }, drivetrain));
    controller2.R2().whileTrue(shooter.shoot());
    controller2.L2().whileTrue(shooter.intake());

    controller2.R1().whileTrue(autoaim);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
