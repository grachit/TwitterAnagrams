import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnagramsReducer extends Reducer<TextPair, Text, Text, Text> {

	@Override
	public void reduce(TextPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Iterator itr = values.iterator();
		
		Set<String> set = new HashSet<>();
		int count =0;
		while (itr.hasNext()) {
			Text t = (Text) itr.next();
			count++;
			if (set.add(t.toString().toUpperCase()));
				
		}
		if(set.size() > 1)
			context.write(key.getSecond(), new Text(set.toString()));
		/*
		 * TODO implement
		 */

	}
}