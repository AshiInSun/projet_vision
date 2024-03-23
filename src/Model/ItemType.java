package Model;

public enum ItemType {
    BLE, PIERRE;

    public String toString() {
        switch(this) {
            case BLE: return "ble";
            case PIERRE: return "pierre";
            default: throw new IllegalArgumentException();
        }
    }
}
