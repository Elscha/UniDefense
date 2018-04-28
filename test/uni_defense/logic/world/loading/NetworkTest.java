package uni_defense.logic.world.loading;

import java.io.IOException;

import org.junit.Test;

import uni_defense.logic.enemies.wave.AbstractWave;
import uni_defense.logic.enemies.wave.WorkerBossWave;
import uni_defense.networking.Client;
import uni_defense.networking.IWaveListener;
import uni_defense.networking.Server;

public class NetworkTest {

    @Test
    public void test() {
        Thread th = new Thread(() -> {
            try {
                Server server = new Server(1225, null, new IWaveListener() {
                    
                    @Override
                    public void addWave(AbstractWave wave) {
                        System.out.println("Got wave " + wave);
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        th.start();
        
        Client client;
        try {
            client = new Client("localhost", 1225, null, new IWaveListener() {
                
                @Override
                public void addWave(AbstractWave wave) {
                    System.out.println("Got wave: " + wave);
                }
            });
            client.sendWave(WorkerBossWave.class);
            
            client.stop();
            
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        try {
            th.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
