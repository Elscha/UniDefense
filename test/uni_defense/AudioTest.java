package uni_defense;
import uni_defense.audio.*;
import java.util.concurrent.TimeUnit;


import org.junit.Test;

public class AudioTest {
	
	@Test
	public void test() {
		int x=1;
		Music bgmMus = new Music("bgm/splashSong.wav");
		Sound sfxTest = new Sound("sfx/bleep1.wav");
		bgmMus.musicStart();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sfxTest.soundStart();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bgmMus.musicStop();
	}

}
