/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Auto;
import frc.robot.subsystems.ArticulatingHood;
import frc.robot.subsystems.ControlWheelSpinner;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = new Drivetrain();
  private final ControlWheelSpinner controlWheelSpinner = new ControlWheelSpinner();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final ArticulatingHood articulatingHood = new ArticulatingHood();
  private final Lift lift = new Lift();
  private final AHRS gyro = new AHRS();

  private final Joystick joystick1 = new Joystick(OIConstants.kJoystick1);
  private final Joystick joystick2 = new Joystick(OIConstants.kJoystick2);

  private final XboxController xboxController = new XboxController(OIConstants.kXboxController);
  public double getGyro(){
    return gyro.getAngle();
  }


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    shooter.setDefaultCommand(
      new RunCommand(() -> SmartDashboard.putNumber("Shooter RPM", shooter.setPower(0)), shooter));
    drivetrain.setDefaultCommand
      (new RunCommand(() -> drivetrain.setTank(-joystick1.getY(), joystick2.getY()), drivetrain));
    articulatingHood.setDefaultCommand(
      new RunCommand(() -> articulatingHood.setPower(0), articulatingHood));
    //controlWheelSpinner.setDefaultCommand(new RunCommand(() -> controlWheelSpinner.spin(joystick1.getX()), controlWheelSpinner));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(joystick1, OIConstants.kSpinWheel).whileHeld(
      new StartEndCommand(
        () -> controlWheelSpinner.spin(.5), 
        () -> controlWheelSpinner.spin(0), controlWheelSpinner));
        
    new JoystickButton(joystick1, OIConstants.kRunIntake).whileHeld(
      new StartEndCommand(
        () -> intake.setIntake((joystick1.getZ() + 1)/2),
        () -> intake.setIntake(0), intake));

    new JoystickButton(xboxController, OIConstants.kRunShooter).whileHeld(
      new RunCommand(
        () -> SmartDashboard.putNumber("Shooter RPM", shooter.setPower(SmartDashboard.getNumber("Shooter Output", 0))), shooter));

    new JoystickButton(joystick2, OIConstants.kSetLift).whileHeld(
      new StartEndCommand(
        () -> lift.setLift(joystick2.getZ()),
        () -> lift.setLift(0), lift));
        
    new JoystickButton(joystick1, OIConstants.kHoodPosition)
      .whenPressed(new RunCommand(
      () -> articulatingHood.setHoodSetPoint(SmartDashboard.getNumber("Hood Setpoint", 90)), articulatingHood))
      .whileHeld(new RunCommand(() -> articulatingHood.setHoodPosition(), articulatingHood), true);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Auto(gyro, 90.0, drivetrain);
  }
}
