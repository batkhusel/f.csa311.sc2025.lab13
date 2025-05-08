package AndrewWebServices;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class AndrewWebServicesTest {
    @Test
    public void testLogInWithFakeDatabase() {
        InMemoryDatabase fakeDb = new InMemoryDatabase();
        fakeDb.addUser("testUser", 1234);

        RecSys dummyRecSys = account -> "dummy";  // Dummy implementation
        PromoService dummyPromo = email -> {};     // Dummy implementation

        AndrewWebServices aws = new AndrewWebServices(fakeDb, dummyRecSys, dummyPromo);

        assertTrue(aws.logIn("testUser", 1234));
        assertFalse(aws.logIn("testUser", 4321));
        assertFalse(aws.logIn("unknownUser", 1234));
    }

    @Test
    public void testGetRecommendationWithStub() {
        Database dummyDb = new Database(); // Actual class, but unused in this test
        RecSys stubRec = new StubRecSys();
        PromoService dummyPromo = email -> {};

        AndrewWebServices aws = new AndrewWebServices(dummyDb, stubRec, dummyPromo);

        String result = aws.getRecommendation("anyUser");
        assertEquals("StubMovie", result);
    }

    @Test
    public void testSendPromoEmailWithMock() {
        Database dummyDb = new Database();
        RecSys dummyRec = account -> "dummy";

        PromoService promoMock = mock(PromoService.class);

        AndrewWebServices aws = new AndrewWebServices(dummyDb, dummyRec, promoMock);

        aws.sendPromoEmail("test@example.com");

        verify(promoMock).mailTo("test@example.com");
    }
}
