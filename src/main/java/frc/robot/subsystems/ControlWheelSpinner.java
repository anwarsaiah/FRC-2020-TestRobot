/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlWheelSpinner extends SubsystemBase {
  /**
   * Creates a new ControlWheelSpinner.
   */

  private CANSparkMax ControlWheelSpinner;
  public ControlWheelSpinner() {
    ControlWheelSpinner = new CANSparkMax(Constants.kControlPanelSpinner, MotorType.kBrushless);
  }

  public void spin (double speed) {
    ControlWheelSpinner.set(speed);
  }

  public void spinByEncoder (double revolutions) {
    CANEncoder encoder = ControlWheelSpinner.getEncoder();
    double start = encoder.getPosition();
    System.out.println(start);
    while ((encoder.getPosition() - start) < revolutions) {
      ControlWheelSpinner.set(0.4);
    }
    ControlWheelSpinner.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}