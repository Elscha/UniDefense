package uni_defense.networking;

public enum Command {

    WAVE((byte) 1),
    DISCONNECT((byte) 2),
    LOST((byte) 3),
    
    ;
    
    private byte id;
    
    private Command(byte id) {
        this.id = id;
    }
    
    public byte getId() {
        return id;
    }
    
    public static Command getById(byte id) {
        switch (id) {
        case 1: return WAVE;
        case 2: return DISCONNECT;
        case 3: return LOST;
        default: return null;
        }
    }
    
}
