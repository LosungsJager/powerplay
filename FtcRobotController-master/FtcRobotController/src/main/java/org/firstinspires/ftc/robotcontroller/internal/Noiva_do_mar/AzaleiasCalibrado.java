package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class AzaleiasCalibrado extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override

    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor baseGarra = hardwareMap.dcMotor.get("base_garra");
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double lim = 0.5;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;


            if(gamepad1.dpad_up) {
                lim = lim + 0.1;
                Thread.sleep(1000);
            }
            if(gamepad1.dpad_down){
                lim = lim - 0.1;
                Thread.sleep(1000);
            }
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (((y + x + rx) / denominator)-0.1)*lim;
            telemetry.addData("frente esquerda:", frontLeftPower);
            double backLeftPower = (((y - x + rx) / denominator)-0.1)*lim;
            telemetry.addData("trás esquerda:", backLeftPower);
            double frontRightPower = (((y - x - rx) / denominator)-0)*lim;
            telemetry.addData("frente direita:", frontRightPower);
            double backRightPower = (((y + x - rx) / denominator)-0)*lim;
            telemetry.addData("trás direita:", backRightPower);

            telemetry.addData("velocidade:", lim);
            telemetry.update();

            baseGarra.setPower(gamepad1.right_trigger);
            baseGarra.setPower(-gamepad1.left_trigger);

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}