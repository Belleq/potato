package org.usfirst.frc.team972.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.*;

public class Robot extends IterativeRobot {
	CANTalon leftMotor = new CANTalon(1);
	CANTalon rightMotor = new CANTalon(2);
	
	DoubleSolenoid hookPiston = new DoubleSolenoid(0,1);
	
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	
	// reverse drive + squared inputs
	boolean reversed = false;
	boolean squared = false;
	boolean pistonOut = false;
	
	boolean lastHoo = false;
	boolean lastSqu = false;
	
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
		
		// pick up left basket
		
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
		
		// pick up right basket
	}
	
	public void autonomousPeriodic() {
		
	}

	public void teleopPeriodic() {
		reversed = leftJoy.getRawButton(Constants.REVERSE_BUTTON_LEFTJOY);
		boolean currSqu = leftJoy.getRawButton(Constants.SQUARE_TOGGLE_BUTTON_LEFTJOY);
		
		if (currSqu && !lastSqu){
			squared = !squared;
		}
		lastSqu = currSqu;
		
		leftMotor.set(comp(leftJoy.getY()));
		rightMotor.set(comp(rightJoy.getY()));
		
		boolean currHoo = leftJoy.getRawButton(Constants.CLAW_TOGGLE_BUTTON_LEFTJOY);
		
		if (currHoo && !lastHoo){
			pistonOut = !pistonOut;
		}
		lastHoo = currHoo;
		
		if (pistonOut){
			hookPiston.set(DoubleSolenoid.Value.kForward);
		} else {
			hookPiston.set(DoubleSolenoid.Value.kReverse);
		}
		
	}

	public void testPeriodic() {
		
	}
}

