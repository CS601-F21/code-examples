import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailParserTest {

    @Test
    void testSimpleValid() {
        EmailParser parser = new EmailParser("snrollins@usfca.edu");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testSimpleLocal() {
        EmailParser parser = new EmailParser("snrollins@usfca.edu");
        assertEquals("snrollins", parser.getLocal(), parser.debug());
    }

    @Test
    public void testSimpleDomain() {
        EmailParser parser = new EmailParser("snrollins@usfca.edu");
        assertEquals("usfca.edu", parser.getDomain(), parser.debug());
    }

    @Test
    public void testSimpleTLD() {
        EmailParser parser = new EmailParser("snrollins@usfca.edu");
        assertEquals("edu", parser.getTLD(), parser.debug());
    }

    /*
     * The following tests focus on a email with a subdomain,
     * srollins@cs.usfca.edu, and makes sure parsing works.
     */

    @Test
    public void testSubdomainValid() {
        EmailParser parser = new EmailParser("srollins@cs.usfca.edu");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testSubdomainLocal() {
        EmailParser parser = new EmailParser("srollins@cs.usfca.edu");
        assertEquals("srollins", parser.getLocal(), parser.debug());
    }

    @Test
    public void testSubdomainDomain() {
        EmailParser parser = new EmailParser("srollins@cs.usfca.edu");
        assertEquals("cs.usfca.edu", parser.getDomain(), parser.debug());
    }

    @Test
    public void testSubdomainTLD() {
        EmailParser parser = new EmailParser("sjengle@cs.usfca.edu");
        assertEquals("edu", parser.getTLD(), parser.debug());
    }

    /*
     * The following tests focus on a complex email,
     * aaa.bb+0123@c45.6-d.ee, and makes sure parsing works.
     */

    @Test
    public void testComplexValid() {
        EmailParser parser = new EmailParser("aaa.bb+0123@c45.6-d.ee");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testComplexLocal() {
        EmailParser parser = new EmailParser("aaa.bb+0123@c45.6-d.ee");
        assertEquals("aaa.bb+0123", parser.getLocal(), parser.debug());
    }

    @Test
    public void testComplexDomain() {
        EmailParser parser = new EmailParser("aaa.bb+0123@c45.6-d.ee");
        assertEquals("c45.6-d.ee", parser.getDomain(), parser.debug());
    }

    @Test
    public void testComplexTLD() {
        EmailParser parser = new EmailParser("aaa.bb+0123@c45.6-d.ee");
        assertEquals("ee", parser.getTLD(), parser.debug());
    }

    /*
     * The following tests focus on making sure the emails are
     * considered valid.
     */

    @Test
    public void testValidManyDots() {
        EmailParser parser = new EmailParser("aa.bb.cccc@dd.eeee.f");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testValidDigits() {
        EmailParser parser = new EmailParser("0123@4567.aaa");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testValidSymbols() {
        EmailParser parser = new EmailParser("aa+bb#cc@dddd.e");
        assertTrue(parser.isValid(), parser.debug());
    }

    @Test
    public void testValidShort() {
        EmailParser parser = new EmailParser("a@b.c");
        assertTrue(parser.isValid(), parser.debug());
    }

    /*
     * The following tests focus on making sure the emails are not
     * considered valid.
     */

    @Test
    public void testInvalidNoAt() {
        EmailParser parser = new EmailParser("aaaa");
        assertFalse(parser.isValid(), parser.debug());
    }

    @Test
    public void testInvalidNoDomain() {
        EmailParser parser = new EmailParser("aaaa@");
        assertFalse(parser.isValid(), parser.debug());
    }

    @Test
    public void testInvalidNoLocal() {
        EmailParser parser = new EmailParser("@bbbb.ccc");
        assertFalse(parser.isValid(), parser.debug());
    }

    @Test
    public void testInvalidNoTLD() {
        EmailParser parser = new EmailParser("aaaa@bbbb");
        assertFalse(parser.isValid(), parser.debug());
    }

    @Test
    public void testInvalidTooManyAts() {
        EmailParser parser = new EmailParser("aaaa@bbbb@cccc.ddd");
        assertFalse(parser.isValid(), parser.debug());
    }}