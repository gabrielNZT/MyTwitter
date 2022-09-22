package testesUnitarios;

import usuario.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteTweet {

    Tweet run;

    @BeforeEach
    public void init(){
        run = new Tweet("@gabriel_nzt","o dia hj foi complicado");
    }

    @Test
    public void TestTweetMenssagem(){
        assertEquals("o dia hj foi complicado", run.getMensagem());
    }

    @Test
    public void TestTweetUsuario(){
        assertEquals("@gabriel_nzt", run.getUsuario());
    }
}
