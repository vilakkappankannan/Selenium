package utils;

import net.datafaker.Faker;

import java.util.UUID;

public class RandomDataUtils {

	private static final String MARKER_PREFIX = "{{";
	private static final String MARKER_POSTFIX = "}}";

	static final String RANDOM_UUID = "randomUUID";

	private static final Faker faker = new Faker();

	private RandomDataUtils() {
		// private constructor to hide implicit public one
	}

	public static Object generateRandomData(String dataPlaceholder) throws RandomDataGeneratorException {
		switch (dataPlaceholder) {
			case (RANDOM_UUID) :
				return UUID.randomUUID().toString();

			default :
				throw new RandomDataGeneratorException("The given data element " + MARKER_PREFIX + dataPlaceholder
						+ MARKER_POSTFIX + " is not a supported type");
		}
	}
}
