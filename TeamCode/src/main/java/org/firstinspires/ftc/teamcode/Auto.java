package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Forward-Back")
public class Auto extends Hardware {
    // Fake values for now. Just showing how to call it
    TensorflowDetector recognizer = new TensorflowDetector(10,20);
    public boolean isBlueAlliance = true;
    public int delaySeconds = 0; //seconds to wait for alliance
    public String startingPosition = "Carousel";

    @Override
    public void runOpMode(){
        //Mirror driving wheels and flip carousel direction only if we are the red alliance
        hardwareSetup(!isBlueAlliance, isBlueAlliance);
        selectParameters();
        telemetry.addData("Status","Waiting for Start");
        //Display all parameter values (so far delaySeconds and startingPosition)
        telemetry.addData("delaySeconds: ", delaySeconds);
        telemetry.addData("startingPosition: ", startingPosition);
        telemetry.update();

        waitForStart();
        telemetry.update();

        //Tensorflow recognize objects
        int place = recognizer.recognizeObjects();
        // Then move to the place.
        //Drive forward
        telemetry.addData("Status", "Driving Forward");
        telemetry.update();
        driveForward(0.5);
        sleep(1000);

        driveForward(0);
        sleep(200);

        telemetry.addData("Status","Driving Backward");
        telemetry.update();
        driveForward(-0.5);
        sleep(1000);
        driveForward(0);
    }

    //Controls: Directional pad to select, x to change mode, a to enter
    public void selectParameters() {
        String currentParameter = "Delay";
        while (!gamepad1.a) { //pressing 'a' will end the selection
            telemetry.addLine("Select " + currentParameter);

            switch (currentParameter) {
                case "Delay":
                    if (gamepad1.dpad_up) {
                        delaySeconds++;
                    } else if (gamepad1.dpad_down) {
                        delaySeconds--;
                    }
                    delaySeconds = Range.clip(delaySeconds, 0, 30);
                    telemetry.addLine("delaySeconds = " + delaySeconds);

                    if (gamepad1.x) { // pressing 'x' sends selector to the next variable
                        currentParameter = "Starting Position";
                    }
                break;

                case "Starting Position":
                    //Choose between "Carousel" and "Warehouse"
                    if (gamepad1.dpad_up) {
                        startingPosition = "Carousel";
                    } else if (gamepad1.dpad_down) {
                        startingPosition = "Warehouse";
                    }
                    telemetry.addLine("startingPosition = " + startingPosition);

                    if(gamepad1.x) {
                        currentParameter = "Delay";
                    }
                break;
            }

            //Output to telemetry and sleep
            telemetry.update();
            sleep(200);
        }
    }

}
