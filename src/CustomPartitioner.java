import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class CustomPartitioner extends Partitioner<TextPair, Text> {

	@Override
	public int getPartition(TextPair arg0, Text arg1, int arg2) {
		// TODO Auto-generated method stub
		return Math.abs(Integer.parseInt(arg0.getFirst().toString()) *127) % arg2;
	}

}
