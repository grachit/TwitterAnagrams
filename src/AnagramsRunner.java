import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.lib.MultipleOutputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hive.hcatalog.mapreduce.HCatInputFormat;

public class AnagramsRunner extends Configured implements Tool {



	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		conf.set("mapred.input.dir.recursive","true");
//		args = new GenericOptionsParser(conf, args).getRemainingArgs();

		// Get the input and output table names as arguments

		// Assume the default database
		String dbName = null;

		Job  job = Job.getInstance(conf);
		HCatInputFormat.setInput(job, "default", "tweets");
		job.setJarByClass(AnagramsRunner.class);
		job.setMapperClass(AnagramsMapper.class);
		job.setReducerClass(AnagramsReducer.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		job.setSortComparatorClass(KeyComparator.class);
		job.setPartitionerClass(CustomPartitioner.class);
		// An HCatalog record as input
		job.setInputFormatClass(HCatInputFormat.class);
		// Mapper emits a string as key and an integer as value
		job.setMapOutputKeyClass(TextPair.class);
		job.setMapOutputValueClass(Text.class);
		job.setNumReduceTasks(2);
		// Ignore the key for the reducerIntWritable output; emitting an
		// HCatalog record as value
		job.setOutputKeyClass(TextPair.class);
		job.setOutputValueClass(Text.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileOutputFormat.setOutputPath(job, new Path("BigData/output"));
		return (job.waitForCompletion(true) ? 0 : 1);
	}
	
	public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new AnagramsRunner(), args);
        System.exit(exitCode);
    }

}
