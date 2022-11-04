package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Disabled
@TeleOp
public class CalibragemAlpha extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override

    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        double lim = 0.5;

        double potBR = 1.0;
        double potFR = 1.0;
        double potBl = 1.0;
        double potFL = 1.0;

        double faixaA = 0;
        double faixaB = 0;
        double faixaC = 0;


        motorFrontLeft.setTargetPosition(5000);
        motorBackLeft.setTargetPosition(5000);
        motorFrontRight.setTargetPosition(5000);
        motorBackRight.setTargetPosition(5000);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            motorFrontLeft.setPower(potFL);
            motorBackLeft.setPower(potBl);
            motorFrontRight.setPower(potFR);
            motorBackRight.setPower(potBR);

            Thread.sleep(2000);

            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);

            telemetry.addData("Frente direita", motorFrontRight.getCurrentPosition());
            telemetry.addData("Frente esquerda", motorFrontLeft.getCurrentPosition());
            telemetry.addData("Trás direita", motorBackRight.getCurrentPosition());
            telemetry.addData("Trás esquerda", motorBackLeft.getCurrentPosition());

            telemetry.update();
            //CÁLCULO FAIXAS

            if (motorFrontRight.getCurrentPosition() != 0 && motorFrontLeft.getCurrentPosition() != 0
            && motorBackRight.getCurrentPosition() != 0 && motorBackLeft.getCurrentPosition() != 0){
                faixaA = motorFrontRight.getCurrentPosition() / motorFrontLeft.getCurrentPosition();
                faixaB = motorFrontLeft.getCurrentPosition() / motorBackRight.getCurrentPosition();
                faixaC = motorBackRight.getCurrentPosition() / motorBackLeft.getCurrentPosition();
            }


            //FIM CÁCLULO FAIXAS
            Thread.sleep(2000);

            while (faixaA < 0.8 || faixaA > 1.2 ||
                    faixaB < 0.8 || faixaB > 1.2 ||
                    faixaC < 0.8 || faixaC > 1.2){

                int media = (motorBackLeft.getCurrentPosition() + motorBackRight.getCurrentPosition() +
                motorFrontLeft.getCurrentPosition() + motorFrontRight.getCurrentPosition()) / 4;
            //ESQUERDA TRAS
                if (motorBackLeft.getCurrentPosition() > media){
                    potBl = potBl - 0.1;
                }else if (motorBackLeft.getCurrentPosition() < media){
                    potBl = potBl + 0.1;
                }
            //ESQUERDA FRENTE
                if (motorFrontLeft.getCurrentPosition() > media){
                    potFL = potFL - 0.1;
                }else if (motorFrontLeft.getCurrentPosition() < media){
                    potFL = potFL + 0.1;
                }
            //DIREITA TRAS
                if (motorBackRight.getCurrentPosition() > media){
                    potBR = potBl - 0.1;
                }else if (motorBackRight.getCurrentPosition() < media){
                    potBR = potBl + 0.1;
                }
            //DIREITA FRENTE
                if (motorFrontRight.getCurrentPosition() > media){
                    potFR = potBl - 0.1;
                }else if (motorFrontRight.getCurrentPosition() < media){
                    potFR = potBl + 0.1;
                }
 //LEITURA DOS ENCODERS
                telemetry.clearAll();

                motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                motorFrontLeft.setPower(potFL);
                motorBackLeft.setPower(potBl);
                motorFrontRight.setPower(potFR);
                motorBackRight.setPower(potBR);

                Thread.sleep(1000);

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);

                Thread.sleep(2000);

                if (motorFrontRight.getCurrentPosition() != 0 && motorFrontLeft.getCurrentPosition() != 0
                        && motorBackRight.getCurrentPosition() != 0 && motorBackLeft.getCurrentPosition() != 0){
                    faixaA = motorFrontRight.getCurrentPosition() / motorFrontLeft.getCurrentPosition();
                    faixaB = motorFrontLeft.getCurrentPosition() / motorBackRight.getCurrentPosition();
                    faixaC = motorBackRight.getCurrentPosition() / motorBackLeft.getCurrentPosition();
                }

                telemetry.addData("Frente direita", motorFrontRight.getCurrentPosition());
                telemetry.addData("Frente esquerda", motorFrontLeft.getCurrentPosition());
                telemetry.addData("Trás direita", motorBackRight.getCurrentPosition());
                telemetry.addData("Trás esquerda", motorBackLeft.getCurrentPosition());
                telemetry.update();
            }
            telemetry.clearAll();
            telemetry.addData("Frente direita", potFR);
            telemetry.addData("Frente esquerda", potFL);
            telemetry.addData("Trás direita", potBR);
            telemetry.addData("Trás esquerda", potBl);
            telemetry.update();
        }
    }
}