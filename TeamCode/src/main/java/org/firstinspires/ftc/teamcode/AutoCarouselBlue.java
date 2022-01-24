package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;


// Just a start with the camera.


public class AutoCarouselBlue extends Hardware{
    OpenCvInternalCamera phoneCam;
    OpenCvWebcam webcam;
    OpenCvDetector pipeline = new OpenCvDetector();
    public OpenCvDetector.ElementLocation elementLocation = OpenCvDetector.ElementLocation.ERROR;
    double angleToTurnTo, angleThatis = 0;
    int diff;
    ParkingPosition parkingPosition = ParkingPosition.WAREHOUSE_TOWARDS_SHARED_HUB;

    @Override
    public void runOpMode(){

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.setPipeline(pipeline);
        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
                elementLocation = OpenCvDetector.ElementLocation.ERROR;
            }
        });

        hardwareSetup(false,true);
        imuSetup();
        while (!isStarted() && !isStopRequested())
        {
            elementLocation = pipeline.getLocation();
            telemetry.addData("Realtime analysis", elementLocation);
            telemetry.addData("Frame Count", webcam.getFrameCount());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(100);
        }
        // When we hit this point start has been pressed. First thing to do is save where we have the
        // marker as being

        elementLocation = pipeline.getLocation();
        webcam.stopStreaming();
        telemetry.addData("Final:", elementLocation);
        telemetry.update();
    }
}
