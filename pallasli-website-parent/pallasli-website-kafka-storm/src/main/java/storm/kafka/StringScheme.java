package storm.kafka;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class StringScheme implements Scheme {

	public static final String STRING_SCHEME_KEY = "str";

	public List<Object> deserialize(byte[] bytes) {
		return new Values(deserializeString(bytes));
	}

	public static String deserializeString(byte[] string) {
		try {
			return new String(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Fields getOutputFields() {
		return new Fields(STRING_SCHEME_KEY);
	}

	@Override
	public List<Object> deserialize(ByteBuffer arg0) {

		return null;
	}
}