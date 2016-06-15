import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.schema.HCatSchema;
import org.apache.hive.hcatalog.mapreduce.HCatBaseInputFormat;

public class AnagramsMapper extends
		Mapper<WritableComparable, HCatRecord, TextPair, Text> {

	@Override
	public void map(WritableComparable key, HCatRecord value, Context context)
			throws IOException, InterruptedException {

		HCatSchema schema = HCatBaseInputFormat.getTableSchema(context
				.getConfiguration());
		String created_at = value.getString("created_at", schema);
		String text = value.getString("text", schema);
		StringTokenizer strTokens = new StringTokenizer(text);
		int count=0;
		while (strTokens.hasMoreTokens()) {
			String token = strTokens.nextToken();
			token = token.replaceAll("[^a-zA-Z0-9]","");
			char[] charArray = token.toUpperCase().toCharArray();
			Arrays.sort(charArray);
			TextPair intTextPair = new TextPair(getDayFromDate(created_at),
					new String(charArray));
			context.write(intTextPair, new Text(token));
		}

	}

	// context.write

	/*
	 * TODO implement
	 */

	private static Integer getDayFromDate(String date) {
		try {
			final String inputFormat = "EEE MMM dd HH:mm:ss z yyyy";
			return Integer.parseInt(new SimpleDateFormat("dd")
					.format(new SimpleDateFormat(inputFormat).parse(date))) + 1;
		} catch (Exception e) {
			return -1;
		}

	}

	public static void main(String a[]) throws Exception {
		System.out.println(getDayFromDate("Tue Jun 31 04:46:09 +0000 2016"));
	}
}
