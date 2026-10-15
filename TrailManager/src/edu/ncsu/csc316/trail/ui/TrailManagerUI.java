package edu.ncsu.csc316.trail.ui;

import java.util.Scanner;

import edu.ncsu.csc316.trail.manager.ReportManager;

/**
 * Simple command-line interface for displaying reports
 * 
 * @author Jason Stout
 */
public class TrailManagerUI {

	/**
	 * Prompts the user for files, then displays options for the user to enter
	 * 
	 * @param args arguments provided to interface
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean filesValid = false;
		ReportManager manager = null;

		while (!filesValid) {
			try {
				System.out.print("Enter landmarks file: ");
				String landmarkFile = scanner.nextLine();
				System.out.print("Enter trails file: ");
				String trailFile = scanner.nextLine();

				// Attempt to create ReportManager (will throw exception if files are invalid)
				manager = new ReportManager(landmarkFile, trailFile);
				filesValid = true;

			} catch (IllegalStateException e) {
				// Extract the original error message about which file was missing
				String errorMessage = e.getMessage();
				if (errorMessage.startsWith("Landmark file not found")) {
					System.out.println("Landmark file not found, please start over.");
				} else if (errorMessage.startsWith("Trail file not found")) {
					System.out.println("Trail file not found, please start over.");
				} else {
					System.out.println("Invalid files, please start over.");
				}
			}
		}

		// Rest of the program (menu options) only runs if files are valid
		System.out.println("\nOptions:");
		System.out.println("1. Get distances from landmark");
		System.out.println("2. List first aid locations");
		System.out.println("3. Quit program");
		System.out.print("Select option: ");
		int choice = Integer.parseInt(scanner.nextLine());

		if (choice == 1) {
			System.out.print("Enter origin landmark ID: ");
			String origin = scanner.nextLine();
			System.out.println(manager.getDistancesReport(origin));
		} else if (choice == 2) {
			System.out.print("Enter minimum intersecting trails: ");
			int min = Integer.parseInt(scanner.nextLine());
			System.out.println(manager.getProposedFirstAidLocations(min));
		} else if (choice == 3) {
			System.out.println("Exiting program");
		}
		scanner.close();
	}

}
