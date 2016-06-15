import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class TextPair implements WritableComparable<TextPair> {

	private Text first;
	private Text second;
	
	public TextPair(){
		set(new Text(), new Text());
	}
	
	public TextPair(int first, String second){
		set(new Text(String.valueOf(first)), new Text(second));
	}
	
	public Text getFirst() {
		return first;
	}

	public Text getSecond() {
		return second;
	}

	public void set(Text first, Text second){
		this.first = first;
		this.second = second;
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return first.hashCode()*163+second.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof TextPair){
			TextPair intTextPair = (TextPair)o;
			return first.equals(intTextPair.first) && second.equals(intTextPair.second);
		}
		return false;
			
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		first.write(out);
		second.write(out);
	}

	@Override
	public int compareTo(TextPair o) {
		int cmp = first.compareTo(o.first);
		if(cmp!=0)
			return cmp;
		return second.compareTo(o.second);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return first +"\t" + second;
	}
	
	public static int compare(Text first, Text second){
		return first.compareTo(second );
	}
}
