package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Forward-Back")
public class Auto extends Hardware {
    // Fake values for now. Just showing how to call it
    //TensorflowDetector recognizer = new TensorflowDetector(10,20, 1);
    public int delaySeconds = 0;

    @Override
    public void runOpMode(){
        hardwareSetup();
        selectParameters();
        waitForStart();

        //Prep for tensorflow:
        //int place = recognizer.recognizeObjects();
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
        boolean selection = true;
        while(selection) {
            while (!gamepad1.a) {
                telemetry.addLine("Select " + currentParameter);

                switch (currentParameter) {
                    case "Delay": {
                        if (gamepad1.dpad_up) {
                            delaySeconds++;
                        }
                        if (gamepad1.dpad_down) {
                            delaySeconds--;
                        }
                        delaySeconds = Range.clip(delaySeconds, 0, 30);
                        telemetry.addLine("delaySeconds = " + delaySeconds);
                        if (gamepad1.x) {
                            currentParameter = "Delay";
                        }
                        break;
                    }
                }
                //Output to telemetry and sleep
                telemetry.update();
                sleep(200);
            }
        }
        if (gamepad1.a){
            selection = false;
        }
    }

}
