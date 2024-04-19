package global.Template;

import java.util.HashSet;
import java.util.Iterator;

public class Status
{

	public final static String CODE_NEW = "001";
	public final static String CODE_DONE = "002";
	public final static String CODE_CUSTOM = "003";
	public final static String CODE_ERROR = "004";
	public final static String CODE_ERROR_DATAACCESS = "005";
	public final static String CODE_ERROR_APP_EXECUTION = "006";
	public final static String CODE_UNSUCESSFULL = "007";
	public final static String CODE_INPROCESS = "008";
	public final static String CODE_FAILURE = "009";
	public final static String CODE_RECORD_FOUND = "010";
	public final static String CODE_LIST = "012";
	public final static String CODE_TRANS_INPROCESS = "011";

	public final static Entry NEW = new Entry("", Status.CODE_NEW);
	public final static Entry DONE = new Entry("", Status.CODE_DONE);
	public final static Entry CUSTOM = new Entry("", Status.CODE_CUSTOM);
	public final static Entry LIST = new Entry("", Status.CODE_LIST);
	public final static Entry ERROR = new Entry("", Status.CODE_ERROR);
	public final static Entry ERROR_DA = new Entry("", Status.CODE_ERROR);
	public final static Entry ERROR_AE = new Entry("", Status.CODE_ERROR);
	public final static Entry UNSUCESSFULL = new Entry("", Status.CODE_UNSUCESSFULL);
	public final static Entry INPROCESS = new Entry("", Status.CODE_INPROCESS);
	public final static Entry FAILURE = new Entry("", Status.CODE_FAILURE);
	public final static Entry RECORDFOUND = new Entry("", Status.CODE_RECORD_FOUND);
	public final static Entry TRANSINPROCESS = new Entry("", Status.CODE_TRANS_INPROCESS);

	private HashSet statusList = new HashSet();

	public HashSet getStatusList()
	{
		return statusList;
	}

	public void clear()
	{
		statusList.clear();
	}

	// status corresponds to label whereas _code corresponds to value
	public void add(Entry _objEntry, String _msg, String _msgType)
	{
		// _objEntry.setLabel(_msgType+_msg);
		//System.out.println("_msgType... " + _msgType + "_msg.... " + _msg);
		Entry entry = (Entry) _objEntry.clone();
		entry.setLabel(_msgType + _msg);
		this.add(entry);
	}

	public void add(Status _status)
	{
		this.statusList.addAll(_status.statusList);
	}

	public void add(String _code, String _status)
	{
		statusList.add(new Entry(_status, _code));
	}

	public void add(Entry _objEntry)
	{
		Entry entry = (Entry) _objEntry.clone();
		statusList.add(entry);
	}

	public boolean contains(Entry _objEntry)
	{
		return this.statusList.contains(_objEntry);
	}

	public boolean remove(Entry _objEntry)
	{
		return this.statusList.remove(_objEntry);
	}

	public String getMessage(String _code)
	{
		//System.out.println(_code);
		Iterator it = statusList.iterator();
		while (it.hasNext())
		{
			Entry objEntry = (Entry) it.next();
			if (objEntry.getValue().trim().equalsIgnoreCase(_code)) return objEntry.getLabel();
		}
		return "";
	}

}// end of class
