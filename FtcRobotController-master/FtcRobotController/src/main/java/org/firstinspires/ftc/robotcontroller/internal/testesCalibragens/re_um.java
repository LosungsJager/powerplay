package org.firstinspires.ftc.robotcontroller.internal.testesCalibragens;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class re_um extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        //usar CHARLIE como configuração
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor baseGarra = hardwareMap.dcMotor.get("base_garra");
        DcMotor levantarGarra = hardwareMap.dcMotor.get("levantarGarra");
        Servo servogarra = hardwareMap.servo.get("servogarra");
        Servo servotrava = hardwareMap.servo.get("servotrava");
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests


        waitForStart();
        double x = 0;
        double y = 1;
        double xquadrado = 0;
        double contador = 0;
        for (int i = 0; i<20; i++){
        /*    if (x == 1 && contador <5){
                contador ++;
            }else{
                x = x + 0.1;
            }
            xquadrado = x*x;
            y = (-0.5* xquadrado + x) * 2;
        */
            motorFrontLeft.setPower(-0.40 * y);
            motorBackLeft.setPower(0.40 * y);
            motorFrontRight.setPower(0.47 * -y);
            motorBackRight.setPower(0.47 * -y);
            //tempo total é 25 este delay
            Thread.sleep(50);
            //tempo total é 25 vezes este delay
            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);

        }



        if (isStopRequested()) return;

    }
}