package guru.springframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InlineMockTest {
    @Test
    void testInlineMock() {
        Map mapMock = mock(Map.class);
        assertEquals(mapMock.size(), 0);
    }
}
