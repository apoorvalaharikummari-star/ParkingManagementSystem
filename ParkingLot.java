import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class ParkingLot {

    private int totalSlots;
    private Map<Integer, String> slots = new HashMap<>();
    private Map<String, Integer> vehicleSlot = new HashMap<>();
    private Map<String, Long> entryTime = new HashMap<>();

    public ParkingLot(int totalSlots) {
        this.totalSlots = totalSlots;

        for (int i = 1; i <= totalSlots; i++) {
            slots.put(i, null);
        }
    }

    // PARK VEHICLE
    public void parkVehicle(String vehicle) {

        if (vehicleSlot.containsKey(vehicle)) {
            System.out.println("Vehicle already parked!");
            return;
        }

        for (int slot : slots.keySet()) {

            if (slots.get(slot) == null) {

                slots.put(slot, vehicle);
                vehicleSlot.put(vehicle, slot);
                entryTime.put(vehicle, System.currentTimeMillis());

                System.out.println("Vehicle " + vehicle + " parked at Slot " + slot);
                log("ENTRY", vehicle, slot, 0);
                return;
            }
        }

        System.out.println("Parking Full!");
    }

    // EXIT VEHICLE
    public void exitVehicle(String vehicle) {

        if (!vehicleSlot.containsKey(vehicle)) {
            System.out.println("Vehicle not found!");
            return;
        }

        int slot = vehicleSlot.get(vehicle);
        long entry = entryTime.get(vehicle);
        long exit = System.currentTimeMillis();

        long durationMin = (exit - entry) / 60000;
        if (durationMin == 0) durationMin = 1;

        int fee = (int) durationMin * 10; // ₹10 per minute

        slots.put(slot, null);
        vehicleSlot.remove(vehicle);
        entryTime.remove(vehicle);

        System.out.println("\n===== RECEIPT =====");
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Slot: " + slot);
        System.out.println("Duration: " + durationMin + " min");
        System.out.println("Fee: Rs." + fee);
        System.out.println("===================\n");

        log("EXIT", vehicle, slot, fee);
    }

    // SHOW STATUS
    public void showStatus() {

        System.out.println("\n--- PARKING STATUS ---");

        for (int i = 1; i <= totalSlots; i++) {
            String v = slots.get(i);
            System.out.println("Slot " + i + ": " + (v == null ? "EMPTY" : v));
        }

        System.out.println("Available Slots: " + getAvailableSlots());
    }

    public int getAvailableSlots() {
        return (int) slots.values().stream().filter(Objects::isNull).count();
    }

    // QR CODE
    public void generateQR() {

        StringBuilder sb = new StringBuilder();
        sb.append("PARKING STATUS\n");

        for (int i = 1; i <= totalSlots; i++) {
            sb.append("Slot ").append(i).append(": ")
              .append(slots.get(i) == null ? "EMPTY" : slots.get(i))
              .append("\n");
        }

        sb.append("Available Slots: ").append(getAvailableSlots());

        QRGenerator.generateQR(sb.toString());
    }

    // FILE LOGGING
    private void log(String type, String vehicle, int slot, int fee) {

        try (FileWriter fw = new FileWriter("parking_log.txt", true)) {

            fw.write(type + " | Vehicle: " + vehicle +
                     " | Slot: " + slot +
                     " | Fee: " + fee +
                     " | Time: " + new Date() + "\n");

        } catch (IOException e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }
}