package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtils {
	private static final String REQUEST_TEMPLATE_PATH = "json/request-templates/";
	private static final String JSON_FILE_EXT = ".json";

	private static final JSONParser jsonParser = new JSONParser();
	public static final String ARRAY_REGEX = "^\\[[0-9]+]$";

	private JsonUtils() {
		// static access only, hides implicit public constructor
	}

	public static JSONObject getRequestTemplateJson(String templateName) throws IOException, ParseException {
		return getTemplateJson(REQUEST_TEMPLATE_PATH + templateName + JSON_FILE_EXT);
	}

	private static JSONObject getTemplateJson(String path) throws IOException, ParseException {
		return (JSONObject) jsonParser.parse(new InputStreamReader(new ClassPathResource(path).getInputStream()));
	}

	public static Object stringToJsonObject(String jsonString) throws ParseException {
		return (JSONObject) jsonParser.parse(jsonString);
	}

	/**
	 * Finds a specific JSONObject from within a json tree structure using the given
	 * searchIndex. The index is a specific location of the object we are looking
	 * for where a '.' is used to indicate the json object at each level of the tree
	 * and '[]' notation is used to indicate an array. For example: <br>
	 * <code>
	 * {
	 *   foo: {
	 *     bar: [
	 *        {bar:"A"},
	 *        {bar:"B"}
	 *     ]
	 *   }
	 * }
	 * </code><br>
	 * In the above json {bar:"B} would be returned if searchIndex was "foo.bar.[1]"
	 *
	 * @param rootNode
	 *            JSONObject - The root of the tree to begin the search
	 * @param searchIndex
	 *            String - The index of the node we are searching for
	 * @return JSONObject - The JSONObject which is at the location of the
	 *         searchIndex OR if the node was not found this value will be the node
	 *         at the depth where the last match was successful.
	 */
	public static JSONObject findIndexInTree(JSONObject rootNode, String searchIndex) {
		Object node = rootNode;

		String[] splitPath = searchIndex.split("\\.");
		int i = 0;
		int searchDepth = splitPath.length - 1;
		// If the searchIndex points to an array index, return the item at that index,
		// otherwise we always
		// return the JSONObject containing the item we are looking for.
		if (splitPath[splitPath.length - 1].matches(ARRAY_REGEX)) {
			searchDepth++;
		}
		for (; i < searchDepth; i++) {
			if (splitPath[i].matches(ARRAY_REGEX) && node instanceof JSONArray) {
				node = ((JSONArray) node).get(Integer.parseInt(splitPath[i].substring(1, splitPath[i].length() - 1)));
			} else {
				node = ((JSONObject) node).get(splitPath[i]);
			}
		}

		return (JSONObject) node;
	}
}
