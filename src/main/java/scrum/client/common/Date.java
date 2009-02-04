package scrum.client.common;

public class Date {

	private int year;
	private int month;
	private int day;

	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public Date(String date) {
		if (date.length() != 10) throw new RuntimeException("Illegal date format: " + date);

		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(5, 7));
		int d = Integer.parseInt(date.substring(8, 10));

		this.year = y;
		this.month = m;
		this.day = d;
	}

	public static Date today() {
		// TODO set dodays date here
		return new Date(2012, 12, 12);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("-");
		if (month < 10) sb.append('0');
		sb.append(month);
		sb.append("-");
		if (day < 10) sb.append('0');
		sb.append(day);
		return sb.toString();
	}

}