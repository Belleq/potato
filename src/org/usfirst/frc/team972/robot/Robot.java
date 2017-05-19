package org.usfirst.frc.team972.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.*;

public class Robot extends IterativeRobot {
	// drive
	CANTalon leftMotor = new CANTalon(1);
	CANTalon rightMotor = new CANTalon(2);
	
	// hooks
	CANTalon hookMotor = new CANTalon(3);

	DigitalInput unlockLimit = new DigitalInput(0);
	DigitalInput lockLimit = new DigitalInput(1);
	
	boolean locked = false;
	boolean lastLocked = false;
	
	// joysticks
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	
	// reverse drive + squared inputs
	boolean reversed = false;
	boolean squared = false;
	
	boolean lastSquared = false;
	
	double comp(double x){
		// so you don't have to retype it for both
		x = ((Math.min(0.0,(Math.abs(x) - 0.05)) * x) / Math.abs(x)) / 0.95; // 5% buffer
		
		if (reversed){
			x *= -1;
		}
		
		if (squared){
			x *= Math.abs(x);
		}
		
		return x;
	}
	
	public void robotInit() {
		
	}

	
	public void autonomousInit() {
		// go straight
		leftMotor.set(1);
		rightMotor.set(1);
		
		if (Constants.USING_ENCODER){
			while(isAutonomous() && isEnabled() && leftMotor.get() < 5000){
				
			}
		} else {
			long current = System.currentTimeMillis();
			while(isAutonomous() && isEnabled() && (current + 5000) > System.currentTimeMillis()){
				
			}
		}
		
		// turn left
		leftMotor.set(-1);
		rightMotor.set(1);
		
		if (Constants.USING_ENCODER){
			while(isAutonomous() && isEnabled() && leftMotor.get() < 5000){
				
			}
		} else {
			long current = System.currentTimeMillis();
			while(isAutonomous() && isEnabled() && (current + 5000) > System.currentTimeMillis()){
				
			}
		}
		
		// go straight
		leftMotor.set(1);
		rightMotor.set(1);
		
		if (Constants.USING_ENCODER){
			while(isAutonomous() && isEnabled() && leftMotor.get() < 5000){
				
			}
		} else {
			long current = System.currentTimeMillis();
			while(isAutonomous() && isEnabled() && (current + 5000) > System.currentTimeMillis()){
				
			}
		}
		
		// turn around
		leftMotor.set(-1);
		rightMotor.set(1);
		
		if (Constants.USING_ENCODER){
			while(isAutonomous() && isEnabled() && leftMotor.get() < 5000){
				
			}
		} else {
			long current = System.currentTimeMillis();
			while(isAutonomous() && isEnabled() && (current + 5000) > System.currentTimeMillis()){
				
			}
		}
		
		
		
		// pick up left basket
		hookMotor.set(0.25);
		while(!lockLimit.get()){
			
		}
		hookMotor.set(0);
		
	}
	
	public void autonomousPeriodic() {
		
	}

	public void teleopPeriodic() {
		reversed = leftJoy.getRawButton(Constants.REVERSE_BUTTON_LEFTJOY);
		boolean currSqu = leftJoy.getRawButton(Constants.SQUARE_TOGGLE_BUTTON_LEFTJOY);
		
		if (currSqu && !lastSquared){
			squared = !squared;
		}
		lastSquared = currSqu;
		
		leftMotor.set(comp(leftJoy.getY()));
		rightMotor.set(comp(rightJoy.getY()));
		
		boolean currLocked = leftJoy.getRawButton(Constants.CLAW_TOGGLE_BUTTON_LEFTJOY);
		
		if (currLocked && !lastLocked){
			locked = !locked;
		}
		lastLocked = currLocked;
		
		if (locked){
			if (lockLimit.get()){
				hookMotor.set(0.25);
			} else {
				hookMotor.set(0);
			}
		} else {
			if (unlockLimit.get()){
				hookMotor.set(-0.25);
			} else {
				hookMotor.set(0);
			}
		}
		
	}

	public void testPeriodic() {
		
	}
}

