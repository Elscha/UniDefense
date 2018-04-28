package uni_defense.networking;

import java.io.IOException;
import java.net.Socket;

import uni_defense.logic.world.World;

public class Client extends Side {

    private Socket socket;

    public Client(String host, int port, World world, IWaveListener listener) throws IOException {
        super(world, listener);
        
        this.socket = new Socket(host, port);
        
        start(socket.getOutputStream(), socket.getInputStream());
    }

    @Override
    protected void onDone() {
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
