package core.basesyntax.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyHandlerTest {
    private static final String FRUIT_NAME = "banana";
    private static final int CURRENT_QUANTITY = 30;
    private static final int ADD_QUANTITY = 20;
    private static final int EXPECTED_QUANTITY = 50;
    private static final int ZERO_QUANTITY = 0;
    private static final int NEGATIVE_QUANTITY = -10;
    private static final String EXCEPTION_MESSAGE_NEGATIVE_QUANTITY =
            "Quantity of fruit can`t be a negative";

    private OperationHandler supplyHandler;

    @BeforeEach
    void setUp() {
        supplyHandler = new SupplyHandler();
    }

    @AfterEach
    void tearDown() {
        Storage.storage.clear();
    }

    @Test
    void handle_correctQuantity_ok() {
        Storage.storage.put(FRUIT_NAME, CURRENT_QUANTITY);
        supplyHandler.handle(FRUIT_NAME, ADD_QUANTITY);
        assertTrue(Storage.storage.containsKey(FRUIT_NAME));
        assertEquals(EXPECTED_QUANTITY, Storage.storage.get(FRUIT_NAME));
    }

    @Test
    void handle_productNotExist_ok() {
        supplyHandler.handle(FRUIT_NAME, ADD_QUANTITY);
        assertTrue(Storage.storage.containsKey(FRUIT_NAME));
        assertEquals(ADD_QUANTITY, Storage.storage.get(FRUIT_NAME));
    }

    @Test
    void handle_negativeQuantity_notOk() {
        var negativeQuantity = assertThrows(RuntimeException.class,
                () -> supplyHandler.handle(FRUIT_NAME, NEGATIVE_QUANTITY));
        assertEquals(EXCEPTION_MESSAGE_NEGATIVE_QUANTITY, negativeQuantity.getMessage());
    }

    @Test
    void handle_zeroQuantity_ok() {
        Storage.storage.put(FRUIT_NAME, CURRENT_QUANTITY);
        supplyHandler.handle(FRUIT_NAME, ZERO_QUANTITY);
        assertEquals(CURRENT_QUANTITY, Storage.storage.get(FRUIT_NAME));
    }
}
