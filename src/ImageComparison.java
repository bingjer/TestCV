// This file ImageComparison.java holds the logic for comparing two screenshots. The logic can be credited to https://docs.opencv.org/4.x/d8/dc8/tutorial_histogram_comparison.html.

import java.util.List;
import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageComparison {

	public double[] compareImages(String expected_image, String actual_img) {
		// Creates a matrix from the images.
		Mat expected = Imgcodecs.imread(expected_image);
		Mat actual = Imgcodecs.imread(actual_img);

		// Return value to check if images are empty.
		int ret = 0;
		if (expected.empty() || actual.empty()) {
			ret = -1;
		}

		if (ret != -1) {
			Mat hsv_expected = new Mat();
			Mat hsv_actual = new Mat();

			// Converts the images to HSV format.
			Imgproc.cvtColor(expected, hsv_expected, Imgproc.COLOR_BGR2HSV);
			Imgproc.cvtColor(actual, hsv_actual, Imgproc.COLOR_BGR2HSV);

			// Creates the histogram h and s bins.
			int hBins = 50, sBins = 60;
			int[] histSize = { hBins, sBins };
			float[] ranges = { 0, 180, 0, 256 };
			int[] channels = { 0, 1 };

			Mat hist_expected = new Mat(), hist_actual = new Mat();

			// Creates the histograms.
			List<Mat> hsv_expected_list = Arrays.asList(hsv_expected);
			Imgproc.calcHist(hsv_expected_list, new MatOfInt(channels), new Mat(), hist_expected,
					new MatOfInt(histSize), new MatOfFloat(ranges), false);
			Core.normalize(hist_expected, hist_expected, 0, 1, Core.NORM_MINMAX);

			List<Mat> hsv_actual_list = Arrays.asList(hsv_actual);
			Imgproc.calcHist(hsv_actual_list, new MatOfInt(channels), new Mat(), hist_actual, new MatOfInt(histSize),
					new MatOfFloat(ranges), false);
			Core.normalize(hist_actual, hist_actual, 0, 1, Core.NORM_MINMAX);

			// Compares the histograms and outputs 4 types of computational data.
			double correlation = Imgproc.compareHist(hist_expected, hist_actual, 0);
			double chi_square = Imgproc.compareHist(hist_expected, hist_actual, 1);
			double intersection = Imgproc.compareHist(hist_expected, hist_actual, 2);
			double Bhattacharyya = Imgproc.compareHist(hist_expected, hist_actual, 3);

			double[] data = { correlation, chi_square, intersection, Bhattacharyya };
			return data;
		}
		else {
			return null;
		}
	}

}
