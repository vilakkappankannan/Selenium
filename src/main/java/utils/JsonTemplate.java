package utils;

import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This JsonTemplate class is used to efficiently templatize json objects so
 * that placeholder data elements can be replaced with their actual values at
 * runtime. Values are replaced in O(1) best case, and O(log(n)) worst case time
 * complexity and O(1) space complexity. Instantiating this object will create
 * an index of the templated fields, this indexing runs O(n) time complexity,
 * space complexity is O(n) where n is the number of templated fields.
 *
 * @author Joe Fratini
 */
public class JsonTemplate {
	private final Map<String, String> templateMapping;
	private final JSONObject templateData;

	/**
	 * Create a template from a json object. This class will map any templatized
	 * json values to a template map so that values can be replaced very efficiently
	 * in the base json object.
	 *
	 * @param jsonToTemplatize
	 *            JSONObject - Json to use as a template
	 */
	public JsonTemplate(JSONObject jsonToTemplatize) {
		templateMapping = new HashMap<>();
		processJsonObject(jsonToTemplatize, "");
		templateData = jsonToTemplatize;
	}

	private void processJsonObject(JSONObject jsonObject, String pathSoFar) {
		if (!jsonObject.isEmpty()) {
			jsonObject.forEach((key, value) -> checkValue(pathSoFar, key, value));
		}
	}

	private void processJsonArray(JSONArray jsonArray, String pathSoFar) {
		if (!jsonArray.isEmpty()) {
			for (int i = 0; i < jsonArray.size(); i++) {
				checkValue(pathSoFar, "[" + i + "]", jsonArray.get(i));
			}
		}
	}

	private void checkValue(String pathSoFar, Object key, Object value) {
		final String newPath = pathSoFar.isEmpty() ? key.toString() : pathSoFar + "." + key.toString();
		if (value instanceof JSONObject) {
			processJsonObject((JSONObject) value, newPath);
		} else if (value instanceof JSONArray) {
			processJsonArray((JSONArray) value, newPath);
		} else if (value instanceof String && value.toString().matches("^\\{\\{(.*?)}}$")) {
			var valueStr = value.toString();
			templateMapping.put(valueStr.substring(2, valueStr.length() - 2), newPath);
		}
	}

	/**
	 * Replaces the value at the key denoted by the templateValue with the given
	 * newValue.
	 *
	 * @param templateValue
	 *            String - A placeholder value in the original json which we want to
	 *            replace.
	 * @param newValue
	 *            Object - The value to replace the placeholder with.
	 * @throws TemplateValueNotFoundException
	 *             - Thrown when the given templateValue does not exist.
	 */
	public void replaceInTemplate(String templateValue, Object newValue) throws TemplateValueNotFoundException {
		String pathToTarget = templateMapping.get(templateValue);
		if (pathToTarget == null) {
			throw new TemplateValueNotFoundException(templateValue);
		}
		String[] splitPath = pathToTarget.split("\\.");
		JSONObject nodeToReplace = JsonUtils.findIndexInTree(templateData, pathToTarget);
		nodeToReplace.replace(splitPath[splitPath.length - 1], newValue);
		templateMapping.remove(templateValue);
	}

	/**
     * Gets the JSONObject once all the placeholder values have been replaced. Note:
     * If any placeholders have not been replace this method will throw a
     * <code>TemplateNotSatisfiedException
     * </code>.
     *
     * @return JSONObject - The final json object with all placeholder values
     * satisfied.
     * @throws TemplateNotSatisfiedException - Thrown if the template was not fully satisfied when this method
     *                                       is called.
     */
	public Object getSatisfiedJson() throws TemplateNotSatisfiedException {
		if (templateMapping.size() > 0) {
			throw new TemplateNotSatisfiedException(
					"Template must be fully satisfied before its ready to use! \nRemaining JSON values: "
							+ ArrayUtils.toString(templateMapping.keySet().toArray()));
		}
		return templateData;
	}
}
