package data;

import java.util.HashMap;
import java.util.Map;

public class LogDB {
	static final Map<Integer, ChatLog> logdb = new HashMap<Integer, ChatLog>();
	
	public static void addLog(ChatLog log)
	{
		logdb.put(log.currentID,log);
	}
	public static ChatLog findLog(int id)
	{
		return logdb.get(id);
	}
}
