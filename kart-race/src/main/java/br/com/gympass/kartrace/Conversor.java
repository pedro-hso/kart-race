package br.com.gympass.kartrace;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversor {

	private static final DateTimeFormatter HOUR_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	private static final String HOUR_PATTERN = "(\\d{2}:\\d{2}:\\d{2}.\\d{3})";
	private static final String SPACE_PATTERN = "\\s+";
	private static final String PILOT_PATTERN = "(\\d{3}) – ([A-Za-z\\.]+)";
	private static final String LAP_PATTERN = "(\\d)";
	private static final String LAP_TIME_PATTERN = "((\\d+):)?(\\d+)\\.(\\d{3})";
	private static final String AVG_SPEED_PATTERN = "(\\d+,\\d+)";
	private static final String LINE_PATTERN = HOUR_PATTERN + SPACE_PATTERN + PILOT_PATTERN + SPACE_PATTERN
			+ LAP_PATTERN + SPACE_PATTERN + LAP_TIME_PATTERN + SPACE_PATTERN + AVG_SPEED_PATTERN;

	private static final int HOUR_GROUP = 1;
	private static final int PILOT_CODE_GROUP = 2;
	private static final int PILOT_NAME_GROUP = 3;
	private static final int LAP_GROUP = 4;
	private static final int LAP_TIME_MINUTE_GROUP = 6;
	private static final int LAP_TIME_SECOND_GROUP = 7;
	private static final int LAP_TIME_MILLIS_GROUP = 8;
	private static final int AVG_SPEED_GROUP = 9;

	public Volta conversor(String linha) {
		Pattern pattern = Pattern.compile(LINE_PATTERN);
		Matcher matcher = pattern.matcher(linha);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(String.format("Linha inválida: " + linha));
		}

		Volta volta = new Volta(LocalTime.parse(matcher.group(HOUR_GROUP), HOUR_FORMATTER),
				matcher.group(PILOT_CODE_GROUP), matcher.group(PILOT_NAME_GROUP),
				Integer.parseInt(matcher.group(LAP_GROUP)),
				LocalTime.of(0, Integer.parseInt(matcher.group(LAP_TIME_MINUTE_GROUP)),
						Integer.parseInt(matcher.group(LAP_TIME_SECOND_GROUP)),
						Integer.parseInt(matcher.group(LAP_TIME_MILLIS_GROUP)) * 1000000),
				Float.parseFloat(matcher.group(AVG_SPEED_GROUP).replace(",", ".")));

		return volta;
	}
}
