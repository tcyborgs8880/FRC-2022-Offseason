// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*

  Date: 3/22/22
  Name: Faaiz
  Task Done: Created class

*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** An example command that uses an example subsystem. */
public class AutoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Autonomous m_autonomous;
  private final Drivetrain m_drivetrain;
  private final Shooter m_shooter;
  private final Intake m_intake; //Faaiz
  private final Timer timer;
  private final Dropper m_dropper;
  
  //private final Elevator m_elevator;
 // private final Intake m_intake;
  //private final Shooter m_shooter;

  /**
   * Creates a new ExampleCommand.
   * 
   * @param subsystem The subsystem used by this command.
   */
  public AutoCommand(Autonomous subsystem, Drivetrain drivetrain, Shooter shooter, Intake intake, Dropper dropper) {
    m_autonomous = subsystem;
    m_drivetrain = drivetrain;
    m_shooter = shooter; //Faaiz
    m_intake = intake;
    m_dropper = dropper;
    timer = new Timer();

    addRequirements(m_autonomous);
    addRequirements(m_drivetrain);
    addRequirements(m_shooter);
    addRequirements(m_intake);
    addRequirements(m_dropper); //Faaiz
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();  //Starts timer
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = timer.get();  //Gets time elapsed in seconds
    m_autonomous.print();

   m_autonomous.turnToAngle(0.2, 90);

    //if(time < 2){
      

   // }

    /* 
    //ROTATION CODE
    if  (time < 1.13){
      m_drivetrain.tankDriveVolts(0.38, -0.38);
    }
    */
    
    /*
   BLUE/RED ALLIANCE (2 Ball Shoot)
    */   
  
    /*
    if (time < 0.1){
      m_dropper.drop(0.08); //+ for down & - for up
    }
    else if (time < 2){
      m_drivetrain.tankDriveVolts(-0.38, -0.38);
      m_intake.intakeMotors(0.6);
    }
    else if (time < 6){
      m_drivetrain.tankDriveVolts(0, 0);
    }
    else if (time < 7.13){
      m_drivetrain.tankDriveVolts(0.38, -0.38);
    }
    else if (time < 9){  
      m_drivetrain.tankDriveVolts(0, 0);
    }
    else if (time < 12){
      //speed and how far the ball needs to go down
      m_shooter.shootShooter(0.8); 
    }
    else if (time < 13){
      m_intake.intakeMotors(0.6);
    }
    else if (time < 15){
      m_shooter.shootShooter(0);
      m_intake.intakeMotors(0);
    }
  */
   /*
    //Surrender (1 Ball Shoot and Taxi) Make sure shooter is facing hoop
    if (time < 2){
      m_shooter.shootShooter(1);
    }
    else if (time < 4){
      m_drivetrain.tankDriveVolts(0.5, 0.5);
    }
    else if (time < 7){
      m_shooter.shootShooter(0);
      m_drivetrain.tankDriveVolts(0, 0);
    }
  

    //Surrender (ONLY TAXI)
    /*
    if (time < 2){
      m_drivetrain.tankDriveVolts(-0.38, -0.38);
    }
    */

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop(); //stops the timer once autonomous ends
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
