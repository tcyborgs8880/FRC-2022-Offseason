// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*

  Date: 3/22/22
  Name: Faaiz
  Task Done: Created class

*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort.Port;
import frc.robot.Robot;


public class Autonomous extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public static AHRS gyro;

  public Autonomous() {
    gyro = new AHRS(Port.kUSB);

  }

  public void print(){
    SmartDashboard.putNumber("turn Effort Raw:", gyro.getYaw());
    SmartDashboard.putNumber("turn Effort Mod:", gyro.getDisplacementX());
  }





  public void turnToAngle(double forwardSpeed, double theta){
    double leftTurn = 0, rightTurn = 0, minPower = .1;

    double  desiredEffort = 0.2,
            maxAngle = 45,
            currentAngle = gyro.getYaw(),
            desiredAngle = theta,
            maxError = maxAngle - desiredAngle,
            kP = desiredEffort / maxError,
            currentError = desiredAngle - currentAngle,
            rawTurningEffort = kP*currentError,
            turningEffort = rawTurningEffort;

    if(Math.abs(rawTurningEffort) < minPower) turningEffort = minPower * rawTurningEffort/Math.abs(rawTurningEffort); //Set minimum value
    if(Math.abs(rawTurningEffort) < 0.02) turningEffort = 0.0; //Set Deadzone

    if (turningEffort > 0){
      leftTurn = turningEffort;
      rightTurn = -turningEffort;
    }
    else {
      leftTurn = turningEffort;
      rightTurn = -turningEffort;
    }

    SmartDashboard.putNumber("kP: ", kP);
    SmartDashboard.putNumber("turn Effort Raw:", rawTurningEffort);
    SmartDashboard.putNumber("turn Effort Mod:", turningEffort);

    Robot.Drivetrain.tankDriveVolts(-(leftTurn), -(rightTurn));
  }






  @Override
  public void periodic() {
    // This method will be called onc.e per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
