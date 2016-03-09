
/*
 * 2.输入一个log file 每一行是一个log 形式如下：
(02/03/2002-14:00:00) :: START
(02/03/2002-14:00:00) :: CONNECTED
(02/03/2002-14:08:00) :: DISCONNECTED
(02/03/2002-14:10:00) :: CONNECTED
(02/03/2002-14:15:00) :: SHUTDOWN

关键字是START, CONNECTED, DISCONNECTED, SHUTDOWN
就有可能同一个时间点出现两种状态，比如 14点的时候同时START和CONNECTED

需要做的是 读入文件 求用户链接时间占总时间的百分比
所谓的总时间就是START – END 连接时间就是CONNECTED – DISCONNECTED
当然logs中有不止一个CONNECTED – DISCONNECTED的pair 并且末尾有可能出现CONNECTED – SHUTDOWN
 * 
 */

import java.util.*;
import java.io.*;
import java.text.*;

public class CalConnectPercentage {

	public static void main(String[] args) throws IOException {
		// use . to get current directory
		File dir = new File(".");
		File file = new File(dir.getCanonicalPath() + File.separator + "log.txt");

		float percentage = calcPercentage(file);
		System.out.printf("\nPercentage: %.2f%% ", percentage);
	}

	private static float calcPercentage(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		Stack<Date> connectStack = new Stack<Date>();

		Stack<Date> stack = new Stack<Date>();

		int total = 0, connect = 0;

		while ((line = br.readLine()) != null) {
			String[] parts = line.split("::");
			String timeStamp = parts[0].trim(); // 02/03/2002-14:00:00
			Date date = new Date();
			// 注意format的格式要与日期String的格式相匹配
			DateFormat sdf = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)");

			try {
				date = sdf.parse(timeStamp);
				String key = parts[1].trim();

				if (key.equals("CONNECTED")) {
					connectStack.push(date);
				} else {
					if (key.equals("DISCONNECTED") || key.equals("SHUTDOWN")) {
						Date top = connectStack.pop();
						connect += minutesDiff(top, date);
					}
				}
				if (key.equals("START")) {
					stack.push(date);
				} else {
					if (key.equals("SHUTDOWN")) {
						Date top = stack.pop();
						total += minutesDiff(top, date);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		br.close();
		System.out.printf("\nConnect time : %d ", connect);
		System.out.printf("\nTotal time: %d ", total);
		float percentage = (float) connect / total * 100;
		return percentage;

	}

	public static int minutesDiff(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null)
			return 0;

		return (int) ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
	}

}
