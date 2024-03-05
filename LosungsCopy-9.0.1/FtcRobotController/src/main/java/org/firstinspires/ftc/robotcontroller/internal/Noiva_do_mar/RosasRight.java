package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
@Autonomous
public class RosasRight extends LinearOpMode {

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
        double lim = 0.5;
        //double servoposicao = 0.2;
        garraB.setPosition(0.9);
        Thread.sleep(3000);

        motorFrontLeft.setPower(0.99 * lim);// anda para frente
        motorBackLeft.setPower(-0.99 * lim);
        motorFrontRight.setPower(-1.2 * lim);
        motorBackRight.setPower(-1.2 * lim);
        Thread.sleep(2050);

        motorFrontLeft.setPower(-0.97 * lim);// anda para tras
        motorBackLeft.setPower(0.97 * lim);
        motorFrontRight.setPower(1.2 * lim);
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(950);
    }
}