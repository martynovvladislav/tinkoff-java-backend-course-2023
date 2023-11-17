package edu.hw5.task3;

import edu.hw5.task3.handlers.DateHandler;
import edu.hw5.task3.handlers.FirstDigitDateHandler;
import edu.hw5.task3.handlers.FirstDigitNTextDateHandler;
import edu.hw5.task3.handlers.FirstTextDateHandler;
import edu.hw5.task3.handlers.FourthDigitDateHandler;
import edu.hw5.task3.handlers.SecondDigitDateHandler;
import edu.hw5.task3.handlers.SecondDigitNTextDateHandler;
import edu.hw5.task3.handlers.SecondTextDateHandler;
import edu.hw5.task3.handlers.ThirdDigitDateHandler;
import edu.hw5.task3.handlers.ThirdTextDateHandler;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task3 {
    private Task3() {}

    private static final List<DateHandler> HANDLER_LIST = List.of(new FirstDigitDateHandler(),
        new SecondDigitDateHandler(), new ThirdDigitDateHandler(), new FourthDigitDateHandler(),
        new FirstTextDateHandler(), new SecondTextDateHandler(), new ThirdTextDateHandler(),
        new FirstDigitNTextDateHandler(), new SecondDigitNTextDateHandler()
    );

    public static Optional<LocalDate> parseDate(String date) {
        return HANDLER_LIST
            .stream()
            .map(handler -> handler.handle(date))
            .filter(Objects::nonNull)
            .findAny();
    }
}
