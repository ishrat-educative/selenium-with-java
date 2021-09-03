package com.educative.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class SummaryReport implements IReporter {

	private DecimalFormat decimalFormat = new DecimalFormat("00.##");

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		File file = new File(outputDirectory, "summary.html");
		try (Writer writer = new FileWriter(file)) {
			writer.write("<html><body>");
			String title = "<table border='1' style='background-color:yellow;border:1px dotted black;width:80%;border-collapse:collapse;'><caption style='margin: 1em; font-weight: bolder'>Test Execution Summary Report</caption><tbody>";
			writer.write(title);
			writer.write(
					"<tr><th style='padding:3px;'align='center'>Suite Name</th><th style='padding:3px;'align='center'>Total</th><th style='padding:3px;'align='center'>Passed</th><th style='padding:3px;'align='center'>Failed</th><th style='padding:3px;'align='center'>Skipped</th><th style='padding:3px;'align='center'>% Tests Passed</th><th style='padding:3px;'align='center'>Total Execution Time (Sec)</th></tr>");

			for (ISuite suite : suites) {
				suite.getResults().forEach((k, v) -> {

					ITestContext context = v.getTestContext();
					Integer passed = context.getPassedTests().size();
					Integer failed = context.getFailedTests().size();
					Integer skipped = context.getSkippedTests().size();
					Integer total = passed + failed + skipped;
					Double execTime = (context.getEndDate().getTime() - context.getStartDate().getTime()) * 0.001;

					if (total > 0) {
						try {
							String percent = decimalFormat
									.format((passed.doubleValue() / total.doubleValue()) * 100.0D);
							writer.write(String.format(
									"<tr><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td><td style='text-align: center'>%s</td></tr>",
									suite.getName(), total, passed, failed, skipped, percent,
									decimalFormat.format(execTime)));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
			writer.write("</tbody></table></body></html>");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("writing summary report to " + file.getAbsolutePath());
	}
}