import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

public class KeyComparator extends WritableComparator {

	protected KeyComparator() {
		// TODO Auto-generated constructor stub
		super(TextPair.class, true);
	}

	private static final Text.Comparator TEXT_COMPARATOR = new Text.Comparator();

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		TextPair a1 = (TextPair) a;
		TextPair a2 = (TextPair) b;
		return a1.compareTo(a2);
	}

}
