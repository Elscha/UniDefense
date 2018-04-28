package uni_defense.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import uni_defense.logic.world.World;

public class Server extends Side {

    private Socket socket;
    
    public Server(int port, World world, IWaveListener listener) throws IOException {
        super(world, listener);
        
        ServerSocket serSock = new ServerSocket(port);
        
        this.socket = serSock.accept();
        
        start(socket.getOutputStream(), socket.getInputStream());
    }
    
    @Override
    protected void onDone() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
