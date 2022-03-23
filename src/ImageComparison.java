import java.util.List;
import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageComparison {
	
	private String expected_img;
	private String actual_img;
	
	public ImageComparison(String expected_img, String actual_img) {
		this.expected_img = expected_img;
		this.actual_img = actual_img;
	}
	
	
	public double[] compareImages(String expected_image, String actual_img) {
		Mat expected = Imgcodecs.imread(expected_image);
		Mat actual = Imgcodecs.imread(actual_img);
		if (expected.empty() || actual.empty()) {
			//TODO: create notification dialogue window
		}
		
		Mat hsv_expected = new Mat();
		Mat hsv_actual = new Mat();
		
		Imgproc.cvtColor(expected, hsv_expected, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(actual, hsv_actual, Imgproc.COLOR_BGR2HSV);
        
        int hBins = 50, sBins = 60;
        int[] histSize = { hBins, sBins };
        // hue varies from 0 to 179, saturation from 0 to 255
        float[] ranges = { 0, 180, 0, 256 };
        // Use the 0-th and 1-st channels
        int[] channels = { 0, 1 };
        
        Mat hist_expected = new Mat(), hist_actual = new Mat();
        
        List<Mat> hsv_expected_list = Arrays.asList(hsv_expected);
        Imgproc.calcHist(hsv_expected_list, new MatOfInt(channels), new Mat(), hist_expected, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(hist_expected, hist_expected, 0, 1, Core.NORM_MINMAX);
        
        List<Mat> hsv_actual_list = Arrays.asList(hsv_actual);
        Imgproc.calcHist(hsv_actual_list, new MatOfInt(channels), new Mat(), hist_actual, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(hist_actual, hist_actual, 0, 1, Core.NORM_MINMAX);
        
        double correlation = Imgproc.compareHist(hist_expected, hist_actual, 0);
        double chi_square = Imgproc.compareHist(hist_expected, hist_actual, 1);
        double intersection = Imgproc.compareHist(hist_expected, hist_actual, 2);
        double Bhattacharyya = Imgproc.compareHist(hist_expected, hist_actual, 3);

        System.out.println(correlation);
        System.out.println(chi_square);
        System.out.println(intersection);
        System.out.println(Bhattacharyya);
        
        double[] data = {correlation, chi_square, intersection, Bhattacharyya};
        return data;
		
	}

}
