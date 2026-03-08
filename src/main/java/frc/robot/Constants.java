package frc.robot;

import com.ctre.phoenix6.controls.DutyCycleOut;

public class Constants {
    public class CANConstants {
        public final static int FRONT_LEFT_MOTOR_ID = 0; 
        public final static int BACK_LEFT_MOTOR_ID = 1; 
        public final static int FRONT_RIGHT_MOTOR_ID = 2; 
        public final static int BACK_RIGHT_MOTOR_ID = 3; 
        public final static int SHOOTER_MOTOR_ID = 4; 
        public final static int INDEXER_MOTOR_ID = 5; 
    }
    
    public class ShooterConstants {
        public final static DutyCycleOut ACTIVE_SHOOT_DUTY_CYCLE = new DutyCycleOut(0.5); 
        public final static DutyCycleOut INDEXER_INTAKE_DUTY_CYCLE = new DutyCycleOut(0.5); 
        public final static DutyCycleOut INDEXER_SHOOT_DUTY_CYCLE = new DutyCycleOut(-0.5); 
        public final static DutyCycleOut INACTIVE_DUTY_CYCLE = new DutyCycleOut(0); 
    }
}
