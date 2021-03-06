package by.mrkip.apps.weatherarchive.globalObj;


public class JsonKeys {
	public static class JsonResponseObjectsTags {
		public static final String TIME_ZONE = "time_zone";
		public static final String CURRENT_WEATHER = "current_condition";
		public static final String NEAREST_AREA = "nearest_area";
		public static final String AREA_NAME = "areaName";
		public static final String WEATHER = "weather";
		public static final String DATA = "data";
		public static final String COUNTRY = "country";
		public static final String WEATHER_DESC = "weatherDesc";
		public static final String WEATHER_ICON_URL = "weatherIconUrl";
		public static final String HOURLY = "hourly";
		public static final String PREDICTIONS = "predictions";
	}

	public static class JsonResponseValuesTags {
		public static final String STATUS = "status";
		public static final String LOCALTIME = "localtime";
		public static final String TEMP_C = "temp_C";
		public static final String WEATHER_DESC = "weatherDesc";
		public static final String WEATHER_ICON_URL = "weatherIconUrl";
		public static final String KEY_VALUE = "value";
		public static final String DATE = "date";
		public static final String TEMP_C1 = "tempC";
		public static final String HUMIDITY = "humidity";
		public static final String WINDSPEED_KMPH = "windspeedKmph";
		public static final String DESCRIPTION = "description";
		public static final String ERROR_MESSAGE = "error_message";
		public static final String PLACE_ID = "place_id";
		public static final String LONGITUDE = "longitude";
		public static final String LATITUDE = "latitude";

	}

	public static class JsonRequestParams {
		public static final String Q = "q";
		public static final String FORMAT = "format";
		public static final String DATE = "date";
		public static final String INCLUDELOCATION = "includelocation";
		public static final String TP = "tp";
		public static final String ENDDATE = "enddate";
		public static final String NUMOFDAY = "num_of_days";
		public static final String CURRENT_WEATHER = "cc";
		public static final String CLIMATE = "mca";
		public static final String SHOWLOCALTIME = "showlocaltime";
		public static final String LANG = "lang";
		public static final String KEY = "key";
		public static final String TYPES = "types";
		public static final String INPUT = "input";
		public static final String PLACEID = "placeid";
		public static final String CITIES = "(cities)";
	}

	public static class JsonRequestParamsValues {
		public static final String COMMA = ",";
		public static final String NO = "no";
		public static final String RU = "ru";
		public static final String JSON = "json";
		public static final String YES = "yes";
	}

}
