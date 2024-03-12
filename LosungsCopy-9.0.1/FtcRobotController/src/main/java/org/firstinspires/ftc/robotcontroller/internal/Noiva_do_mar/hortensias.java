package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

//E0 esq_t
//E1 dir_f
//E2 trena
//E3 lancador
//Servo 0 garraA
//Servo 1 garraB

//C0 dir_t
//C1 esq_f
//C2 base_garra
//Cdigital1 touch

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class hortensias extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor baseGarra = hardwareMap.dcMotor.get("base");
        DcMotor trena = hardwareMap.dcMotor.get("trena");
        DcMotor lancador_aviao = hardwareMap.dcMotor.get("lancador");

        Servo garraA = hardwareMap.servo.get("garraA");
        Servo garraB = hardwareMap.servo.get("garraB");

        TouchSensor toque = hardwareMap.touchSensor.get("touch");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        lancador_aviao.setDirection(DcMotorSimple.Direction.REVERSE);
        baseGarra.setDirection(DcMotorSimple.Direction.REVERSE);

        double lim = 0.6;
        double servoposicao = 0.2;
        garraB.setPosition(1.1);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            if (gamepad1.dpad_up) {
                lim = lim + 0.1;
                Thread.sleep(1000);
            }
            if (gamepad1.dpad_down) {
                lim = lim - 0.1;
                Thread.sleep(1000);
            }

            // motor de movimento
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = -((y + x + rx) / denominator) * lim;
            telemetry.addData("frente esquerda:", frontLeftPower);
            double backLeftPower = ((y - x + rx) / denominator) * lim;
            telemetry.addData("trás esquerda:", backLeftPower);
            double frontRightPower = ((y - x - rx) / denominator) * lim * 0.91;
            telemetry.addData("frente direita:", frontRightPower);
            double backRightPower = ((y + x - rx) / denominator) * lim;
            telemetry.addData("trás direita:", backRightPower);

            motorFrontLeft.setPower(frontLeftPower * 1.26);
            motorBackLeft.setPower(backLeftPower * 1.26);
            motorFrontRight.setPower(frontRightPower * 1.50);
            motorBackRight.setPower(backRightPower * 1.50);
            
            telemetry.addData("velocidade:", lim);
            telemetry.addData("toque", toque.isPressed());
            telemetry.update();

            lancador_aviao.setPower(gamepad1.right_trigger);

            motorFrontLeft.setPower(frontLeftPower * 1.26);
            motorBackLeft.setPower(backLeftPower * 1.26);
            motorFrontRight.setPower(frontRightPower * 1.50);
            motorBackRight.setPower(backRightPower * 1.50);

            if (gamepad2.right_trigger == 0) {
                baseGarra.setPower(0.5 * gamepad2.left_trigger);
            }
            if (gamepad2.left_trigger == 0) {
                baseGarra.setPower(-0.5 * gamepad2.right_trigger);
            }

            if (gamepad1.left_trigger == 1) {
                trena.setPower(9.7);
            }
            if (gamepad1.left_trigger != 1) {
                trena.setPower(0);
            }
            if (gamepad1.left_bumper) {
                trena.setPower(-9.8);
            }
            //*if(gamepad2.left_trigger){
            //   garraA.setPosition(0  .5);
            //}

            if (gamepad2.y) {
                garraB.setPosition(0.8);
                telemetry.addData("posição servo: ", garraB.getPosition());
                telemetry.update();
                sleep(500);
                //garra em posição de largar pixal
            }
            if (gamepad2.x) {
                garraB.setPosition(0.4);
                telemetry.addData("posição servo: ", garraB.getPosition());
                telemetry.update();
                sleep(500);
                //garra em posição de pegar
            }


            if (gamepad2.a) {
                garraA.setPosition(0.35);
            }

            if (gamepad2.b) {
                garraA.setPosition(1);
            }
        }
    }
}


