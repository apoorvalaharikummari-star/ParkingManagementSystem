import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total parking slots: ");
        int total = sc.nextInt();

        ParkingLot lot = new ParkingLot(total);

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   SMART PARKING SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Exit Vehicle");
            System.out.println("3. Show Status");
            System.out.println("4. Generate QR Code");
            System.out.println("5. Exit");
            System.out.println("==============================");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter vehicle number: ");
                    String v = sc.next();
                    lot.parkVehicle(v);
                    break;

                case 2:
                    System.out.print("Enter vehicle number: ");
                    String ev = sc.next();
                    lot.exitVehicle(ev);
                    break;

                case 3:
                    lot.showStatus();
                    break;

                case 4:
                    lot.generateQR();
                    break;

                case 5:
                    System.out.println("System Exit...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}