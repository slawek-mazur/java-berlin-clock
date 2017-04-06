package com.ubs.opsit.interviews;

import java.util.stream.Stream;

import static org.apache.commons.lang.StringUtils.rightPad;

public class TimeConverterImpl implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        final int[] parts =
                Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();

        return String.join(System.lineSeparator(),
                topLampState(parts[2]),
                hoursFirstLine(parts[0]),
                hoursSecondLine(parts[0]),
                minutesFirstLine(parts[1]),
                minutesSecondLine(parts[1])
        );
    }

    private String minutesSecondLine(int minutes) {
        final int on = minutes % 5;
        final int off = 4 - on;

        return rightPad("", on, 'Y') + rightPad("", off, 'O');
    }

    private String minutesFirstLine(int minutes) {
        final int on = minutes / 5;
        final int off = 11 - on;

        return (rightPad("", on, 'Y') + rightPad("", off, 'O')).replace("YYY", "YYR");
    }

    private String hoursSecondLine(int hours) {
        final int on = hours % 5;
        final int off = 4 - on;

        return rightPad("", on, 'R') + rightPad("", off, 'O');
    }

    private String hoursFirstLine(int hours) {
        final int on = hours / 5;
        final int off = 4 - on;

        return rightPad("", on, 'R') + rightPad("", off, 'O');
    }

    private String topLampState(int seconds) {
        return seconds % 2 == 0 ? "Y" : "O";
    }
}
