/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {
	private String gameData; //Text used for determining which switch has been assigned
	
	private DifferentialDrive m_robotDrive
		= new DifferentialDrive(new Spark(0), new Spark(1)); //Creates Drive Train Instance
	
	private Joystick m_stick = new Joystick(0); //Create Joystick Instance
	
	//The following specifies the channels for the motor and potentiometer
	@SuppressWarnings("null")
	private static final int kPotChannel = (Integer) null;
	@SuppressWarnings("null")
	private static final int kMotorChannel = (Integer) null;
	
	//The following states the PID constants
	private static final double kP = -5.0;
	private static final double kI = -0.02;
	private static final double kD = -2.0;
	
	//The following specifies the variables for the elevator
	private PIDController m_pidController; //Creates Variable for PID
	private AnalogInput m_potentiometer; //Creates Variable for Potentiometer
	private SpeedController m_elevatorMotor; //Creates Variable for Elevator Motor
	
	@Override //Run when robot starts up
	public void robotInit() {
		setPeriod(0.01); //Sets code to run 100 times per second
		
		//The following instantiates the elevator motor, potentiometer,
		//and PID Controller
		m_potentiometer = new AnalogInput(kPotChannel);
		m_elevatorMotor = new Spark(kMotorChannel);
		m_pidController
		= new PIDController(kP, kI, kD, m_potentiometer, m_elevatorMotor);
		m_pidController.setInputRange(0, 5);
	}

	@Override //Run when autonomous mode is enabled
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override //Run periodically when autonomous mode is enabled
	public void autonomousPeriodic() {
		if (gameData.charAt(0) == 'L') {
			//Put left SWITCH auto code here
		} else if (gameData.charAt(0) == 'R') {
			//Put right SWITCH auto code here
		} else if (gameData.charAt(1) == 'L') {
			//Put right SCALE code here
		} else if (gameData.charAt(1) == 'R') {
			//Put right SCALE code here
		} else if (gameData.charAt(2) == 'L') {
			//Put right ENEMY SWITCH code here
		} else if (gameData.charAt(2) == 'R') {
			//Put right ENEMY SWITCH code here
		}
	}
	
	public void telopInit() {
		m_pidController.enable();
	}
	
	@Override //Run periodically when teleop mode is enabled
	public void teleopPeriodic() {
		m_robotDrive.arcadeDrive(m_stick.getRawAxis(1), m_stick.getRawAxis(4)); //Arcade Drive using 2 Joysticks from Xbox Controller
		//Here goes the code for the Elevator
		//m_pidController.setSetpoint(kSetPoints[0-2]);
	}
	
	@Override //Run periodically when test mode is enabled
	public void testPeriodic() {
	}
}
