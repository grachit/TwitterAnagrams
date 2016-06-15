import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {

	protected GroupComparator() {
		// TODO Auto-generated constructor stub
		super(TextPair.class, true);
	}


	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		TextPair a1 = (TextPair) a;
		TextPair a2 = (TextPair) b;
		return TextPair.compare(a1.getSecond(), a2.getSecond());
	}

}
