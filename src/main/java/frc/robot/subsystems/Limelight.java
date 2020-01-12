/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */
  private  NetworkTable table; 
  private  NetworkTableInstance inst;
  private  NetworkTableEntry tv;
  private  NetworkTableEntry tx;
  private  NetworkTableEntry ty;
  private  NetworkTableEntry ta;
  private  NetworkTableEntry ledMode;

  private  boolean isDetected;
  private  double x = 0;
  private  double y = 0;
  private  double area = 0;







  public Limelight()  {
     
  }

  public void printValues(){

    
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  tx = table.getEntry("tx");
  ty = table.getEntry("ty");
  ta = table.getEntry("ta");
  tv = table.getEntry("tv");

    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimeilightY", y);
    SmartDashboard.putNumber("LimeLightArea", area);
  }

  public boolean isTargetDetected()
  {
    if(tv.getDouble(0.0) == 1)
    { 
      return true;
    }
    return false;
  }
  public double getDistance()
  {
    double area = ta.getDouble(0.0);
    double distance; // mathwork needed base on height of bot and angle of camera
    return area;

  }
  public double getTX(){
    x = tx.getDouble(0.0);
    return x;
  }

  public double getTY(){
    y = ty.getDouble(0.0);
    return y;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
