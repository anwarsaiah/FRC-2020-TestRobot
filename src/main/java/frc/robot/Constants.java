/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int kLeftMaster = 0;
    public static final int kRightMaster = 1;
    public static final int kLeftSlave = 2;
    public static final int kRightSlave = 3;

    public static final int kJoystick1 = 0;
    public static final int kJoystick2 = 1;

    public static final int kControlWheelSpinner = 6;

    public static final int limelightServo = 4; //change when you know the PWM

    public static final int kLeftShooter = 0;
    public static final int kRightShooter = 0;

    public static final float kFShooter = 0f;
    public static final float kPShooter = 0.2f;
}
