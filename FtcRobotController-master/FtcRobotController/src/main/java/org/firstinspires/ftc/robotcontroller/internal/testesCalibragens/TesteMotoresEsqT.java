package org.firstinspires.ftc.robotcontroller.internal.testesCalibragens;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp
public class TesteMotoresEsqT extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double lim = 0.6;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;


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

  /*
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = -((y + x + rx) / denominator)*lim;
            telemetry.addData("frente esquerda:", frontLeftPower);
            double backLeftPower = ((y - x + rx) / denominator)*lim;
            telemetry.addData("trás esquerda:", backLeftPower);
            double frontRightPower = ((y - x - rx) / denominator)*lim;
            telemetry.addData("frente direita:", frontRightPower);
            double backRightPower = ((y + x - rx) / denominator)*lim;
            telemetry.addData("trás direita:", backRightPower);

            telemetry.addData("velocidade:", lim);
            telemetry.update();

            //motorFrontLeft.setPower(frontLeftPower * 0.78);
            //motorBackLeft.setPower(backLeftPower * 0.78);
            motorFrontLeft.setPower(frontLeftPower * 0.91);
            motorBackLeft.setPower(backLeftPower * 0.91);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
*/
            motorBackLeft.setPower(1);
        }
    }
}
