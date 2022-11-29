package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp
public class TesteMotores extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override

    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double Esquerda = -gamepad1.left_stick_y; //
            double Direita = gamepad1.right_stick_y; //
            double lim = 0.5;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]

            telemetry.addData("Frente direita", motorFrontRight.getCurrentPosition());
            telemetry.addData("Frente esquerda", motorFrontLeft.getCurrentPosition());
            telemetry.addData("Trás direita", motorBackRight.getCurrentPosition());
            telemetry.addData("Trás esquerda", motorBackLeft.getCurrentPosition());
            telemetry.update();
            telemetry.update();
/*
            motorFrontLeft.setPower(Esquerda);
            motorBackLeft.setPower(Esquerda);
            motorFrontRight.setPower(Direita);
            motorBackRight.setPower(Direita);
*/
            //motorFrontLeft.setPower(0.5);
            //motorBackLeft.setPower(0.5);
            //motorFrontRight.setPower(0.5);
            motorBackRight.setPower(0.5);

            Thread.sleep(1000);

            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);

            motorBackRight.setTargetPosition(5000);
            motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Thread.sleep(1000);
        }
    }
}