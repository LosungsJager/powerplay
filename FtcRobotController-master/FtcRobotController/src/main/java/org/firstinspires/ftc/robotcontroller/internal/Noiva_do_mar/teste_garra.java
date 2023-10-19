package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class teste_garra {
    
    @TeleOp
    public class TesteMotores extends LinearOpMode {
        private ElapsedTime runtime = new ElapsedTime();

        @Override

        public void runOpMode() throws InterruptedException {

            Servo ServoGarra = hardwareMap.servo.get("servogarra");
            DcMotor Braço = hardwareMap.dcMotor.get("base_garra");

            waitForStart();

            if (isStopRequested()) return;

            while (opModeIsActive()) {
                Braço.setPower(gamepad1.right_trigger);
                Braço.setPower(-gamepad1.left_trigger);

            }

        }
    }
}
