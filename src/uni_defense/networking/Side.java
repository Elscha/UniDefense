package uni_defense.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uni_defense.logic.enemies.wave.AbstractWave;
import uni_defense.logic.world.World;

public abstract class Side implements Runnable {

    private OutputStream out;
    
    private InputStream in;
    
    private IWaveListener listener;
    
    private World world;
    
    public Side(World world, IWaveListener listener) {
        this.world = world;
        this.listener = listener;
    }
    
    protected void start(OutputStream out, InputStream in) {
        this.in = in;
        this.out = out;
        
        new Thread(this).start();
    }
    
    protected abstract void onDone();
    
    public void stop() {
        try {
            out.write(Command.DISCONNECT.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        onDone();
    }
    
    public void sendWave(Class<? extends AbstractWave> wave) {
        try {
            
            out.write(Command.WAVE.getId());
            try {
                out.write((byte) wave.getMethod("getId").invoke(null));
            } catch (ReflectiveOperationException | SecurityException e) {
                e.printStackTrace();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        int read;
        
        try {
            while ((read = in.read()) != -1) {
                Command c = Command.getById((byte) read);
                
                switch (c) {
                
                case WAVE:
                    int id = in.read();
                    if (id != -1) {
                        AbstractWave wave = AbstractWave.createWaveById(world, (byte) id);
                        listener.addWave(wave);
                    }
                    break;
                
                case DISCONNECT:
                    onDone();
                    return;
                
                default:
                    System.err.println("Unkown command: " + c);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
