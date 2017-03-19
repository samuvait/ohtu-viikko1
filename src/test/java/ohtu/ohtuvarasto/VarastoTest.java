package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenTayteen() {
        Varasto uusi = new Varasto(10, 10);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenNegatiivisella() {
        Varasto uusi = new Varasto(10, -2);
        assertEquals(0, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenLiianSuurella() {
        Varasto uusi = new Varasto(10, 11);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenNollallaSaldoNolla() {
        Varasto uusi = new Varasto(0, 0);
        assertEquals(0, uusi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luominenNollalla() {
        Varasto uusi = new Varasto(0);
        assertEquals(0, uusi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaminenNegatiivisella() {
        Varasto uusi = new Varasto(10, 5);
        uusi.lisaaVarastoon(-2);
        assertEquals(5, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaminenYliRajojen() {
        Varasto uusi = new Varasto(10, 5);
        uusi.lisaaVarastoon(7);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivisella() {
        Varasto uusi = new Varasto(10, 5);
        double otettu = uusi.otaVarastosta(-3);
        assertEquals(0, otettu, vertailuTarkkuus);
    }

    @Test
    public void ottaminenYliRajojen() {
        Varasto uusi = new Varasto(10, 5);
        double otettu = uusi.otaVarastosta(10);
        assertEquals(5, otettu, vertailuTarkkuus);
    }
    
    @Test
    public void tekstiOikein() {
        Varasto uusi = new Varasto(10, 5);
        String haluttu = "saldo = 5, vielä tilaa 5";
        String tulos = uusi.toString();
        assertTrue(tulos.contains("saldo = 5") && tulos.contains("tilaa 5"));
    }
}