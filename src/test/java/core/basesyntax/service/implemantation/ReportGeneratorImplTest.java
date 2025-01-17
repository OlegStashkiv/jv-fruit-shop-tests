package core.basesyntax.service.implemantation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.service.ReportGenerator;
import core.basesyntax.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private static final String LINE_HEADER = "fruit,quantity";
    private static final String FRUIT_NAME = "banana";
    private static final int FRUIT_QUANTITY = 10;
    private static final String EXPECTED_REPORT =
            LINE_HEADER + "\n" + FRUIT_NAME + "," + FRUIT_QUANTITY;

    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        reportGenerator = new ReportGeneratorImpl();
    }

    @Test
    void report_Generate_Ok() {
        Storage.storage.put(FRUIT_NAME, FRUIT_QUANTITY);
        String actualReport = reportGenerator.generateReport();
        assertEquals(EXPECTED_REPORT, actualReport);
        Storage.storage.clear();
    }
}
