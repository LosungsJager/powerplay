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
package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous
public class rosas extends LinearOpMode {
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

        /*ACELERAR!!
        motorFrontLeft.setPower(0.87 * lim);// anda para frente
        motorBackLeft.setPower(-0.87 * lim);
        motorFrontRight.setPower(-1.2 * lim);
        motorBackRight.setPower(-1.2 * lim);
        Thread.sleep(1200);

        motorFrontLeft.setPower(-0.97 * lim);// anda para tras
        motorBackLeft.setPower(0.97 * lim);
        motorFrontRight.setPower(1.2 * lim);
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(950);

        motorFrontLeft.setPower(0.97 * lim);// giro de 45°
        motorBackLeft.setPower(-0.97 * lim);
        motorFrontRight.setPower(1.2 * lim);
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(1000);

        motorFrontLeft.setPower(-0.97 * lim);// andar para tras/;
        motorBackLeft.setPower(0.97 * lim);
        motorFrontRight.setPower(1.2 * lim);
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(1150);

        garraB.setPosition(0.4); //posição da mão da garra
        garraA.setPosition(0.35); //abre a mão da garra
       */ //Thread.sleep(250);

        motorFrontLeft.setPower(0.97 * lim);// anda pra direita???
        motorBackLeft.setPower(-0.97 * lim);
        motorFrontRight.setPower(-1.2 * lim);
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(1000);

        motorFrontLeft.setPower(-0.97 * lim);// anda pra esquerda???
        motorBackLeft.setPower(0.97 * lim);
        motorFrontRight.setPower(1.2 * lim);
        motorBackRight.setPower(-1.2 * lim);
        Thread.sleep(1000);

        motorFrontLeft.setPower(0.97 * lim);// anda pra diagonal direita???
        motorBackRight.setPower(1.2 * lim);
        Thread.sleep(1000);

        motorFrontRight.setPower(0.97 * lim);// anda pra diagonal esquerda???
        motorBackLeft.setPower(1.2 * lim);
        Thread.sleep(1000);
   }
}