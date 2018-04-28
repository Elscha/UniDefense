package uni_defense.networking;

public enum Command {

    WAVE((byte) 1),
    
    DISCONNECT((byte) 2),
    
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
        default: return null;
        }
    }
    
}
