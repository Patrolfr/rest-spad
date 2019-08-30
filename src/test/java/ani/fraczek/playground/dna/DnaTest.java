package ani.fraczek.playground.dna;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DnaTest{

    @Test
    public void test01() {
        assertEquals("TTTT", DnaStrand.makeComplement("AAAA"));
    }
    @Test
    public void test02() {
        assertEquals("TAACG", DnaStrand.makeComplement("ATTGC"));
    }
    @Test
    public void test03() {
        assertEquals("CATA", DnaStrand.makeComplement("GTAT"));
    }


    @Test
    public void test04() {
        assertEquals("TTTT", DnaStrand.makeComplementFast("AAAA"));
    }
    @Test
    public void test05() {
        assertEquals("TAACG", DnaStrand.makeComplementFast("ATTGC"));
    }
    @Test
    public void test06() {
        assertEquals("CATA", DnaStrand.makeComplementFast("GTAT"));
    }


}
